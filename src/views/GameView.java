package views;

import domain.controllers.AuthController;
import factory.PanelFactory;
import factory.PanelType;
import main.IAppView;
import main.IPanel;
import panels.RunPanel;
import panels.RunningMap;
import panels.WinGamePanel;

import javax.swing.*;

public class GameView implements IAppView {

    private JFrame frame;

    private IPanel menuPanel;
    private IPanel buildPanel;
    private IPanel runPanel;
    private IPanel helpPanel;
    private IPanel pausePanel;
    private IPanel winGamePanel;

    public GameView() {
        putFrametoGame();
        menuPanel = PanelFactory.getInstance().createPanel(PanelType.Menu, this);
        buildPanel = PanelFactory.getInstance().createPanel(PanelType.Build, this);
        helpPanel = PanelFactory.getInstance().createPanel(PanelType.Help, this);
        pausePanel = PanelFactory.getInstance().createPanel(PanelType.Pause, this);
        winGamePanel = PanelFactory.getInstance().createPanel(PanelType.Win, this);
    }

    public void createrunPanel() {
        runPanel = PanelFactory.getInstance().createPanel(PanelType.Run, this);
        ((RunPanel) runPanel).getRunningMap().startThread();
    }

    @Override
    public void putFrametoGame() {
        this.frame = new JFrame();
        this.frame.setResizable(false);
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
            case Build -> buildPanel;
            case Run -> runPanel;
            case Pause -> pausePanel;
            case Help -> helpPanel;
            case Win -> winGamePanel;
            default -> throw new IllegalArgumentException("No such kind of panel type");
        };
    }

    @Override
    public JFrame getFrame() {
        return this.frame;
    }


}
