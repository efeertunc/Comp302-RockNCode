package domain;

import java.util.ArrayList;

public class Building {
	
	private int[][] map;
	ArrayList<Integer> xlist;
	ArrayList<Integer> ylist;
	ArrayList<Integer> objtype;
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
}
