
package panels;

import controllers.AuthController;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;
import main.IPanel;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import controllers.AuthController;
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
public class CreateAccount implements IPanel {
    private JTextField newUsername;
    private JTextField userHint;
    private JPasswordField newPassword;
    private JPasswordField confirmPass;
    private JPanel panel;
    private JButton registerButton;
    private JButton prevPage;
    public CreateAccount(IAppView appView) {
        putPaneltoFrame(appView.getFrame());
        initialize();
        design();
    }
    private void createAccount() {
        ((AuthView) EscapeFromKoc.getInstance().getView(ViewType.AuthView)).getAuthController().registerClick(
                newUsername.getText().trim(), newPassword.getText().trim(), confirmPass.getText().trim(),
                userHint.getText().trim());
    }
    @Override
    public void showPanel(Boolean show)  {
        panel.setVisible(show);
    }

    @Override
    public void initialize() {
        newUsername = new JTextField();
        userHint = new JTextField();
        newPassword = new JPasswordField();
        confirmPass = new JPasswordField();
        prevPage = new JButton("<-");
        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createAccount();
            }
        });
        prevPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((AuthView) EscapeFromKoc.getInstance().getView(ViewType.AuthView)).getAuthController().goBack();
            }
        });

    }

    @Override
    public void design() {
        JLabel header = new JLabel("ESCAPE FROM KOC");
        panel.add(header);
        header.setBounds(66, 20, 314, 61);
        header.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
        header.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(prevPage);
        prevPage.setBounds(10, 10, 30, 30);

        panel.add(registerButton);
        registerButton.setBounds(153, 237, 159, 29);

        panel.add(newUsername);
        newUsername.setBounds(66, 151, 130, 26);

        newUsername.setColumns(10);
        newUsername.setText("Type Username");
        newUsername.setForeground(new Color(142, 144, 145));

        newUsername.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (newUsername.getText().equals("Type Username")) {
                    newUsername.setText("");
                    newUsername.setForeground(new Color(0, 0, 0));
                }
            }
        });

        newUsername.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (newUsername.getText().equals("")) {
                    newUsername.setText("Type Username");
                    newUsername.setForeground(new Color(142, 144, 145));
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
        panel.add(newPassword);
        newPassword.setBounds(250, 151, 130, 26);
        newPassword.setEchoChar((char) 0);
        char passwordChar = newPassword.getEchoChar();
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
