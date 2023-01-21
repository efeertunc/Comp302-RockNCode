package domain.controllers;

import factory.PanelType;
import factory.ViewFactory;
import factory.ViewType;
import main.EscapeFromKoc;
import main.IAppView;
import views.AuthView;
import views.GameView;

import java.util.concurrent.TimeUnit;

public class PauseController {

	/**
	 * This method is called when the user clicks the help button
	 */
	public void openHelp() {
		EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getCurPanel(),
												EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Help));
	}

	/**
	 * This method is called when the user clicks the resume button
	 */
	public void resumeGame() {
		EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getCurPanel(),
												EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Run));
	}

	/**
	 * This method is called when the user clicks the menu button
	 */
	public void openMenu() {
		IAppView newGame =  ViewFactory.getInstance().createView(ViewType.GameView);
		
		EscapeFromKoc.getInstance().changeView(EscapeFromKoc.getInstance().getView(ViewType.GameView),
												newGame);

		EscapeFromKoc.getInstance().setView(ViewType.GameView, newGame);
		
		EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getCurPanel(),
												EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Menu));
	}


	/**
	 * This method is called when the user clicks the exit button
	 */
	public void saveAndExit() {
		((AuthView) EscapeFromKoc.getInstance().getView(ViewType.AuthView)).getAuthController().saveGameClick(true);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.exit(0);

	}


}
