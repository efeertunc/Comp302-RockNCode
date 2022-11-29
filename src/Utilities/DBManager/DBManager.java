package Utilities.DBManager;

import Models.Account;
import Models.Constants;
import Models.Player;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public final class DBManager {

    private static DBManager instance = new DBManager();
    private DBObserver DBObserver;
    private Firestore database;

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
                    database = FirestoreClient.getFirestore();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginUser(String username, String password) {
        for (Account account : readData()) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                notifyAuthObservers(Constants.DatabaseConstants.LOGIN_ACCEPTED);
                Player.getInstance().setAccount(account);
                return;
            }
        }
        notifyAuthObservers(Constants.DatabaseConstants.LOGIN_REJECTED);
    }

    public ArrayList<Account> readData() {
        ArrayList<Account> playerArrayList = new ArrayList<>();

        ApiFuture<QuerySnapshot> query = database.collection("users").get();
        QuerySnapshot querySnapshot = null;
        try {
            querySnapshot = query.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        List<QueryDocumentSnapshot> documents = null;
        if (querySnapshot != null) {
            documents = querySnapshot.getDocuments();
        }
        if (documents != null) {
            for (QueryDocumentSnapshot document : documents) {
                Account player = new Account(document.getString("username"), document.getString("password"), document.getString("hint"), document.getId());
                playerArrayList.add(player);
            }
        }
        return playerArrayList;
    }

    public void createUser(String username, String password, String hint) {
        DatabaseReference mDatabaseUsername = FirebaseDatabase.getInstance("https://comp302-4de74-default-rtdb.firebaseio.com").getReference();
        String id = mDatabaseUsername.child("users").push().getKey();
        Account account = new Account(username, password, hint, id);

        for (Account account1 : readData()) {
            if (account1.getUsername().equals(account.getUsername())) {
                System.out.println("Username kullanımda. Lütfen başka bir username seçiniz.");
                return;
            }
        }

        DocumentReference docRef = database.collection("users").document(id);
        ApiFuture<WriteResult> result = docRef.set(account);
        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
            notifyAuthObservers(Constants.DatabaseConstants.REGISTER_ACCEPTED);
        } catch (InterruptedException | ExecutionException e) {
            notifyAuthObservers(Constants.DatabaseConstants.REGISTER_REJECTED);
            e.printStackTrace();
        }
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
        }

    }

    public void subscribeAuthObserver(DBObserver observer) {
        this.DBObserver = observer;
    }
}
