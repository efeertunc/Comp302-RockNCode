package controllers;

import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;

public class HelpController {

	public void back() {
		EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Help), EscapeFromKoc.getInstance().getOldPanel());
	}

}
