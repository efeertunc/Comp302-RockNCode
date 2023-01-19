package factory;

import domain.gameObjects.alien.blind.BlindAlien;
import domain.gameObjects.alien.shooter.ShooterAlien;
import domain.gameObjects.alien.timeWaster.TimeWasterAlien;
import domain.gameObjects.avatar.Avatar;
import domain.gameObjects.avatar.Bag;
import domain.gameObjects.obstacle.Obstacle;
import domain.gameObjects.powerUps.PowerUpTile;
import domain.gameObjects.powerUps.PowerUpTypes;
import helperComponents.Position;
import com.google.firebase.database.DataSnapshot;
import domain.gameObjects.*;

public class ObjectTileFactory {

    private static ObjectTileFactory instance;

    private ObjectTileFactory() {
    }

    public static ObjectTileFactory getInstance() {
        if (instance == null) {
            instance = new ObjectTileFactory();
        }
        return instance;
    }

    public static ObjectTile createTile(DataSnapshot dataSnapshot) {
        //int ID = dataSnapshot.child("id").getValue(Integer.class);
        int image = dataSnapshot.child("image").getValue(Integer.class);
        int x = dataSnapshot.child("position").child("x").getValue(Integer.class);
        int y = dataSnapshot.child("position").child("y").getValue(Integer.class);
        Position position = new Position(x, y);

        String ObjectType = dataSnapshot.getKey();

        switch (ObjectType) {
            case "Avatar":
                int life = dataSnapshot.child("life").getValue(Integer.class);
                int currentTime = dataSnapshot.child("currentTime").getValue(Integer.class);
                int vestTime = dataSnapshot.child("vestTime").getValue(Integer.class);
                int hintTime = dataSnapshot.child("hintTime").getValue(Integer.class);
                boolean hasKey = dataSnapshot.child("hasKey").getValue(Boolean.class);
                int bottleNum = dataSnapshot.child("bag").child("powers").child("BOTTLE").child("num").getValue(Integer.class);
                System.out.println("Bottle num: "+ bottleNum);
                int hintNum = dataSnapshot.child("bag").child("powers").child("HINT").child("num").getValue(Integer.class);
                int vestNum = dataSnapshot.child("bag").child("powers").child("VEST").child("num").getValue(Integer.class);

                Bag bag1 = new Bag();
                bag1.setNumToPowerUp(PowerUpTypes.BOTTLE, bottleNum);
                bag1.setNumToPowerUp(PowerUpTypes.HINT, hintNum);
                bag1.setNumToPowerUp(PowerUpTypes.VEST, vestNum);

                System.out.println("Bag from db");
                return new Avatar(life, currentTime, x, y,image, bag1, vestTime, hintTime, hasKey);
            case "EmptyTile":
                return new EmptyTile(x, y, image);
            case "Obstacle":
                int type = dataSnapshot.child("type").getValue(Integer.class);
                return new Obstacle(type,x ,y,image);
            case "TimeWasterAlien":
                return new TimeWasterAlien(x,y,image);
            case "ShooterAlien":
                return new ShooterAlien(x,y,image);
            case "BlindAlien":
                return new BlindAlien(x,y,image);
            case "PowerUpTile":
                int time2 = dataSnapshot.child("time").getValue(Integer.class);
                String type2 = dataSnapshot.child("type").getValue(String.class);
                switch (type2) {
                    case "VEST":
                        return new PowerUpTile(PowerUpTypes.VEST, x, y, image, time2);
                    case "HINT":
                        return new PowerUpTile(PowerUpTypes.HINT, x, y, image, time2);
                    case "BOTTLE":
                        return new PowerUpTile(PowerUpTypes.BOTTLE, x, y, image, time2);
                    case "EXTRA_LIFE":
                        return new PowerUpTile(PowerUpTypes.EXTRA_LIFE, x, y, image, time2);
                    case "EXTRA_TIME":
                        return new PowerUpTile(PowerUpTypes.EXTRA_TIME, x, y, image, time2);
                }
        }
        return null;
    }
}
