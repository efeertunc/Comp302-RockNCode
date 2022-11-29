package Utilities.DBManager;

import Models.Account;

public interface DBObserver {

    public void loginAccepted(Account user, String response);

    public void loginRejected(String response);

    public void registerAccepted(Account user, String response);

    public void registerRejected(String response);

}
