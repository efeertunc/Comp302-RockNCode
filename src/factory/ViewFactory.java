package factory;

import main.IAppView;
import views.AuthView;
import views.GameView;

public class ViewFactory {

    private static ViewFactory instance;

    private ViewFactory() {
    }

    public static ViewFactory getInstance() {
        if (instance == null) {
            instance = new ViewFactory();
        }
        return instance;
    }

    public IAppView createView(ViewType type) {
        return switch (type) {
            case AuthView -> new AuthView();
            case GameView -> new GameView();
            default -> throw new IllegalArgumentException("No such kind of panel type");
        };
    }
}
