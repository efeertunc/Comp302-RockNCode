package panels;

import domain.controllers.BuildController;
import main.IAppView;
import main.IPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class WinGamePanel extends JPanel implements IPanel {

    public WinGamePanel(IAppView appView) {
        putPaneltoFrame(appView.getFrame());
        initialize();
        design();
    }
    @Override
    public void showPanel(Boolean show) {
        this.setVisible(show);

    }

    @Override
    public void initialize() {

    }

    @Override
    public void design() {
        JLabel BigLabel = new JLabel("YOU WON THE GAME !!");
        BigLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        BigLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        BigLabel.setHorizontalAlignment(SwingConstants.CENTER);
        BigLabel.setBounds(450, 200, 300, 300);
        this.add(BigLabel);
    }

    @Override
    public void putPaneltoFrame(JFrame frame) {
        frame.add(this);
       this.setVisible(false);
        this.setBounds(0, 0, 1400, 800);
        this.setLayout(null);
        this.setBorder(new LineBorder(Color.BLACK));
    }
}
