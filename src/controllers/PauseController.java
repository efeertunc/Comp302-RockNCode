package controllers;

import factory.PanelType;
import factory.ViewFactory;
import factory.ViewType;
import main.EscapeFromKoc;
import main.IAppView;
import views.GameView;

public class PauseController {

	public void openHelp() {
		EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getCurPanel(),
												EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Help));
	}

	public void resumeGame() {
		EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getCurPanel(),
												EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Run));
	}

	public void openMenu() {
		IAppView newGame =  ViewFactory.getInstance().createView(ViewType.GameView);
		
		EscapeFromKoc.getInstance().changeView(EscapeFromKoc.getInstance().getView(ViewType.GameView),
												newGame);

		EscapeFromKoc.getInstance().setView(ViewType.GameView, newGame);
		
		EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getCurPanel(),
												EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Menu));
	}


    public void saveAndExit() {
		((GameView) EscapeFromKoc.getInstance().getView(ViewType.GameView)).getAuthController().saveGameClick(true);
		System.exit(0);

	}


}
