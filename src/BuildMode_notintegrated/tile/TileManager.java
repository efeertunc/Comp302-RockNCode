package tile;

import javax.imageio.ImageIO;

import main.BuildPanel;

public class TileManager {
	BuildPanel bp;
	// BuildController controller;
	public ObjectTile[] objects = new ObjectTile[3];

	public TileManager(BuildPanel bp) {
		super();
		this.bp = bp;
		// this.controller = controller;

		getTileImage();

	}

	public void getTileImage() {
		try {
			objects[0] = new ObjectTile();
			objects[0].image = (ImageIO.read(getClass().getResourceAsStream("chair.png")));

			objects[1] = new ObjectTile();
			objects[1].image = (ImageIO.read(getClass().getResourceAsStream("chair_200.png")));

			objects[2] = new ObjectTile();
			objects[2].image = (ImageIO.read(getClass().getResourceAsStream("table1.png")));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
