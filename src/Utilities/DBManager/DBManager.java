package Utilities.DBManager;

import HelperComponents.Position;
import Models.Account;
import Models.Constants;
import Models.Player;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import domain.*;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;
import objects.ObjectTile;
import objects.ObjectTileFactory;
import panels.BuildPanel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class DBManager implements IDatabaseAdapter{

    private static DBManager instance = new DBManager();
    private DBObserver DBObserver;
    private DatabaseReference database;
    private final ArrayList<Account> userList = new ArrayList<>();
    private ObjectTile[][] objectTiles = new ObjectTile[12][17];

    private Map< Integer, ObjectTile[][]> map ;

    private static int currentIndexFromDB;
    private static boolean isSaved = false;


    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    @Override
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
    @Override
    public void loginUser(String username, String password) {
        if (checkUserLogin(username, password)) {
            isSaved();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (isSaved){
                // DBden gelen mapler alındı.
                getMapForLoadGame();
                getLastIndexFromDB();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                BuildingTracker.setCurrentIndex(currentIndexFromDB);

                for (int i = 0; i < 6 ; i++){
                    // Build listesine kendisinin mapi eklendi.
                    BuildingTracker.getBuildingList().get(i).setMap_obj(map.get(i));
                }
            }else {
                //Kayıt yok.
                getMapForLoadGame();
                // getMapForLoadGame() fonksiyonu kayıt olmamış mapler için EmptyTile ekledi.
                for (int i = 0; i < 6 ; i++){
                    // Build listesine kendisinin mapi eklendi.
                    BuildingTracker.getBuildingList().get(i).setMap_obj(map.get(i));
                }
            }
            ((BuildPanel) EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Build)).getBuildingMap().setMapForDB();
            ((BuildPanel) EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Build)).setText();
            ((BuildPanel) EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Build)).controlOfNextButton();
            notifyAuthObservers(Constants.DatabaseConstants.LOGIN_ACCEPTED);
        } else {
            notifyAuthObservers(Constants.DatabaseConstants.LOGIN_REJECTED);
        }
    }

    @Override
    public void registerUser(String username, String firstPassword, String secondPassword, String hint) {
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

    @Override
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

    public void isSaved() {
        database.child("users").child(Player.getInstance().getAccount().getID()).child("isSaved").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                isSaved = snapshot.getValue(Boolean.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                notifyAuthObservers(Constants.DatabaseConstants.DATABASE_READ_ERROR);
            }
        });

    }

    @Override
    public void saveGame() {
        Account account = Player.getInstance().getAccount();
        String id = account.getID();
        Map<String, Object> mapList = new HashMap<>();
        Map<String, Object> currentIndex = new HashMap<>();

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
        currentIndex.put("/users/" + id + "/" + "currentIndex", BuildingTracker.getCurrentIndex());
        currentIndex.put("/users/" + id + "/" + "isSaved",true);
        database.updateChildren(currentIndex, new DatabaseReference.CompletionListener() {
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

    private void getMapForLoadGame() {
        map = new HashMap<>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 12; j++) {
                for (int k = 0; k < 17; k++) {
                    int finalJ = j;
                    int finalK = k;
                    int finalI = i;
                    database.child("users").child(Player.getInstance().getAccount().getID()).child("map").child(String.valueOf(i)).child(String.valueOf(j)).child(String.valueOf(k)).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                                ObjectTile obj = ObjectTileFactory.createTile(dataSnapshot);
                                objectTiles[finalJ][finalK] = obj;

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            notifyAuthObservers(Constants.DatabaseConstants.DATABASE_READ_ERROR);
                        }
                    });
                }
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (objectTiles[0][0] != null){
                System.out.println("index: " + i + " map is loaded from db");
                map.put(i, objectTiles);
                objectTiles = new ObjectTile[12][17];
            }
            else{
                // Kayıt yok. Maplere emptyTile ekliyoruz.
                System.out.println("index: " + i + " map is loaded from getEmptyMap");
                map.put(i, getNewEmptyMap());
            }

        }
    }

    private ObjectTile[][] getNewEmptyMap() {
        ObjectTile[][] objectTiles = new ObjectTile[12][17];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 17; j++) {
                objectTiles[i][j] = new EmptyTile(j,i,4);
            }
        }
        return objectTiles;
    }

    private void getLastIndexFromDB(){
        database.child("users").child(Player.getInstance().getAccount().getID()).child("currentIndex").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                currentIndexFromDB = snapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                notifyAuthObservers(Constants.DatabaseConstants.DATABASE_READ_ERROR);
            }
        });

        /*for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 12; j++) {
                for (int k = 0; k < 17; k++) {
                    if (map.get(i)[j][k] == null){
                        System.out.println("Last index in getLastIndexFromDB fuction is: " + i);
                        return i;
                    }
                }
            }
        }
        return 5;*/
    }

}
