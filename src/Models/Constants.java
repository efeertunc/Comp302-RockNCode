package Models;

public interface Constants {

    interface DatabaseConstants {
        String LOGIN_ACCEPTED = "Welcome ";
        String LOGIN_REJECTED = "Please check your username or password";
        String REGISTER_ACCEPTED = "Register successfully. You can log in now with using ";
        String REGISTER_REJECTED = "Register failed";
        String PASSWORD_LENGTH = "Password length should not be lower than 6";
        String USERNAME_LENGTH = "Username length should not be lower than 6";
        String PASSWORD_SAME = "Passwords do not match";
        String USERNAME_NOT_AVAILABLE = "This username is used by another user. Decide another username";
        String DATABASE_WRITE_ERROR = "Database write error has occured";
        String DATABASE_READ_ERROR = "Database read error has occured";
        String JSON = "src/Utilities/DBManager/comp302-4de74-firebase-adminsdk-yfz83-9552d14b68.json";
    }

}
