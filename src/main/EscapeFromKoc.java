package main;

import factory.PanelType;
import factory.ViewFactory;
import factory.ViewType;

import java.awt.*;

public class EscapeFromKoc {

    public static synchronized void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EscapeFromKoc.getInstance().startApp();
                    ;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static EscapeFromKoc instance;

    private IAppView authView;
    private IAppView gameView;

    private EscapeFromKoc() {
    }

    public static EscapeFromKoc getInstance() {
        if (instance == null) {
            instance = new EscapeFromKoc();
        }
        return instance;
    }

    private void startApp() {
        authView = ViewFactory.getInstance().createView(ViewType.AuthView);

        authView.showView(true);
        authView.getPanel(PanelType.Auth).showPanel(true);
    }

    public IAppView getView(ViewType type) {
        return switch (type) {
            case AuthView -> authView;
            case GameView -> gameView;
            default -> throw new IllegalArgumentException("No such kind of panel type");
        };
    }

    public void changeView(IAppView from, IAppView to) {
        if (from == null) {
            startApp();
        }
/*        else if (to == null) {
            exitApp(from);
        }*/
        else {
            from.showView(false);
            to.showView(true);
        }
    }
}
