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
        BigLabel.setBounds(450, 400, 350, 30);
        this.add(BigLabel);


        Icon imgIcon1 = new ImageIcon(this.getClass().getResource("/visual/confet.gif"));
        JLabel label1 = new JLabel(imgIcon1);
        JLabel label2 = new JLabel(imgIcon1);
        JLabel label3 = new JLabel(imgIcon1);
        JLabel label4 = new JLabel(imgIcon1);
        label1.setBounds(150, 200, 300, 300); // You can use your own values
        label2.setBounds(850, 200, 300, 300); // You can use your own values
        label3.setBounds(250, 50, 300, 300); // You can use your own values
        label4.setBounds(650, 50, 300, 300); // You can use your own values
        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
    }

    @Override
    public void putPaneltoFrame(JFrame frame) {
        frame.add(this);
       this.setVisible(false);
        this.setBounds(0, 0, 1400, 800);
        this.setLayout(null);
        this.setBorder(new LineBorder(Color.BLACK));
        this.setOpaque(false);
    }
}
