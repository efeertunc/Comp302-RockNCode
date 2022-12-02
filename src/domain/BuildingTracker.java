package domain;

import java.util.ArrayList;

public class BuildingTracker {
	
	private ArrayList<Building> buildingList;
	
	private int currentIndex;
	

	public BuildingTracker() {
		this.buildingList = new ArrayList<Building>();
		
		int[][] map = new int [12][17];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				map[i][j] = 0;
			}
		}
		
		Building omerB = new Building(map, BuildingType.OMER, 5);
		buildingList.add(omerB);
		Building caseB = new Building(map, BuildingType.CASE, 5);
		buildingList.add(caseB);
		Building sosB = new Building(map, BuildingType.SOS, 5);
		buildingList.add(sosB);
		Building scieB = new Building(map, BuildingType.SCIE, 5);
		buildingList.add(scieB);
		Building engB = new Building(map, BuildingType.ENG, 5);
		buildingList.add(engB);
		Building snaB = new Building(map, BuildingType.SNA, 5);
		buildingList.add(snaB);	
		
		this.currentIndex = 0;
		
	}


	public ArrayList<Building> getBuildingList() {
		return buildingList;
	}


	public void setBuildingList(ArrayList<Building> buildingList) {
		this.buildingList = buildingList;
	}


	public int getCurrentIndex() {
		return currentIndex;
	}


	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}
	
	
	
	

	

}
