package controllers;

import factory.PanelType;
import factory.ViewFactory;
import factory.ViewType;
import main.EscapeFromKoc;
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
		EscapeFromKoc.getInstance().setView(ViewType.GameView, ViewFactory.getInstance().createView(ViewType.GameView));
		
		EscapeFromKoc.getInstance().changeView(EscapeFromKoc.getInstance().getView(ViewType.AuthView),
												EscapeFromKoc.getInstance().getView(ViewType.GameView));
		
		EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getCurPanel(),
												EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Menu));
	}
	
	

}
