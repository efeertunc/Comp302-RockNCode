package domain.building;

import java.util.ArrayList;

public class BuildingTracker {
	
	private static ArrayList<Building> buildingList;
	private static int currentIndex;

	public BuildingTracker() {
		System.out.println("buildTracker");
		buildingList = new ArrayList<Building>();
		
		int[][] map = new int [12][17];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				map[i][j] = 0;
			}
		}
		Building omerB = new Building(new int [12][17], BuildingType.OMER, 5);
		buildingList.add(0,omerB);
		Building caseB = new Building(new int [12][17], BuildingType.CASE, 1);
		buildingList.add(1,caseB);
		Building sosB = new Building(new int [12][17], BuildingType.SOS, 1);
		buildingList.add(2,sosB);
		Building scieB = new Building(new int [12][17], BuildingType.SCIE, 1);
		buildingList.add(3,scieB);
		Building engB = new Building(new int [12][17], BuildingType.ENG, 1);
		buildingList.add(4,engB);
		Building snaB = new Building(new int [12][17], BuildingType.SNA, 2);
		buildingList.add(5,snaB);
		
		currentIndex = 0;
		
	}

	public static ArrayList<Building> getBuildingList() {
		return buildingList;
	}

	public static void initialize() {
		Building omerB = new Building(new int [12][17],  BuildingType.OMER, 5);
		buildingList.add(0,omerB);
		Building caseB = new Building(new int [12][17],  BuildingType.CASE, 1);
		buildingList.add(1,caseB);
		Building sosB = new Building(new int [12][17], BuildingType.SOS, 1);
		buildingList.add(2,sosB);
		Building scieB = new Building(new int [12][17], BuildingType.SCIE, 1);
		buildingList.add(3,scieB);
		Building engB = new Building(new int [12][17], BuildingType.ENG, 1);
		buildingList.add(4,engB);
		Building snaB = new Building(new int [12][17], BuildingType.SNA, 2);
		buildingList.add(5,snaB);

		currentIndex = 0;
	}

	public static int getCurrentIndex() {
		return currentIndex;
	}

	public static void setCurrentIndex(int currentIndex1) {
		currentIndex = currentIndex1;
	}

}
