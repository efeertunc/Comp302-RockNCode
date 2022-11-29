package controllers;

import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;

public class AuthController {

    public void login() {
        EscapeFromKoc.getInstance().changeView(EscapeFromKoc.getInstance().getView(ViewType.AuthView),
                                                EscapeFromKoc.getInstance().getView(ViewType.GameView));

        //EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.Auth),
        //                                        EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Menu));
    }
}
