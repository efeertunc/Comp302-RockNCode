package test;

import database.DBManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBManagerTest {
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
                    dbManager.checkAllRequiredFields("username", null);
                });
    }

    @Test
    @DisplayName("throws NullPointerException when username is null")
    void test3() {
        assertThrows(NullPointerException.class,
                () -> {
                    dbManager.checkAllRequiredFields(null, "password");
                });
    }

    @Test
    @DisplayName("throws IllegalArgumentException when password length is less than 6")
    void test4() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    dbManager.checkAllRequiredFields("username", "orange","12345","12345");
                });
    }

    @Test
    @DisplayName("throws IllegalArgumentException when first password does not equal second password")
    void test5() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    dbManager.checkAllRequiredFields("username", "orange","123456","012345");
                });
    }

    @Test
    @DisplayName("throws IllegalArgumentException when username length is less than 6")
    void test6() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    dbManager.checkAllRequiredFields("12345", "orange","123456","123456");
                });
    }

    @Test
    @DisplayName("return true when all inputs are valid")
    void test7() {
        assertTrue(dbManager.checkAllRequiredFields("username", "orange","123456","123456"));
    }

    @AfterEach
    void tearDown() {
        dbManager.closeFirebase();
    }
}
