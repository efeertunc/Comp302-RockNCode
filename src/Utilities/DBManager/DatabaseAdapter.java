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

    public void registerUser(String username, String firstPassword, String secondPassword, String hint) {
        dbManager.createUser(username, firstPassword, secondPassword, hint);
    }

    public void forgotPassword(String username, String hint, String firstPassword, String secondPassword){
        dbManager.forgotPassword(username, hint, firstPassword, secondPassword);
    }

    public void subscribeAuthObserver(DBObserver observer) {
        dbManager.subscribeAuthObserver(observer);

    }

}
