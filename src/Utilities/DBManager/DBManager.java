package Utilities.DBManager;

import Models.Account;
import Models.Constants;
import Models.Player;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import domain.*;
import objects.ObjectTile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class DBManager {

    private static DBManager instance = new DBManager();
    private DBObserver DBObserver;
    private DatabaseReference database;
    private final ArrayList<Account> userList = new ArrayList<>();


    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }

        return instance;
    }

    public void connectDB() {
        try {
            FileInputStream serviceAccount;
            {
                try {
                    serviceAccount = new FileInputStream(Constants.DatabaseConstants.JSON);
                    FirebaseOptions options = new FirebaseOptions.Builder()
                            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                            .build();
                    FirebaseApp.initializeApp(options);
                    database = FirebaseDatabase.getInstance("https://comp302-4de74-default-rtdb.firebaseio.com").getReference();
                    getListOfUsers();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Auth Functions

    public void loginUser(String username, String password) {
        if (checkUserLogin(username, password)) {
            notifyAuthObservers(Constants.DatabaseConstants.LOGIN_ACCEPTED);
        } else {
            notifyAuthObservers(Constants.DatabaseConstants.LOGIN_REJECTED);
        }
    }

    public void createUser(String username, String firstPassword, String secondPassword, String hint) {
        if (!checkUsernameRegister(username)) {
            notifyAuthObservers(Constants.DatabaseConstants.USERNAME_NOT_AVAILABLE);
            return;
        }

        if (username.length() < 6) {
            notifyAuthObservers(Constants.DatabaseConstants.USERNAME_LENGTH);
            return;
        }

        if (firstPassword.length() < 6) {
            notifyAuthObservers(Constants.DatabaseConstants.PASSWORD_LENGTH);
            return;
        }

        if (!firstPassword.equals(secondPassword)) {
            notifyAuthObservers(Constants.DatabaseConstants.PASSWORD_SAME_REGISTER);
            return;
        }

        String id = database.child("users").push().getKey();
        Account account = new Account(username, firstPassword, hint, id);

        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> accountValues = account.toMap();

        childUpdates.put("/users/" + account.getID(), accountValues);

        database.updateChildren(childUpdates, (error, ref) -> notifyAuthObservers(Constants.DatabaseConstants.DATABASE_WRITE_ERROR + " because of" + error.getDetails()));

        Player.getInstance().setAccount(account);
        notifyAuthObservers(Constants.DatabaseConstants.REGISTER_ACCEPTED);

    }

    public void forgotPassword(String username, String hint, String firstPassword, String secondPassword) {

        if (firstPassword.length() < 6) {
            notifyAuthObservers(Constants.DatabaseConstants.PASSWORD_LENGTH_FORGOT);
            return;
        }

        if (!firstPassword.equals(secondPassword)) {
            notifyAuthObservers(Constants.DatabaseConstants.PASSWORD_SAME_FORGOT);
            return;
        }
        Account account = checkUsernameForgotPassword(username,hint);

        if (account != null){
            account.setPassword(firstPassword);
            Map<String, Object> childUpdates = new HashMap<>();
            Map<String, Object> accountValues = account.toMap();

            childUpdates.put("/users/" + account.getID(), accountValues);

            database.updateChildren(childUpdates, (error, ref) -> notifyAuthObservers(Constants.DatabaseConstants.DATABASE_WRITE_ERROR + " because of" + error.getDetails()));
            notifyAuthObservers(Constants.DatabaseConstants.PASSWORD_CHANGED);
        }else {
            notifyAuthObservers(Constants.DatabaseConstants.HINT_WRONG);
        }
    }

    private boolean checkUsernameRegister(String username) {
        getListOfUsers();
        for (Account account : userList) {
            if (account.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    private Account checkUsernameForgotPassword(String username, String hint) {
        getListOfUsers();
        for (Account account : userList) {
            if (account.getUsername().equals(username) && account.getHint().equals(hint)) {
                return account;
            }
        }
        return null;
    }

    private boolean checkUserLogin(String username, String password) {
        getListOfUsers();
        for (Account account : userList) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                Player.getInstance().setAccount(account);
                return true;
            }
        }
        return false;
    }

    private void getListOfUsers() {
        database.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Account account = dataSnapshot.getValue(Account.class);
                    userList.add(account);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                notifyAuthObservers(Constants.DatabaseConstants.DATABASE_READ_ERROR);
            }
        });
    }

    public void notifyAuthObservers(String response) {
        if (response.equals(Constants.DatabaseConstants.LOGIN_ACCEPTED)) {
            DBObserver.loginAccepted(Player.getInstance().getAccount(), response);
            return;
        }
        if (response.equals(Constants.DatabaseConstants.LOGIN_REJECTED)) {
            DBObserver.loginRejected(response);
            return;
        }
        if (response.equals(Constants.DatabaseConstants.REGISTER_ACCEPTED)) {
            DBObserver.registerAccepted(Player.getInstance().getAccount(), response);
            return;
        }
        if (response.equals(Constants.DatabaseConstants.REGISTER_REJECTED)) {
            DBObserver.registerRejected(response);
            return;
        }
        if (response.equals(Constants.DatabaseConstants.PASSWORD_LENGTH)) {
            DBObserver.registerRejected(response);
            return;
        }
        if (response.equals(Constants.DatabaseConstants.PASSWORD_SAME_REGISTER)) {
            DBObserver.registerRejected(response);
            return;
        }
        if (response.equals(Constants.DatabaseConstants.USERNAME_LENGTH)) {
            DBObserver.registerRejected(response);
            return;
        }
        if (response.equals(Constants.DatabaseConstants.USERNAME_NOT_AVAILABLE)) {
            DBObserver.registerRejected(response);
            return;
        }
        if (response.equals(Constants.DatabaseConstants.PASSWORD_CHANGED)) {
            DBObserver.changePasswordAccepted(response);
            return;
        }
        if (response.equals(Constants.DatabaseConstants.HINT_WRONG)) {
            DBObserver.changePasswordRejected(response);
            return;
        }
        if (response.equals(Constants.DatabaseConstants.PASSWORD_SAME_FORGOT)) {
            DBObserver.changePasswordRejected(response);
            return;
        }
        if (response.equals(Constants.DatabaseConstants.PASSWORD_LENGTH_FORGOT)) {
            DBObserver.changePasswordRejected(response);
        }

    }

    public void subscribeAuthObserver(DBObserver observer) {
        this.DBObserver = observer;
    }

    //Save Game Functions

    public void saveGame() {
        Account account = Player.getInstance().getAccount();
        String id = account.getID();
        Map<String, Object> mapList = new HashMap<>();

        clearDatabase(id);

        for (int i = 0; i < 6; i++) {
            if (BuildingTracker.getBuildingList().get(i).getMap_obj() != null){
                for (int j = 0; j < 12; j++) {
                    for (int k = 0; k < 17; k++) {

                        ObjectTile objectTile = BuildingTracker.getBuildingList().get(i).getMap_obj()[j][k];

                        if (objectTile instanceof Alien){
                            mapList.put("/users/" + id + "/" + "map" + "/"+  i + "/" + j + "/" + k + "/" + "Alien", objectTile);

                        }
                        if (objectTile instanceof Avatar){
                            mapList.put("/users/" + id + "/" + "map" + "/" + i + "/" + j + "/" + k + "/" + "Avatar", objectTile);

                        }
                        if (objectTile instanceof EmptyTile){
                            mapList.put("/users/" + id + "/" + "map" + "/" + i + "/" + j + "/" + k + "/" + "EmptyTile", objectTile);

                        }
                        if (objectTile instanceof Obstacle){
                            mapList.put("/users/" + id + "/" + "map" + "/" + i + "/" + j + "/" + k + "/" + "Obstacle", objectTile);

                        }
                        if (objectTile instanceof TimeWasterAlien){
                            mapList.put("/users/" + id + "/" + "map" + "/" + i + "/" + j + "/" + k + "/" + "TimeWasterAlien", objectTile);

                        }
                    }
                }

                database.updateChildren(mapList, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            System.out.println("Data could not be saved " + databaseError.getMessage());
                        } else {
                            System.out.println("Data saved successfully.");
                        }
                    }
                });


            }
        }
    }

    private void clearDatabase(String id) {
        database.child("users").child(id).child("map").removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                if (error != null) {
                    System.out.println("Data could not be deleted " + error.getMessage());
                } else {
                    System.out.println("Data deleted successfully.");
                }
            }
        });
    }
}
