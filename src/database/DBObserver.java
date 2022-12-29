package database;

import models.Account;

public interface DBObserver {

    public void loginAccepted(Account user, String response);

    public void loginRejected(String response);

    public void registerAccepted(Account user, String response);

    public void registerRejected(String response);

    public void changePasswordAccepted(String response);

    public void changePasswordRejected(String response);

}
