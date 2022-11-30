package controllers;

import Models.Account;
import Utilities.DBManager.DBObserver;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;

public class AuthController  implements DBObserver {

    public AuthController() {

        EscapeFromKoc.getInstance().getDatabaseAdapter().subscribeAuthObserver(this);
    }

    public void loginClick(String username, String password) {
        EscapeFromKoc.getInstance().getDatabaseAdapter()
                .loginUser(username, password);
    }

    @Override
    public void loginAccepted(Account user, String response) {
        EscapeFromKoc.getInstance().changeView(EscapeFromKoc.getInstance().getView(ViewType.AuthView), EscapeFromKoc.getInstance().getGameView());
        EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Auth), EscapeFromKoc.getInstance().getGameView().getPanel(PanelType.Menu));
    }

    @Override
    public void loginRejected(String response) {

    }

    @Override
    public void registerAccepted(Account user, String response) {

    }

    @Override
    public void registerRejected(String response) {

    }
}
