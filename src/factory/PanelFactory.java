package factory;

import main.IAppView;
import main.IPanel;
import panels.AuthPanel;

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
            case Auth -> new AuthPanel(appView);
            default -> throw new IllegalArgumentException("No such kind of panel type");
        };
    }
}
