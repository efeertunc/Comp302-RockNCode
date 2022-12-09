package controllers;

import HelperComponents.Direction;
import domain.Avatar;
import domain.Building;
import domain.BuildingTracker;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;

public class RunController {
	Building currentBuilding;
	Avatar avatar;

	public RunController()
	{
		currentBuilding = BuildingTracker.getBuildingList()
				.get(BuildingTracker.getCurrentIndex());

		currentBuilding.setObstacles();
		currentBuilding.setKey();
		avatar = currentBuilding.setAvatar();
	}

	public void pause() {
		EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getCurPanel(),
				EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Pause));
	}

	public void movePlayer(Direction.fourDir dir)
	{
		int avatarX = avatar.getPosition().getX();
		int avatarY = avatar.getPosition().getY();
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
		avatar.searchKey(indexX , indexY , currentBuilding);
	}

}
