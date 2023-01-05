package models;

import main.EscapeFromKoc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public interface Constants {

    interface DatabaseConstants {
        String LOGIN_ACCEPTED = "Welcome ";
        String LOGIN_REJECTED = "Please check your username or password";
        String REGISTER_ACCEPTED = "Register successfully. You can log in now with using ";
        String REGISTER_REJECTED = "Register failed";
        String PASSWORD_LENGTH = "Password length should not be lower than 6";
        String PASSWORD_LENGTH_FORGOT = "Password length should not be lower than 6.";
        String USERNAME_LENGTH = "Username length should not be lower than 6";
        String PASSWORD_SAME_REGISTER = "Passwords do not match";
        String PASSWORD_SAME_FORGOT = "Passwords do not match.";
        String USERNAME_NOT_AVAILABLE = "This username is used by another user. Decide another username";
        String DATABASE_WRITE_ERROR = "Database write error has occured";
        String DATABASE_READ_ERROR = "Database read error has occured";
        String PASSWORD_CHANGED = "You changed your password successfully";
        String HINT_WRONG = "Your hint and username does not match";
        String JSON = "src/database/comp302-4de74-firebase-adminsdk-yfz83-9552d14b68.json";
    }

    abstract  class ImageConstants{
        public static final BufferedImage ALIEN_TIMEWASTER;
        public static final BufferedImage ALIEN_SHOOTER;
        public static final BufferedImage ALIEN_BLIND;
        public static final BufferedImage CHAIR;
        public static final BufferedImage BIN;
        public static final BufferedImage TABLE;
        public static final BufferedImage EMPTY;
        public static final BufferedImage AVATAR;
        public static final BufferedImage AVATAR_HAPPY;
        public static final BufferedImage KEY;
        public static final BufferedImage SHELVE;
        public static final BufferedImage OPENDOOR;
        public static final BufferedImage CLOSEDOOR;

        static {
            try {
                ALIEN_TIMEWASTER = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/alienTimeWaster.png")));
                ALIEN_BLIND = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/alienBlind.png")));
                ALIEN_SHOOTER = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/alienShooter.png")));
                CHAIR = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/chair_200.png")));
                BIN = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/bin.png")));
                TABLE = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/table1.png")));
                EMPTY = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/empty.png")));
                AVATAR = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/avatar.png")));
                AVATAR_HAPPY = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/avatarHappy.png")));
                KEY = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/keyObj.png")));
                SHELVE = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/shelve.png")));
                OPENDOOR = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/opened_big.png")));
                CLOSEDOOR = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/closed.png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
