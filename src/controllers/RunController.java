package controllers;

import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;

public class RunController {

	public void pause() {
		EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getCurPanel(),
				EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Pause));
	}

}
