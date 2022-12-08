package domain;

import java.util.ArrayList;

public class BuildingTracker {

	private static ArrayList<Building> buildingList;

	private static int currentIndex;

	public BuildingTracker() {
		this.buildingList = new ArrayList<Building>();

		int[][] map = new int[12][17];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				map[i][j] = 0;
			}
		}
		ArrayList<Integer> xlist = new ArrayList<Integer>();
		ArrayList<Integer> ylist = new ArrayList<Integer>();
		ArrayList<Integer> objtype = new ArrayList<Integer>();

		// change the requirements

		Building omerB = new Building(map, xlist, ylist, objtype, BuildingType.OMER, 5);
		buildingList.add(omerB);
		Building caseB = new Building(map, xlist, ylist, objtype, BuildingType.CASE, 1);
		buildingList.add(caseB);
		Building sosB = new Building(map, xlist, ylist, objtype, BuildingType.SOS, 1);
		buildingList.add(sosB);
		Building scieB = new Building(map, xlist, ylist, objtype, BuildingType.SCIE, 1);
		buildingList.add(scieB);
		Building engB = new Building(map, xlist, ylist, objtype, BuildingType.ENG, 1);
		buildingList.add(engB);
		Building snaB = new Building(map, xlist, ylist, objtype, BuildingType.SNA, 2);
		buildingList.add(snaB);

		this.currentIndex = 0;

	}

	public static ArrayList<Building> getBuildingList() {
		return buildingList;
	}

	public void setBuildingList(ArrayList<Building> buildingList) {
		this.buildingList = buildingList;
	}

	public static int getCurrentIndex() {
		return currentIndex;
	}

	public static void setCurrentIndex(int currentIndex1) {
		currentIndex = currentIndex1;
	}

}
