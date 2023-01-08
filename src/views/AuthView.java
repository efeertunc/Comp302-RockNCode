package views;

import domain.controllers.AuthController;
import factory.PanelFactory;
import factory.PanelType;
import main.IAppView;
import main.IPanel;

import javax.swing.*;

public class AuthView implements IAppView {
    private final AuthController authController;
    private JFrame frame;
    private final IPanel authPanel;
    private final IPanel CreateAccount;
    private final IPanel ForgotPass;


    public AuthView() {
        authController = new AuthController();
        putFrametoGame();
        authPanel = PanelFactory.getInstance().createPanel(PanelType.Login, this);
        CreateAccount = PanelFactory.getInstance().createPanel(PanelType.Register, this);
        ForgotPass = PanelFactory.getInstance().createPanel(PanelType.ForgotPass, this);
    }

    @Override
    public void putFrametoGame() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(false);
        frame.setBounds(100, 100, 450, 382);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void showView(Boolean show) {
        frame.setVisible(show);
    }

    @Override
    public IPanel getPanel(PanelType panel) {
        return switch (panel) {
            case Login -> authPanel;
            case Register ->  CreateAccount;
            case ForgotPass ->  ForgotPass;
            default -> throw new IllegalArgumentException("No such kind of panel type");
        };
    }

    @Override
    public JFrame getFrame() {
        return frame;
    }

    public AuthController getAuthController() {
        return authController;
    }
}
