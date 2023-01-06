package domain.controllers;

import helperComponents.Direction;
import domain.gameObjects.avatar.Avatar;
import domain.building.Building;
import domain.building.BuildingTracker;
import domain.SoundManager;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;

public class RunController {
	private Building currentBuilding;
	private Avatar avatar;
	private SoundManager sound = new SoundManager();

	public RunController()
	{
		currentBuilding = BuildingTracker.getBuildingList()
				.get(BuildingTracker.getCurrentIndex());
		currentBuilding.setKey();
		avatar = currentBuilding.setAvatar();
		currentBuilding.generateAlien();
	}

	public void pause() {
		EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getCurPanel(),
				EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Pause));
	}

	public void movePlayer(Direction.fourDir dir)
	{
		int avatarX = avatar.getPosition().getX();
		int avatarY = avatar.getPosition().getY();
		System.out.println("Direction is "+dir);
		switch (dir){
			case up:
				avatar.move(avatarX, avatarY - 1,currentBuilding);
				break;
			case right:
				avatar.move(avatarX + 1, avatarY, currentBuilding);
				break;
			case down:
				avatar.move(avatarX,avatarY+1 , currentBuilding);
				break;
			case left:
				avatar.move(avatarX-1,avatarY , currentBuilding);
				break;
			default:
				System.out.println("Error on moveplayer switch statement");
		}
	}

	public void searchKey(int x, int y)
	{
		int indexX = (x-42) / 48;
		int indexY = (y-97) / 48;
		if (indexX < 0 || indexX>16 || indexY <0 || indexY >16)
		{
			System.out.println("MouseClick is outside of the field");
			return;
		}
		if(avatar.searchKey(indexX , indexY , currentBuilding)){
			sound.playSoundEffect(0);
		}
	}

	public SoundManager getSound() {
		return sound;
	}
}
