package domain.controllers;

import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;
import views.GameView;

public class MenuController {

	public void exit() {
		EscapeFromKoc.getInstance().changeView(EscapeFromKoc.getInstance().getView(ViewType.GameView),
												null);		
	}

	public void openHelp() {
		EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getCurPanel(), 
												EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Help));
	}

	public void startBuildingMode() {
		EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getCurPanel(), 
				EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Build));
		
	}


	public void startRunMode() {

		((GameView) EscapeFromKoc.getInstance().getView(ViewType.GameView)).createrunPanel();

		EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getCurPanel(),
				EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Run));

		EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Run).showPanel(true);
	}
}
