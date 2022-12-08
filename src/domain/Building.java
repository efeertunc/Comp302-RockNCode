package domain;

import java.util.ArrayList;

public class Building {

	private int[][] map;
	ArrayList<Integer> xlist;
	ArrayList<Integer> ylist;
	ArrayList<Integer> objtype;
	private BuildingType type;

	private int minReq;

	public Building(int[][] map, ArrayList<Integer> xlist, ArrayList<Integer> ylist, ArrayList<Integer> objtype,
			BuildingType type, int minReq) {
		this.map = map;
		this.type = type;
		this.objtype = objtype;
		this.xlist = xlist;
		this.ylist = ylist;
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

	public void setYlist(ArrayList<Integer> Ylist) {
		this.ylist = Ylist;
	}

	public ArrayList<Integer> getXlist() {
		return xlist;
	}

	public ArrayList<Integer> getYlist() {
		return ylist;
	}

	public ArrayList<Integer> getObjtype() {
		return objtype;
	}

	public void setObjtype(ArrayList<Integer> objtype) {
		this.objtype = objtype;
	}

	public void setXlist(ArrayList<Integer> Xlist) {
		this.xlist = Xlist;
	}

	public void setObjType(ArrayList<Integer> ObjType) {
		this.objtype = ObjType;
	}

}
