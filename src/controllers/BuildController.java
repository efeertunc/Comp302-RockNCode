package controllers;

import domain.Building;
import domain.BuildingTracker;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;

public class BuildController {

	private BuildingTracker buildingList;

	public BuildController() {
		buildingList = new BuildingTracker();
	}

	public BuildingTracker getBuildingList() {
		return buildingList;
	}

	public void setBuildingList(BuildingTracker buildingList) {
		this.buildingList = buildingList;
	}

	public void openHelpScreen() {
		EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getCurPanel(),
				EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Help));
	}

	public Building nextBuilding() {

		return buildingList.getBuildingList().get(buildingList.getCurrentIndex() + 1);
	}

	public Building SetnextBuilding() {
		buildingList.setCurrentIndex(buildingList.getCurrentIndex() + 1);

		return buildingList.getBuildingList().get(buildingList.getCurrentIndex() + 1);
	}

	public void startRun() {
		// Burada olu�tural� m�?
		EscapeFromKoc.getInstance().getView(ViewType.GameView).createrunPanel();
		EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getCurPanel(),
				EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Run));

	}

}
