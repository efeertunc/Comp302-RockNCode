package database;

import domain.gameObjects.alien.blind.BlindAlien;
import domain.gameObjects.alien.shooter.ShooterAlien;
import domain.gameObjects.avatar.Bag;
import domain.gameObjects.powerUps.PowerUpTile;
import helperComponents.Position;
import models.Account;
import models.Constants;
import models.Player;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import domain.building.BuildingTracker;
import domain.gameObjects.avatar.Avatar;
import domain.gameObjects.EmptyTile;
import domain.gameObjects.obstacle.Obstacle;
import domain.gameObjects.alien.timeWaster.TimeWasterAlien;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;
import domain.gameObjects.ObjectTile;
import factory.ObjectTileFactory;
import panels.BuildPanel;
import panels.LoginPanel;
import panels.MenuPanel;

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
    private static boolean isRunningMode = false;


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
        if (checkAllRequiredFieldsLogin(username, password)){
            if (checkUserLogin(username, password)) {
                isSaved();
             
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                isRunningMode();
                if (isSaved){
                    // DBden gelen mapler alındı.
                    System.out.println("is saved true");
                    getMapForLoadGame();
                    System.out.println("get map is savedden sonra");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    getLastIndexFromDB();

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    BuildingTracker.setCurrentIndex(currentIndexFromDB);
                    System.out.println("index : "+ BuildingTracker.getCurrentIndex());

                    for (int i = 0; i < 6 ; i++){
                        // Build listesine kendisinin mapi eklendi.
                        BuildingTracker.getBuildingList().get(i).setMap_obj(map.get(i));
                    }
                }else {
                    //Kayıt yok.
                    System.out.println("Kayıt yok");
                    getMapForLoadGame();
                    System.out.println("kayıt yok 2");
                    // getMapForLoadGame() fonksiyonu kayıt olmamış mapler için EmptyTile ekledi.
                    for (int i = 0; i < 6 ; i++){
                        // Build listesine kendisinin mapi eklendi.
                        BuildingTracker.getBuildingList().get(i).setMap_obj(map.get(i));
                    }
                }
                if (!isRunningMode){
                    System.out.println("build mode");
                    ((BuildPanel) EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Build)).loadGameForBuilding();

                }else {
                    System.out.println("rıunning mode");
                    ((MenuPanel) EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Menu)).setRunningMode(isRunningMode);
                }

                notifyAuthObservers(Constants.DatabaseConstants.LOGIN_ACCEPTED);
            } else {
                notifyAuthObservers(Constants.DatabaseConstants.LOGIN_REJECTED);
            }
        }

    }

    @Override
    public void registerUser(String username, String firstPassword, String secondPassword, String hint) {
        if (!checkUsernameRegister(username)) {
            notifyAuthObservers(Constants.DatabaseConstants.USERNAME_NOT_AVAILABLE);
            return;
        }
        if (checkAllRequiredFieldsRegister(username, hint, firstPassword, secondPassword)){
            String id = database.child("users").push().getKey();
            Account account = new Account(username, firstPassword, hint, id);

            Map<String, Object> childUpdates = new HashMap<>();
            Map<String, Object> accountValues = account.toMap();

            childUpdates.put("/users/" + account.getID(), accountValues);

            database.updateChildren(childUpdates, (error, ref) -> notifyAuthObservers(Constants.DatabaseConstants.DATABASE_WRITE_ERROR + " because of" + error.getDetails()));

            Player.getInstance().setAccount(account);
            notifyAuthObservers(Constants.DatabaseConstants.REGISTER_ACCEPTED);
        }
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

    public boolean checkAllRequiredFieldsRegister(String username, String hint, String firstPassword, String secondPassword){
        try {
            return checkAllRequiredFields(username, hint, firstPassword, secondPassword);
        }
        catch (Exception e) {
            notifyAuthObservers(e.getMessage());
            return false;
        }
    }

    public boolean checkAllRequiredFieldsLogin(String username, String password){
        try {
            return checkAllRequiredFields(username, password);
        }
        catch (Exception e) {
            notifyAuthObservers(e.getMessage());
            return false;
        }
    }

    public boolean checkAllRequiredFields(String username, String hint, String firstPassword, String secondPassword) {
        if (username == null || firstPassword == null || secondPassword == null || hint == null) {
            throw new NullPointerException("Username or password or hint can not be null");
        }
        if (username.length() < 6){
            throw new IllegalArgumentException(Constants.DatabaseConstants.USERNAME_LENGTH);
        }
        if (firstPassword.length() < 6){
            throw new IllegalArgumentException(Constants.DatabaseConstants.PASSWORD_LENGTH);
        }
        if (secondPassword.length() < 6){
            throw new IllegalArgumentException(Constants.DatabaseConstants.PASSWORD_LENGTH);
        }
        if (!firstPassword.equals(secondPassword)){
            throw new IllegalArgumentException(Constants.DatabaseConstants.PASSWORD_SAME_REGISTER);
        }
        return true;
    }

    public boolean checkAllRequiredFields(String username, String password) {
        if (username == null || password == null ) {
            throw new NullPointerException("Username or password can not be null");
        }
        if (username.length() < 6){
            throw new IllegalArgumentException(Constants.DatabaseConstants.LOGIN_REJECTED);
        }
        if (password.length() < 6){
            throw new IllegalArgumentException(Constants.DatabaseConstants.LOGIN_REJECTED);
        }
        return true;
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

    public void isRunningMode() {
        database.child("users").child(Player.getInstance().getAccount().getID()).child("isRunningMode").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                isRunningMode = snapshot.getValue(Boolean.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                notifyAuthObservers(Constants.DatabaseConstants.DATABASE_READ_ERROR);
            }
        });

    }

    @Override
    public void saveGame(boolean isRunningMode) {
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

                        if (objectTile instanceof Avatar){
                            int life = ((Avatar) objectTile).getLife();
                            double currentTime = (int) ((Avatar) objectTile).getCurrentTime();
                            Position position = ((Avatar) objectTile).getPosition();
                            int image = (objectTile).getImage();
                            Bag bag = ((Avatar) objectTile).getBag();
                            int vestTime = (int) ((Avatar) objectTile).getVestTime();
                            int hintTime = (int) ((Avatar) objectTile).getHintTime();
                            boolean hasKey = ((Avatar) objectTile).isHasKey();

                            mapList.put("/users/" + id + "/" + "map" + "/" + i + "/" + j + "/" + k + "/" + "Avatar" + "/" + "life", life);
                            mapList.put("/users/" + id + "/" + "map" + "/" + i + "/" + j + "/" + k + "/" + "Avatar" + "/" + "currentTime", currentTime);
                            mapList.put("/users/" + id + "/" + "map" + "/" + i + "/" + j + "/" + k + "/" + "Avatar" + "/" + "position", position);
                            mapList.put("/users/" + id + "/" + "map" + "/" + i + "/" + j + "/" + k + "/" + "Avatar" + "/" + "image", image);
                            mapList.put("/users/" + id + "/" + "map" + "/" + i + "/" + j + "/" + k + "/" + "Avatar" + "/" + "bag", bag);
                            mapList.put("/users/" + id + "/" + "map" + "/" + i + "/" + j + "/" + k + "/" + "Avatar" + "/" + "vestTime", vestTime);
                            mapList.put("/users/" + id + "/" + "map" + "/" + i + "/" + j + "/" + k + "/" + "Avatar" + "/" + "hintTime", hintTime);
                            mapList.put("/users/" + id + "/" + "map" + "/" + i + "/" + j + "/" + k + "/" + "Avatar" + "/" + "hasKey", hasKey);

                        }
                        if (objectTile instanceof EmptyTile){
                            mapList.put("/users/" + id + "/" + "map" + "/" + i + "/" + j + "/" + k + "/" + "EmptyTile", objectTile);

                        }
                        if (objectTile instanceof Obstacle){
                            if (((Obstacle) objectTile).getKey() != null){
                                objectTile.setImage(((Obstacle) objectTile).getSavedImage());
                            }
                            mapList.put("/users/" + id + "/" + "map" + "/" + i + "/" + j + "/" + k + "/" + "Obstacle", objectTile);

                        }
                        if (objectTile instanceof TimeWasterAlien){
                            mapList.put("/users/" + id + "/" + "map" + "/" + i + "/" + j + "/" + k + "/" + "TimeWasterAlien", objectTile);
                        }
                        if (objectTile instanceof ShooterAlien){
                            mapList.put("/users/" + id + "/" + "map" + "/" + i + "/" + j + "/" + k + "/" + "ShooterAlien", objectTile);
                        }
                        if (objectTile instanceof BlindAlien){
                            mapList.put("/users/" + id + "/" + "map" + "/" + i + "/" + j + "/" + k + "/" + "BlindAlien", objectTile);
                        }
                        if (objectTile instanceof PowerUpTile){
                            mapList.put("/users/" + id + "/" + "map" + "/" + i + "/" + j + "/" + k + "/" + "PowerUpTile", objectTile);
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
        currentIndex.put("/users/" + id + "/" + "isRunningMode",isRunningMode);
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

        database.child("users").child(id).child("currentIndex").removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                if (error != null) {
                    System.out.println("Data could not be deleted " + error.getMessage());
                } else {
                    System.out.println("Data deleted successfully.");
                }
            }
        });

        database.child("users").child(id).child("isSaved").removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                if (error != null) {
                    System.out.println("Data could not be deleted " + error.getMessage());
                } else {
                    System.out.println("Data deleted successfully.");
                }
            }
        });

        database.child("users").child(id).child("isRunningMode").removeValue(new DatabaseReference.CompletionListener() {
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
                    int finalI1 = i;
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

    public void closeFirebase(){
        FirebaseApp.getInstance().delete();
    }

}
