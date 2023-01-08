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

public class ForgotPasswordPanel implements IPanel {
    private IAppView authView;
    private JTextField username;
    private JPasswordField newPassword;
    private JPasswordField confirmPass;

    private JTextField userHint;

    private JPanel panel;
    private JButton forgotPasswordButton;
    private JButton prevPage;
    private JLabel info;



    public ForgotPasswordPanel(IAppView appView) {
        this.authView = appView;
        putPaneltoFrame(appView.getFrame());
        initialize();
        design();
    }

    private void forgotPassword() {
        ((AuthView) authView).getAuthController().forgotPasswordClick(username.getText(), userHint.getText(), newPassword.getText(), confirmPass.getText());
    }


    @Override
    public void showPanel(Boolean show)  {
        panel.setVisible(show);
    }

    @Override
    public void initialize() {
        username = new JTextField();
        userHint = new JTextField();
        newPassword = new JPasswordField();
        confirmPass = new JPasswordField();
        prevPage = new JButton("<-");
        forgotPasswordButton = new JButton("Change Password");
        forgotPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                forgotPassword();
            }
        });
        prevPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((AuthView) authView).getAuthController().goBackFromForgotPassword();
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

        panel.add(prevPage);
        prevPage.setBounds(10, 10, 50, 30);

        panel.add(forgotPasswordButton);
        forgotPasswordButton.setBounds(153, 237, 159, 29);

        panel.add(username);
        username.setBounds(66, 151, 130, 26);

        username.setColumns(10);
        username.setText("Type Username");
        username.setForeground(new Color(142, 144, 145));

        username.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (username.getText().equals("Type Username")) {
                    username.setText("");
                    username.setForeground(new Color(0, 0, 0));
                }
            }
        });

        username.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (username.getText().equals("")) {
                    username.setText("Type Username");
                    username.setForeground(new Color(142, 144, 145));
                }
            }
        });
        panel.add(userHint);
        userHint.setColumns(10);
        userHint.setBounds(66, 189, 130, 26);
        userHint.setText("Type Hint");
        userHint.setForeground(new Color(142, 144, 145));
        userHint.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (userHint.getText().equals("Type Hint")) {
                    userHint.setText("");
                    userHint.setForeground(new Color(0, 0, 0));
                }
            }
        });
        userHint.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (userHint.getText().equals("")) {
                    userHint.setText("Type Hint");
                    userHint.setForeground(new Color(142, 144, 145));
                }
            }
        });


        char passwordChar = newPassword.getEchoChar();
        newPassword.setEchoChar((char) 0);
        newPassword.setText("Enter password");
        newPassword.setForeground(new Color(142, 144, 145));
        newPassword.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (newPassword.getText().equals("Enter password")) {
                    newPassword.setText("");
                    newPassword.setEchoChar(passwordChar);
                    newPassword.setForeground(new Color(0, 0, 0));
                }
            }
        });
        newPassword.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (newPassword.getText().equals("")) {
                    newPassword.setEchoChar((char) 0);
                    newPassword.setText("Enter password");
                    newPassword.setForeground(new Color(142, 144, 145));

                }
            }
        });
        panel.add(newPassword);
        newPassword.setBounds(250, 151, 130, 26);

        panel.add(confirmPass);
        confirmPass.setBounds(250, 189, 130, 26);
        confirmPass.setEchoChar((char) 0);
        confirmPass.setText("Confirm password");
        confirmPass.setForeground(new Color(142, 144, 145));
        confirmPass.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (confirmPass.getText().equals("Confirm password")) {
                    confirmPass.setText("");
                    confirmPass.setEchoChar(passwordChar);
                    confirmPass.setForeground(new Color(0, 0, 0));
                }
            }
        });
        confirmPass.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (confirmPass.getText().equals("")) {
                    confirmPass.setEchoChar((char) 0);
                    confirmPass.setText("Confirm password");
                    confirmPass.setForeground(new Color(142, 144, 145));

                }
            }
        });

        info = new JLabel();
        info.setHorizontalAlignment(SwingConstants.CENTER);
        info.setBounds(6, 310, 438, 16);
        panel.add(info);
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


