package controllers;

import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;

public class MenuController {

	public void exit() {
		EscapeFromKoc.getInstance().changeView(EscapeFromKoc.getInstance().getView(ViewType.GameView), null);
	}

	public void openHelp() {
		EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getCurPanel(),
				EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Help));
	}

	public void startBuildingMode() {

		EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getCurPanel(),
				EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Build));

	}

}
