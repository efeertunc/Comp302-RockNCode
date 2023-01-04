package domain;

import domain.gameObjects.ObjectTile;

public class TileManager {

    private final int originalTileSize = 16; // 16x16 tile
    private final int scale = 3;
    private int tileSize = originalTileSize * scale; // 48x48 tile //her parça

    public ObjectTile[] objects = new ObjectTile[11];

    public TileManager() {
        super();

        getTileImage();

    }

    public void getTileImage() {

        try {
            objects[0] = new ObjectTile();
            objects[0].setImage(0);

            objects[1] = new ObjectTile();
            objects[1].setImage(1);

            objects[2] = new ObjectTile();
            objects[2].setImage(2);

            objects[3] = new ObjectTile();
            objects[3].setImage(3);

            objects[4] = new ObjectTile();
            objects[4].setImage(4); //empty

            objects[5] = new ObjectTile();
            objects[5].setImage(5); //playerNormal

            objects[6] = new ObjectTile();
            objects[6].setImage(6); //playerWithKey

            objects[7] = new ObjectTile();
            objects[7].setImage(7);

            objects[8] = new ObjectTile();
            objects[8].setImage(8);

            objects[9] = new ObjectTile();
            objects[9].setImage(9);

            objects[10] = new ObjectTile();
            objects[10].setImage(10);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getTileSize() {
        return tileSize;
    }

    public ObjectTile[] getObjects() {
        return objects;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public void setObjects(ObjectTile[] objects) {
        this.objects = objects;
    }

}
