package panels;

import factory.ViewType;
import main.EscapeFromKoc;
import main.IAppView;
import main.IPanel;
import views.AuthView;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LoginPanel implements IPanel {
    private IAppView authView;
    private JPanel panel;
    private JButton forgotPass;
    private JTextField loginUsername;
    private JPasswordField loginPasswordField;
    private JButton loginButton;
    private JLabel info;
    private JButton createAccountButton;


    public LoginPanel(IAppView appView) {
        this.authView = appView;
        putPaneltoFrame(appView.getFrame());
        initialize();
        design();
    }

    private void login() {
        ((AuthView) authView).getAuthController().loginClick(
                loginUsername.getText().trim(), loginPasswordField.getText().trim());
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

    @Override
    public void showPanel(Boolean show) {
        panel.setVisible(show);
    }

    @Override
    public void initialize() {
        forgotPass = new JButton("Forgot Password");

        loginUsername = new JTextField();
        loginPasswordField = new JPasswordField();
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                login();

            }
        });

        createAccountButton = new JButton("Create New Account");
        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((AuthView) authView).getAuthController().goRegisterPanel();
            }
        });

        forgotPass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((AuthView) authView).getAuthController().goForgotPasswordPanel();
            }
        });


    }

    public void setInfo(String text) {
        info.setText(text);
    }

    @Override
    public void design() {

        JLabel header = new JLabel("ESCAPE FROM KOC");
        panel.add(header);
        header.setBounds(66, 20, 314, 61);
        header.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
        header.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(forgotPass);
        forgotPass.setBounds(160, 244, 139, 29);

        panel.add(loginUsername);
        loginUsername.setColumns(10);
        loginUsername.setBounds(66, 113, 130, 26);
        loginUsername.setText("Type Username");
        loginUsername.setForeground(new Color(142, 144, 145));
        loginUsername.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (loginUsername.getText().equals("Type Username")) {
                    loginUsername.setText("");
                    loginUsername.setForeground(new Color(0, 0, 0));
                }
            }
        });

        loginUsername.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (loginUsername.getText().equals("")) {
                    loginUsername.setText("Type Username");
                    loginUsername.setForeground(new Color(142, 144, 145));
                }
            }
        });


        char passwordChar = loginPasswordField.getEchoChar();
        loginPasswordField.setEchoChar((char) 0);
        loginPasswordField.setText("Enter password");
        loginPasswordField.setForeground(new Color(142, 144, 145));
        loginPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (loginPasswordField.getText().equals("Enter password")) {
                    loginPasswordField.setText("");
                    loginPasswordField.setEchoChar(passwordChar);
                    loginPasswordField.setForeground(new Color(0, 0, 0));
                }
            }
        });
        loginPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (loginPasswordField.getText().equals("")) {
                    loginPasswordField.setEchoChar((char) 0);
                    loginPasswordField.setText("Enter password");
                    loginPasswordField.setForeground(new Color(142, 144, 145));

                }
            }
        });
        panel.add(loginButton);
        loginButton.setBounds(185, 171, 79, 29);

        panel.add(createAccountButton);
        createAccountButton.setBounds(153, 207, 159, 29);

        panel.add(loginPasswordField);
        loginPasswordField.setBounds(250, 113, 130, 26);

        info = new JLabel();
        info.setHorizontalAlignment(SwingConstants.CENTER);
        info.setBounds(6, 310, 438, 16);
        panel.add(info);



    }



}
