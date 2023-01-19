package panels;

import main.IAppView;
import main.IPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.net.URL;

public class GameOverPanel extends JPanel implements IPanel {

    public GameOverPanel(IAppView appView) {
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
        Icon imgIcon1 = new ImageIcon(this.getClass().getResource("/visual/gameover.gif"));
        JLabel label1 = new JLabel(imgIcon1);
        label1.setBounds(150, 70, 800, 500);
        this.add(label1);
    }
    @Override
    public void putPaneltoFrame(JFrame frame) {

        frame.add(this);
        this.setVisible(false);
        this.setBounds(0, 0, 1400, 800);
        this.setLayout(null);
        this.setBorder(new LineBorder(Color.BLACK));
        this.setOpaque(false);

        URL imageURL = getClass().getResource("/visual/school.png");
        ImageIcon icon = null;
        if (imageURL != null) {
            icon = new ImageIcon(imageURL);
        }
        JLabel background = new JLabel(icon);
        background.setOpaque(false);
        background.setBounds(0, 0, 1000, 710);
        this.add(background);
        //background.setVisible(false);
    }
}
