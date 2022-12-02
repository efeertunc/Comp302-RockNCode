package domain;

public class Building {
	
	private int[][] map;
	private BuildingType type;
	private int minReq;
	
	
	public Building(int[][] map, BuildingType type, int minReq) {
		this.map = map;
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
	
	

}
