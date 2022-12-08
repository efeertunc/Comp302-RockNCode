package objects;

import javax.imageio.ImageIO;

public class TileManager {

    private final int originalTileSize = 16; // 16x16 tile
    private final int scale = 3;
    private int tileSize = originalTileSize * scale; // 48x48 tile //her parça

    public ObjectTile[] objects = new ObjectTile[4];

    public TileManager() {
        super();

        getTileImage();

    }

    public void getTileImage() {

        try {
            objects[0] = new ObjectTile();
            objects[0].image = (ImageIO.read(getClass().getResource("/visual/shelve.png")));

            objects[1] = new ObjectTile();
            objects[1].image = (ImageIO.read(getClass().getResource("/visual/chair_200.png")));

            objects[2] = new ObjectTile();
            objects[2].image = (ImageIO.read(getClass().getResource("/visual/bin.png")));

            objects[3] = new ObjectTile();
            objects[3].image = (ImageIO.read(getClass().getResource("/visual/table1.png")));

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
