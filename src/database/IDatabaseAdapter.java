package database;

public interface IDatabaseAdapter {

    //Auth Functions

    void connectDB();

    void loginUser(String username, String password);

    void registerUser(String username, String firstPassword, String secondPassword, String hint);

    void forgotPassword(String username, String hint, String firstPassword, String secondPassword);

    void subscribeAuthObserver(DBObserver observer);

    //Save Game Functions
    void saveGame(boolean isRunningMode);

}
