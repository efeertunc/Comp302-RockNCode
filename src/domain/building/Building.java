package domain.building;

import domain.gameObjects.alien.Alien;
import domain.gameObjects.alien.TimeWasterAlien;
import domain.gameObjects.avatar.Avatar;
import domain.gameObjects.obstacle.Obstacle;
import helperComponents.Position;
import domain.gameObjects.*;
import main.EscapeFromKoc;
import domain.gameObjects.ObjectTile;

import java.util.ArrayList;
import java.util.Random;

public class Building {

	private ObjectTile[][] map_obj;
	private double time;
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
		this.time = (double) getNumofObstacles(map) * 5;
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

	public Avatar setAvatar()
	{

		for (int i = 0; i < 17; i++)
		{
			for (int j = 0; j <12; j++)
			{
				if (map_obj[j][i] instanceof Avatar)
				{
					avatar = (Avatar) map_obj[j][i];
					return avatar;
				}
			}
		}

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
		avatar = new Avatar(3,60, emptyTiles.get(selectedEmptyTile).getPosition().getX(), emptyTiles.get(selectedEmptyTile).getPosition().getY(), 5);
		map_obj[avatar.getPosition().getY()][avatar.getPosition().getX()] = avatar;
		return avatar;
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

	public void generateAlien()
	{
		for (int i = 0; i < 17; i++)
		{
			for (int j = 0; j <12; j++)
			{
				if (map_obj[j][i] instanceof Alien)
				{
					TimeWasterAlien alien = new TimeWasterAlien(map_obj[j][i].getPosition().getX(),map_obj[j][i].getPosition().getY(), 7);
					map_obj[alien.getPosition().getY()][alien.getPosition().getX()] = alien;
					return;
				}
			}
		}

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
		TimeWasterAlien alien = new TimeWasterAlien(emptyTiles.get(selectedEmptyTile).getPosition().getX(),emptyTiles.get(selectedEmptyTile).getPosition().getY(), 7);
		map_obj[alien.getPosition().getY()][alien.getPosition().getX()] = alien;
	}

	public void deleteKey()
	{
		((Obstacle)map_obj[keyPos.getY()][keyPos.getX()]).deleteKey();
		keyPos=null;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double intervalTime) {
		time -=  intervalTime/1000000000;
		if (time < 0) {
			time = 0;
		}
	}
}
