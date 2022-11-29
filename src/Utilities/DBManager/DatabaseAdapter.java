package Utilities.DBManager;

public class DatabaseAdapter {

    private DBManager dbManager;

    public DatabaseAdapter(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public void connect() {
        dbManager.connectDB();
    }

    public void loginUser(String username, String password) {
        dbManager.loginUser(username, password);
    }

    public void registerUser(String username, String password, String hint) {
        dbManager.createUser(username, password, hint);
    }

    public void subscribeAuthObserver(DBObserver observer) {
        dbManager.subscribeAuthObserver(observer);

    }

}
