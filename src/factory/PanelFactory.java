package factory;

import main.IAppView;
import main.IPanel;
import panels.*;

public class PanelFactory {

    private static PanelFactory instance;

    private PanelFactory() {
    }

    public static PanelFactory getInstance() {
        if (instance == null) {
            instance = new PanelFactory();
        }
        return instance;
    }

    public IPanel createPanel(PanelType type, IAppView appView) {
        return switch (type) {
            case ForgotPass -> new ForgotPasswordPanel(appView);
            case Register -> new RegisterPanel(appView);
            case Login -> new LoginPanel(appView);
            case Menu -> new MenuPanel(appView);
            case Build -> new BuildPanel(appView);
            case Run -> new RunPanel(appView);
            case Pause -> new PausePanel(appView);
            case Help -> new HelpPanel(appView);
            case Win -> new WinGamePanel(appView);
            case GameOver ->new GameOverPanel(appView);
            default -> throw new IllegalArgumentException("No such kind of panel type");
        };
    }
}
