package factory;

import domain.gameObjects.alien.TimeWasterAlien;
import domain.gameObjects.avatar.Avatar;
import domain.gameObjects.obstacle.Obstacle;
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
        int ID = dataSnapshot.child("id").getValue(Integer.class);
        int image = dataSnapshot.child("image").getValue(Integer.class);
        int x = dataSnapshot.child("position").child("x").getValue(Integer.class);
        int y = dataSnapshot.child("position").child("y").getValue(Integer.class);
        Position position = new Position(x, y);

        String ObjectType = dataSnapshot.getKey();

        switch (ObjectType) {
            case "Avatar":
                //int life = dataSnapshot.child("life").getValue(Integer.class);
                //int time = dataSnapshot.child("time").getValue(Integer.class);
                return new Avatar(0, 60, x, y, image);
            case "EmptyTile":
                return new EmptyTile(x, y, image);
            case "Obstacle":
                //int uix = (int) dataSnapshot.child("position").child("uix").getValue(Integer.class);
                //int uiy = (int) dataSnapshot.child("position").child("uiy").getValue(Integer.class);
                //Key key = new Key(finalI);
                int type = dataSnapshot.child("type").getValue(Integer.class);
                return new Obstacle(type,x ,y,image);
            case "TimeWasterAlien":
                return new TimeWasterAlien(x,y,image);
        }
        return null;
    }
}
