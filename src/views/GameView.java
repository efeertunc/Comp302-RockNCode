package views;

import factory.PanelFactory;
import factory.PanelType;
import main.IAppView;
import main.IPanel;

import javax.swing.*;

public class GameView implements IAppView {

    private JFrame frame;

    private IPanel menuPanel;

    public GameView() {
        putFrametoGame();
        menuPanel = PanelFactory.getInstance().createPanel(PanelType.Menu, this);
    }


    @Override
    public void putFrametoGame() {
        this.frame = new JFrame();
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLayout(null);
        this.frame.setVisible(false);
        this.frame.setBounds(100, 100, 450, 382);
        this.frame.setLocationRelativeTo(null);
    }

    @Override
    public void showView(Boolean show) {
        frame.setVisible(show);
    }

    @Override
    public IPanel getPanel(PanelType panel) {
        return switch (panel) {
            case Menu -> menuPanel;
            default -> throw new IllegalArgumentException("No such kind of panel type");
        };
    }

    @Override
    public JFrame getFrame() {
        return this.frame;
    }


}
