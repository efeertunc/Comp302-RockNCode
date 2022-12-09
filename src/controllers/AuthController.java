package controllers;

import Models.Account;
import Utilities.DBManager.DBObserver;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;
import panels.ForgotPasswordPanel;
import panels.LoginPanel;
import panels.RegisterPanel;

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

    public void forgotPasswordClick(String username, String hint, String firstPassword, String secondPassword){
        EscapeFromKoc.getInstance().getDatabaseAdapter()
                .forgotPassword(username, hint, firstPassword, secondPassword);
        EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.Login),
                EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.ForgotPass));

    }

    public void goRegisterPanel(){

        EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.Login),
                EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.Register));

    }

    public void goForgotPasswordPanel(){
        EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.Login),
           EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.ForgotPass));
    }

    public void goBackFromRegister() {
        EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.Register),
                EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.Login));
    }

    public void goBackFromForgotPassword() {
        EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.ForgotPass),
                EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.Login));
    }

    @Override
    public void loginAccepted(Account user, String response) {
        ((LoginPanel) EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.Login)).setInfo(response + user.getUsername());

        EscapeFromKoc.getInstance().changeView(EscapeFromKoc.getInstance().getView(ViewType.AuthView),
                EscapeFromKoc.getInstance().getView(ViewType.GameView));
        EscapeFromKoc.getInstance().changePanel(EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.Login),
                EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Menu));
    }
    @Override
    public void loginRejected(String response) {
        ((LoginPanel) EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.Login)).setInfo(response);
    }

    @Override
    public void registerAccepted(Account user, String response) {
        ((RegisterPanel) EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.Register)).setInfo(response + user.getUsername());
    }

    @Override
    public void registerRejected(String response) {
        ((RegisterPanel) EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.Register)).setInfo(response);
    }

    @Override
    public void changePasswordAccepted(String response) {
        ((ForgotPasswordPanel) EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.ForgotPass)).setInfo(response);
    }

    @Override
    public void changePasswordRejected(String response) {
        ((ForgotPasswordPanel) EscapeFromKoc.getInstance().getView(ViewType.AuthView).getPanel(PanelType.ForgotPass)).setInfo(response);
    }
}
