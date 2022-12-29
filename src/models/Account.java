package models;

import java.util.HashMap;
import java.util.Map;

public class Account {
    String username;
    String password;
    String hint;
    String ID;

    public Account(String username, String password, String hint, String ID) {
        this.username = username;
        this.password = password;
        this.hint = hint;
        this.ID = ID;
    }

    public Account() {
    }

    public String getUsername() {
        return username;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();
        result.put("username", username);
        result.put("password", password);
        result.put("hint", hint);
        result.put("ID", ID);

        return result;
    }
}
