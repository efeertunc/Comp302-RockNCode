package domain;

import HelperComponents.Position;

import java.util.ArrayList;
import java.util.Random;

public class Building {
	
	private int[][] map;
	ArrayList<Integer> xlist;
	ArrayList<Integer> ylist;
	ArrayList<Integer> objtype;
	ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	Position keyPos;
	Avatar avatar;
	private BuildingType type;
	private int minReq;

	public Building(int[][] map, ArrayList<Integer> xlist, ArrayList<Integer> ylist, ArrayList<Integer> objtype, BuildingType type, int minReq) {
		this.map = map;
		this.xlist = xlist;
		this.ylist = ylist;
		this.objtype = objtype;
		this.type = type;
		this.minReq = minReq;
	}
	
	
	public int[][] getMap() {
		return map;
	}
	public void setMap(int[][] map) {
		this.map = map;
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
	public void initializeMap(){
		for (int i = 0; i < xlist.size(); i++) {
			map[ylist.get(i) / 50][xlist.get(i) / 50] = objtype.get(i) + 1;
		}
	}
	public void setObstacles()
	{
		for (int i = 0 ; i< 17; i ++)
		{
			for (int j = 0 ; j <12; j ++)
			{
				if (map[j][i] != 0)
				{
					obstacles.add(new Obstacle(map[j][i],i,j));
				}
			}
		}
	}
	public void setKey()
	{
		Random rand = new Random();
		int selectedObstacle = rand.nextInt(obstacles.size());
		obstacles.get(selectedObstacle).generateKey(BuildingTracker.getCurrentIndex());
		keyPos = obstacles.get(selectedObstacle).position;
	}

	public Avatar setAvatar()
	{
		avatar = new Avatar(3,120, 0, 0);
		map[0][0] = avatar.getMatrixCode();
		return avatar;
	}

	public Obstacle checkObstacle(int x, int y)
	{
		for (int i = 0; i < obstacles.size() ; i ++)
		{
			if (obstacles.get(i).position.getX() == x && obstacles.get(i).position.getY() == y)
			{
				return obstacles.get(i);
			}
		}
		System.out.println("That is not an obstacle");
		return null;
	}
}
