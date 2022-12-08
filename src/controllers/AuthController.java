package controllers;

import Models.Account;
import Utilities.DBManager.DBObserver;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;
import main.IPanel;
import panels.AuthPanel;

public class AuthController implements DBObserver {

    public AuthController() {
        EscapeFromKoc.getInstance().getDatabaseAdapter().subscribeAuthObserver(this);
    }

    public void loginClick(String username, String password) {
        EscapeFromKoc.getInstance().getDatabaseAdapter()
                .loginUser(username, password);
    }

    public void registerClick(String username, String firstPassword, String secondPassword, String hint) {
        EscapeFromKoc.getInstance().getDatabaseAdapter()
                .registerUser(username, firstPassword, secondPassword, hint);
    }

    public void goregister(){

        EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.Auth),
                EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.CreateAccount));

    }
/*
    public void forgotPassword(String username, String hint, String firstPassword, String secondPassword){
       // EscapeFromKoc.getInstance().getDatabaseAdapter()
                .registerUser(username, hint, firstPassword, secondPassword);
        //EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.Auth),
          //      EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.ForgotPass));

    }



 */
    public void forgotPassword(){
        EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.Auth),
           EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.ForgotPass));
    }

    @Override
    public void loginAccepted(Account user, String response) {

        EscapeFromKoc.getInstance().changeView(EscapeFromKoc.getInstance().getView(ViewType.AuthView),
                                                EscapeFromKoc.getInstance().getView(ViewType.GameView));
        EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.Auth),
                                EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Menu));
    }
    public void goBack() {
        EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.CreateAccount),
                EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.Auth));
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
