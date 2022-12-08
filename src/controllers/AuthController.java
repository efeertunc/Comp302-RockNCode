package controllers;

import Models.Account;
import Utilities.DBManager.DBObserver;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;
import main.IPanel;
import panels.AuthPanel;

public class AuthController implements DBObserver {
	private IPanel panel;

	public AuthController(IPanel panel) {
		this.panel = panel;
		// EscapeFromKoc.getInstance().getDatabaseAdapter().subscribeAuthObserver(this);
	}

	public void loginClick(String username, String password) {
		// EscapeFromKoc.getInstance().getDatabaseAdapter().loginUser(username,
		// password);
		// S�LLL
		EscapeFromKoc.getInstance().changeView(EscapeFromKoc.getInstance().getView(ViewType.AuthView),
				EscapeFromKoc.getInstance().getView(ViewType.GameView));
		EscapeFromKoc.getInstance().changePanel(
				EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.Auth),
				EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Menu));
	}

	public void registerClick(String username, String firstPassword, String secondPassword, String hint) {
		EscapeFromKoc.getInstance().getDatabaseAdapter().registerUser(username, firstPassword, secondPassword, hint);
	}

	@Override
	public void loginAccepted(Account user, String response) {
		((AuthPanel) panel).setInfo(response + user.getUsername());

		EscapeFromKoc.getInstance().changeView(EscapeFromKoc.getInstance().getView(ViewType.AuthView),
				EscapeFromKoc.getInstance().getView(ViewType.GameView));
		EscapeFromKoc.getInstance().changePanel(
				EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.Auth),
				EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Menu));
	}

	@Override
	public void loginRejected(String response) {
		((AuthPanel) panel).setInfo(response);
	}

	@Override
	public void registerAccepted(Account user, String response) {
		((AuthPanel) panel).setInfo(response + user.getUsername());
	}

	@Override
	public void registerRejected(String response) {
		((AuthPanel) panel).setInfo(response);
	}
}
