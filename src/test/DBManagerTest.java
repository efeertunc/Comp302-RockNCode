/*
package test;

import Models.Account;
import Utilities.DBManager.DBManager;
import Utilities.DBManager.DBObserver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBManagerTest implements DBObserver {
    DBManager dbManager;
    @BeforeEach
    void init() throws Exception {
        dbManager = DBManager.getInstance();
        dbManager.connectDB();
    }

    @Test
    @DisplayName("throws NullPointerException when both arguments are null")
    void test1() {
        assertThrows(NullPointerException.class,
                () -> {
                    dbManager.checkAllRequiredFields(null, null);
                });
    }

    @Test
    @DisplayName("throws NullPointerException when password is null")
    void test2() {
        assertThrows(NullPointerException.class,
                () -> {
                    dbManager.checkAllRequiredFields("user", null);
                });
    }

    @Test
    @DisplayName("throws IllegalArgumentException when password length is less than 6")
    void test3() {
        assertTrue(dbManager.checkAllRequiredFields("sdsdsdsdsd", "12345ffff"));
    }

    @AfterEach
    void tearDown() {
    }

    @Override
    public void loginAccepted(Account user, String response) {

    }

    @Override
    public void loginRejected(String response) {

    }

    @Override
    public void registerAccepted(Account user, String response) {

    }

    @Override
    public void registerRejected(String response) {

    }

    @Override
    public void changePasswordAccepted(String response) {

    }

    @Override
    public void changePasswordRejected(String response) {

    }
}*/
