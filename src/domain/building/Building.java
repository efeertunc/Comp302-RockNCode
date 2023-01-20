package domain.building;

import domain.gameObjects.alien.Alien;
import domain.gameObjects.alien.blind.BlindAlien;
import domain.gameObjects.alien.shooter.ShooterAlien;
import domain.gameObjects.alien.timeWaster.TimeWasterAlien;
import domain.gameObjects.avatar.Avatar;
import domain.gameObjects.obstacle.Obstacle;
import domain.gameObjects.powerUps.PowerUpTile;
import domain.gameObjects.powerUps.PowerUpTypes;
import helperComponents.Direction;
import helperComponents.Position;
import domain.gameObjects.*;
import domain.gameObjects.ObjectTile;

import java.util.ArrayList;
import java.util.Random;

public class Building {

	private ObjectTile[][] map_obj;
	private double time;
	private double powerUpTime;
	private Position keyPos;
	private Avatar avatar;
	private BuildingType type;
	private int minReq;
	private boolean isKeyAvailable = false;

	public Building(int[][] map, BuildingType type, int minReq) {
		this.type = type;
		this.minReq = minReq;
	}

	// sets the map and the time ,according to number of obstacles in the map,
	// when the map is set from database, or when the map is created by the user during the game.
	public void setMap_obj(ObjectTile[][] map) {
		this.map_obj = map;

		for (int i = 0; i < 17; i++) {
			for (int j = 0; j <12; j++) {
				if (map_obj[j][i] instanceof Avatar) {
					avatar = (Avatar) map_obj[j][i];
					this.time = avatar.getCurrentTime();
					System.out.println("Time avatardan alındı");
					this.powerUpTime = avatar.getCurrentTime();
					return;
				}
			}
		}
		System.out.println("Time objeden alındı");
		this.time = (double) getNumofObstacles(map) * 5;
		this.powerUpTime = this.time;
	}

	// returns the number of obstacles in the map
	public int getNumofObstacles(ObjectTile[][] map) {
		int num = 0;

		for (ObjectTile[] objectTiles : map) {
			for (int j = 0; j < map[0].length; j++) {
				if (objectTiles[j] instanceof Obstacle) {
					num++;
				}
			}
		}

		return num;
	}

	public ObjectTile[][] getMap_obj() {
		return map_obj ;
	}

	public void setMap(ObjectTile[][] map) {
		this.map_obj = map;
	}

	public BuildingType getType() {
		return type;
	}

	public void setType(BuildingType type) {
		this.type = type;
	}

	public int getMinReq() {
		return minReq;
	}

	public void setKey()
	{
		if (!isKeyAvailable){
			for (int i = 0; i < 17; i++)
			{
				for (int j = 0; j <12; j++)
				{
					if (map_obj[j][i] instanceof Obstacle)
					{
						if (((Obstacle) map_obj[j][i]).getKey() != null){
							System.out.println("Key databaseden oluşturuldu");
							keyPos = map_obj[j][i].getPosition();
							isKeyAvailable = true;
							return;
						}
					}
				}
			}
			isKeyAvailable = true;
			setKey2();
		}else {
			setKey2();
		}
	}

	public void setKey2(){
		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
		for (int i = 0; i < 17; i++)
		{
			for (int j = 0; j <12; j++)
			{
				if (map_obj[j][i] instanceof Obstacle)
				{
					obstacles.add((Obstacle) map_obj[j][i]);
				}
			}
		}
		System.out.println("Key databaseden oluşturulmadı");
		Random rand = new Random();
		int selectedObstacle = rand.nextInt(obstacles.size());
		obstacles.get(selectedObstacle).generateKey(BuildingTracker.getCurrentIndex());
		keyPos = obstacles.get(selectedObstacle).getPosition();
		obstacles.clear();
	}
	public Avatar getAvatar()
	{
		return this.avatar;
	}
	public Position getKeyPos() {
		return keyPos;
	}

	public Position setKeyPos(Position keyPos) {
		return this.keyPos = keyPos;
	}

	public Avatar setAvatarToMap(Avatar eskiAvatar) {

		for (int i = 0; i < 17; i++) {
			for (int j = 0; j <12; j++) {
				if (map_obj[j][i] instanceof Avatar) {
					avatar = (Avatar) map_obj[j][i];
					return avatar;
				}
			}
		}

		ArrayList<EmptyTile> emptyTiles = new ArrayList<EmptyTile>();

		for (int i = 0; i < 17; i++) {
			for (int j = 0; j <12; j++) {
				if (map_obj[j][i] instanceof EmptyTile) {
					emptyTiles.add((EmptyTile) map_obj[j][i]);
				}
			}
		}
		Random rand = new Random();
		int selectedEmptyTile = rand.nextInt(emptyTiles.size());
		int emptyX = emptyTiles.get(selectedEmptyTile).getPosition().getX();
		int emptyY = emptyTiles.get(selectedEmptyTile).getPosition().getY();
		Position position = new Position(emptyX, emptyY);

		eskiAvatar.setPosition(position);
		eskiAvatar.setHasKey(false);
		eskiAvatar.notifyAvatarObserver();
		eskiAvatar.setImage(5);
		eskiAvatar.setCurrentTime(this.time);

		map_obj[position.getY()][position.getX()] = eskiAvatar;

		avatar = eskiAvatar;

		return eskiAvatar;
	}

	public Obstacle checkObstacle(int x, int y)
	{
		if (map_obj[y][x] instanceof Obstacle)
		{
			return (Obstacle) map_obj[y][x];
		}
		System.out.println("That is not an obstacle");
		return null;
	}

	public PowerUpTile checkPowerTile(int x, int y) {
		if (map_obj[y][x] instanceof PowerUpTile)
		{
			return (PowerUpTile) map_obj[y][x];
		}
		System.out.println("That is not an power up");
		return null;
	}

	public void generateAlien()
	{
		ArrayList<EmptyTile> emptyTiles = new ArrayList<EmptyTile>();
		for (int i = 0; i < 17; i++)
		{
			for (int j = 0; j <12; j++)
			{
				if (map_obj[j][i] instanceof EmptyTile)
				{
					emptyTiles.add((EmptyTile) map_obj[j][i]);
				}
			}
		}
		Random rand = new Random();
		int selectedEmptyTile = rand.nextInt(emptyTiles.size());
		int alienType = rand.nextInt(3);
		Alien alien;
		int emptyX = emptyTiles.get(selectedEmptyTile).getPosition().getX();
		int emptyY = emptyTiles.get(selectedEmptyTile).getPosition().getY();
		switch (alienType) {
			case 0 ->
					alien = new TimeWasterAlien(emptyX, emptyY, 7);
			case 1 ->
					alien = new ShooterAlien(emptyX, emptyY, 9);
			case 2 ->
					alien = new BlindAlien(emptyX, emptyY, 10);
			default -> {
				alien = null;
				System.out.println("Alien is not generated!");
			}
		}
		map_obj[alien.getPosition().getY()][alien.getPosition().getX()] = alien;
	}

	public void deleteKey()
	{
		((Obstacle)map_obj[keyPos.getY()][keyPos.getX()]).deleteKey();
		keyPos=null;
	}

	public void deletePowerTile(int x, int y) {
		map_obj[y][x] = new EmptyTile(x,y,4);
	}

	public double getTime() {
		return time;
	}

	public void updateTime(double intervalTime) {
		time -=  intervalTime/1000000000;
		if (time < 0) {
			time = 0;
		}
	}

	public void updatePowerUpTime(double intervalTime) {
		powerUpTime -=  intervalTime/1000000000;
		if (powerUpTime < 0) {
			powerUpTime = 0;
		}
	}

	public void setTime(int powerUpTime) {
		time += powerUpTime;
	}

	public void generatePowerTile() {
		ArrayList<EmptyTile> emptyTileList = new ArrayList<EmptyTile>();
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j <12; j++) {
				if (map_obj[j][i] instanceof EmptyTile)  {
					emptyTileList.add((EmptyTile) map_obj[j][i]);
				}
			}
		}
		Random rand = new Random();
		int selectedEmptyTile = rand.nextInt(emptyTileList.size());

		int powerUpType = rand.nextInt(5);
		ObjectTile powerUpTile;
		int emptyX = emptyTileList.get(selectedEmptyTile).getPosition().getX();
		int emptyY = emptyTileList.get(selectedEmptyTile).getPosition().getY();
		switch(powerUpType){
			case 0 ->
					powerUpTile = new PowerUpTile(PowerUpTypes.EXTRA_TIME, emptyX, emptyY, 11);
			case 1 ->
					powerUpTile = new PowerUpTile(PowerUpTypes.EXTRA_LIFE, emptyX, emptyY, 12);
			case 2 ->
				powerUpTile = new PowerUpTile(PowerUpTypes.HINT, emptyX, emptyY, 25);
			case 3 ->
					powerUpTile = new PowerUpTile(PowerUpTypes.VEST, emptyX, emptyY, 26);
			case 4 ->
					powerUpTile = new PowerUpTile(PowerUpTypes.BOTTLE, emptyX, emptyY, 27);
			default -> {
				powerUpTile = null;
				System.out.println("Power up is not generated!");
			}
		}
		map_obj[powerUpTile.getPosition().getY()][powerUpTile.getPosition().getX()] = powerUpTile;
	}

	public double getPowerUpTime() {
		return powerUpTime;
	}

	public void setPowerUpTime(double powerUpTime) {
		this.powerUpTime = powerUpTime;
	}

	public Position findBottleLastPos(Position avatarPos, Direction.fourDir dir) {
		Position bottlePos = avatarPos;
		Position newPos = bottlePos;

		while (newPos.getX() >= 0 && newPos.getX() < 17 && newPos.getY() >= 0 && newPos.getY() < 12) {
			if (!(map_obj[newPos.getY()][newPos.getX()] instanceof Obstacle)) {
				bottlePos = newPos;
				switch (dir) {
					case up -> newPos = new Position(bottlePos.getX(), bottlePos.getY() - 1);
					case down -> newPos = new Position(bottlePos.getX(), bottlePos.getY() + 1);
					case left -> newPos = new Position(bottlePos.getX() - 1, bottlePos.getY());
					case right -> newPos = new Position(bottlePos.getX() + 1, bottlePos.getY());
				}
			}
			else {
				break;
			}
		}
		return bottlePos;
	}


}
