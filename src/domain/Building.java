package domain;

import HelperComponents.Position;
import main.EscapeFromKoc;
import objects.ObjectTile;

import java.util.ArrayList;
import java.util.Random;

public class Building {

	private ObjectTile[][] map_obj;
	ArrayList<Integer> xlist;
	ArrayList<Integer> ylist;
	ArrayList<Integer> objtype;
	Position keyPos;
	Avatar avatar;
	private BuildingType type;
	private int minReq;

	public Building(int[][] map, ArrayList<Integer> xlist, ArrayList<Integer> ylist, ArrayList<Integer> objtype, BuildingType type, int minReq) {
		this.xlist = xlist;
		this.ylist = ylist;
		this.objtype = objtype;
		this.type = type;
		this.minReq = minReq;
	}
	public void setMap_obj(ObjectTile[][] map) {
		this.map_obj = map;
	}
	public ObjectTile[][] getMap_obj() {
		return map_obj ;
	}
	public ObjectTile[][] getMap() {
		return map_obj;
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
	public void setMinReq(int minReq) {
		this.minReq = minReq;
	}

	public ArrayList<Integer> getXlist() {
		return xlist;
	}

	public void setXlist(ArrayList<Integer> xlist) {
		this.xlist = xlist;
	}

	public ArrayList<Integer> getYlist() {
		return ylist;
	}

	public void setYlist(ArrayList<Integer> ylist) {
		this.ylist = ylist;
	}

	public ArrayList<Integer> getObjtype() {
		return objtype;
	}

	public void setObjtype(ArrayList<Integer> objtype) {
		this.objtype = objtype;
	}
	public void setKey()
	{
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
		Random rand = new Random();
		int selectedObstacle = rand.nextInt(obstacles.size());
		obstacles.get(selectedObstacle).generateKey(BuildingTracker.getCurrentIndex());
		keyPos = obstacles.get(selectedObstacle).position;
		obstacles.clear();
	}

	public Avatar setAvatar()
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
		avatar = new Avatar(3,120, emptyTiles.get(selectedEmptyTile).position.getX(), emptyTiles.get(selectedEmptyTile).position.getY(), EscapeFromKoc.getInstance().tm.objects[5].image);
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
		TimeWasterAlien alien = new TimeWasterAlien(emptyTiles.get(selectedEmptyTile).position.getX(),emptyTiles.get(selectedEmptyTile).position.getY(), EscapeFromKoc.getInstance().tm.objects[7].image);
		map_obj[alien.position.getY()][alien.position.getX()] = alien;
	}

	public void deleteKey()
	{
		((Obstacle)map_obj[keyPos.getY()][keyPos.getX()]).deleteKey();
		keyPos=null;
	}
}
