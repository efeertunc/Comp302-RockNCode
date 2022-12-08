package panels;

import factory.ViewType;
import main.EscapeFromKoc;
import main.IAppView;
import main.IPanel;
import views.AuthView;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ForgotPass implements IPanel {
    private JTextField Username;

    private JTextField Hint;

    private JPanel panel;


    public ForgotPass(IAppView appView) {

        putPaneltoFrame(appView.getFrame());
        initialize();
        design();
    }
    /*private void forgot() {
        ((AuthView) EscapeFromKoc.getInstance().getView(ViewType.AuthView)).getAuthController().forgotPassword(
                Username.getText().trim(), );
    }

     */
    @Override
    public void showPanel(Boolean show)  {
        panel.setVisible(show);
    }

    @Override
    public void initialize() {
        Username = new JTextField();
        Hint = new JTextField();
    }

    @Override
    public void design() {
        JLabel header = new JLabel("ESCAPE FROM KOC");
        panel.add(header);
        header.setBounds(66, 20, 314, 61);
        header.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
        header.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(Username);
        Username.setBounds(66, 151, 130, 26);

        Username.setColumns(10);
        Username.setText("Type Username");
        Username.setForeground(new Color(142, 144, 145));

        Username.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Username.getText().equals("Type Username")) {
                    Username.setText("");
                    Username.setForeground(new Color(0, 0, 0));
                }
            }
        });

        Username.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (Username.getText().equals("")) {
                    Username.setText("Type Username");
                    Username.setForeground(new Color(142, 144, 145));
                }
            }
        });
    }

    @Override
    public void putPaneltoFrame(JFrame frame) {
        panel = new JPanel();
        panel.setVisible(false);
        panel.setBounds(6, 6, 438, 342);
        panel.setLayout(null);
        panel.setBorder(new LineBorder(Color.BLACK));
        frame.add(panel);
    }
}


