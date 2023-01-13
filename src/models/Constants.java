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
        public static final BufferedImage ALIEN_BLIND_ATTACK;
        public static final BufferedImage ALIEN_SHOOTER_ATTACK;
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

        public static final BufferedImage EXTRATIME;
        public static final BufferedImage EXTRALIFE;
        public static final BufferedImage OMER ;
        public static final BufferedImage CASE;
        public static final BufferedImage SOS;
        public static final BufferedImage SCIE;
        public static final BufferedImage ENG;
        public static final BufferedImage SNA;
        public static final BufferedImage STONE;
        public static final BufferedImage WOOD;
        public static final BufferedImage POT;
        public static final BufferedImage ENTER;
        static {
            try {
                ALIEN_TIMEWASTER = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/alienTimeWaster.png")));
                ALIEN_BLIND = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/alienBlind.png")));
                ALIEN_SHOOTER = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/alienShooter.png")));
                ALIEN_SHOOTER_ATTACK = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/alienShooterAttack.png")));
                ALIEN_BLIND_ATTACK = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/alienBlindAttack.png")));
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
                EXTRATIME = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/extratime.png")));
                EXTRALIFE = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/life.png"))); //12id
                OMER = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/grass.jpeg"))); //15
                CASE = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/ice.jpg"))); //16
               SOS = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/orange.png")));  //17
                SCIE = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/woodtile.jpeg")));  //18
                ENG = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/white.jpeg")));  //19
                SNA = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/pink.jpg")));   //20
                STONE = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/stone.png"))); //21
                WOOD = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/wood.png")));  //22
                POT = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/flowerpot.png"))); //23
                ENTER = (ImageIO.read(EscapeFromKoc.class.getResource("/visual/entrance.png")));//24

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    abstract  class FileConstants {
        public static final String[] fileList = new String[6];
        static {
            fileList[0]="omer.txt";
            fileList[1]="case.txt";
            fileList[2]="sos.txt";
            fileList[3]="scie.txt";
            fileList[4]="eng.txt";
            fileList[5]="sna.txt";
        }

    }

}
