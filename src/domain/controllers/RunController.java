package domain.controllers;

import helperComponents.Direction;
import domain.gameObjects.avatar.Avatar;
import domain.building.Building;
import domain.building.BuildingTracker;
import domain.SoundManager;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;
import models.Constants;
import panels.RunPanel;
import panels.RunningMap;

import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

public class RunController {
	private Building currentBuilding;
	private Avatar avatar;
	private double scale=1;

	public Avatar getAvatar() {
		return avatar;
	}

	private SoundManager sound = new SoundManager();

	public RunController() {
		initialize();
	}


	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}


	public void initialize() {
		currentBuilding = BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex());
		currentBuilding.setKey();
		avatar = currentBuilding.setAvatarToMap(new Avatar(3,60, 0, 0, 5));
		//currentBuilding.generateAlien();
	}

	public void pause() {
		EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getCurPanel(),
				EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Pause));
	}


	public void doAction(int keyCode) {
		avatar.doAction(keyCode);
	}


	public void nextLevel() {
		if (BuildingTracker.getCurrentIndex()!= 5) {
			BuildingTracker.setCurrentIndex(BuildingTracker.getCurrentIndex() + 1);

			currentBuilding = BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex());
			currentBuilding.setKey();
			currentBuilding.setAvatarToMap(avatar);

			try {
				((RunPanel) EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Run)).
						getRunningMap().openFile(Constants.FileConstants.fileList[BuildingTracker.getCurrentIndex()]);
			} catch (FileNotFoundException e) {


			}

			((RunPanel) EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Run)).
					getRunningMap().setMap_obj(currentBuilding.getMap_obj());
		}
		else{
			EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getCurPanel(),
													EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Win));
		}
	}


	public boolean searchKey(int x, int y)
	{
		int indexX = (int)(x/scale-27) / 48;
		int indexY = (int)(y/scale-90) / 48;
		System.out.println("Converted to "+ indexX+" , "+indexY);
		if (indexX < 0 || indexX>16 || indexY <0 || indexY >11)
		{
			System.out.println("MouseClick is outside of the field");
			return false;
		}
		if(avatar.searchKey(indexX , indexY , currentBuilding)){
			sound.playSoundEffect(0);
			return true;
		}
		return false;
	}

	public SoundManager getSound() {
		return sound;
	}


	public boolean searchPowerUp(int x, int y) {
		int indexX = (int)(x/scale-27) / 48;
		int indexY = (int)(y/scale-80) / 48;
		if (indexX < 0 || indexX>16 || indexY <0 || indexY >16)
		{
			System.out.println("MouseClick is outside of the field");
			return false;
		}
		System.out.println("converted :"+ indexY+ " "+indexX);
		if(avatar.searchPowerTile(indexX , indexY , currentBuilding)){
			sound.playSoundEffect(0);
			return true;
		}
		return false;
	}
}
