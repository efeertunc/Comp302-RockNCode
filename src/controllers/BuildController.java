package controllers;

import domain.Building;
import domain.BuildingTracker;
import domain.BuildingType;
import domain.SoundManager;
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

	public void startRun() {
		EscapeFromKoc.getInstance().getView(ViewType.GameView).createrunPanel();
		EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getCurPanel(),
				EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Run));
		EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Run).showPanel(true);


	}

} 
