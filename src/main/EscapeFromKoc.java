package main;

import Utilities.DBManager.DBManager;
import Utilities.DBManager.DatabaseAdapter;
import factory.PanelType;
import factory.ViewFactory;
import factory.ViewType;
import javax.swing.JFrame;
import java.awt.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EscapeFromKoc {

    public static synchronized void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EscapeFromKoc.getInstance().startApp();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static EscapeFromKoc instance;
    private DatabaseAdapter databaseAdapter;

    private IAppView authView;
    private IAppView gameView;

    private IPanel curPanel;
    private IPanel oldPanel;

    private EscapeFromKoc() {
    }

    public static EscapeFromKoc getInstance() {
        if (instance == null) {
            instance = new EscapeFromKoc();
        }
        return instance;
    }

    private void startApp() {
        if (!checkInternetConnection()) {
            System.out.println("Lütfen internete bağlı olduğunuzdan emin olup tekrar deneyin.");
        } else {
            databaseAdapter = new DatabaseAdapter(DBManager.getInstance());
            databaseAdapter.connect();

            authView = ViewFactory.getInstance().createView(ViewType.AuthView);
            gameView = ViewFactory.getInstance().createView(ViewType.GameView);

            authView.showView(true);
            authView.getPanel(PanelType.Login).showPanel(true);
            setCurPanel(authView.getPanel(PanelType.Login));
            gameView.getFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
    }

    public void changeView(IAppView from, IAppView to) {
        if (from == null) {
            startApp();
        } else if (to == null) {
            exitApp(from);
        } else {
            from.showView(false);
            to.showView(true);
        }
    }

    public void changePanel(IPanel from, IPanel to) {
        if (from == null) {
            to.showPanel(true);
        } else if (to == null) {
            from.showPanel(false);
        } else {
            from.showPanel(false);
            to.showPanel(true);

        }
        setCurPanel(to);
        setOldPanel(from);
    }

    private void exitApp(IAppView view) {
        view.showView(false);
        view.getFrame().dispose();
        System.exit(0);
    }

    public boolean checkInternetConnection() {
        boolean status = false;
        Socket sock = new Socket();
        InetSocketAddress address = new InetSocketAddress("www.google.com", 80);

        try {
            sock.connect(address, 3000);
            if (sock.isConnected()) status = true;
        } catch (Exception e) {
            status = false;
        } finally {
            try {
                sock.close();
            } catch (Exception e) {
            }
        }
        return status;
    }


    public IAppView getView(ViewType type) {
        return switch (type) {
            case AuthView -> authView;
            case GameView -> gameView;
            default -> throw new IllegalArgumentException("No such kind of panel type");
        };
    }

    public IAppView setView(ViewType type, IAppView appView) {
        return switch (type) {
            case AuthView -> authView = appView;
            case GameView -> gameView = appView;
            default -> throw new IllegalArgumentException("No such kind of panel type");
        };
    }

    public IPanel getOldPanel() {
        return oldPanel;
    }


    public void setOldPanel(IPanel oldPanel) {
        this.oldPanel = oldPanel;
    }

    public IPanel getCurPanel() {
        return curPanel;
    }


    public void setCurPanel(IPanel curPanel) {
        this.curPanel = curPanel;
    }


    public DatabaseAdapter getDatabaseAdapter() {
        return databaseAdapter;
    }

}
