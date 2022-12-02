package main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class ObjectPanel {
	public JComboBox comboBox;
	JPanel panel;
	JButton btnNewButton_1;
	JButton btnNewButton_1_1;
	JButton btnNewButton;

	public ObjectPanel(JFrame frame) {
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(910, 0, 380, 650);
		frame.getContentPane().add(panel);
		initialize();
		buttonActionPerformed();

	}

	private void initialize() {
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(Color.ORANGE);
		panel.setDoubleBuffered(true);

		comboBox = new JComboBox();
		comboBox.setBounds(10, 233, 138, 22);
		panel.add(comboBox);
		comboBox.addItem("Chair 1");
		comboBox.addItem("Chair 2");
		comboBox.addItem("Table");

		JTextPane txtpnPleaseSelectThe = new JTextPane();
		txtpnPleaseSelectThe.setBackground(Color.ORANGE);
		txtpnPleaseSelectThe.setEditable(false);
		txtpnPleaseSelectThe.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		txtpnPleaseSelectThe.setText("Please select the type of the object.");
		txtpnPleaseSelectThe.setBounds(10, 166, 187, 33);
		panel.add(txtpnPleaseSelectThe);

		JTextPane txtpnPleaseSelectThe_1 = new JTextPane();
		txtpnPleaseSelectThe_1.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		txtpnPleaseSelectThe_1.setBackground(Color.ORANGE);
		txtpnPleaseSelectThe_1.setEditable(false);
		txtpnPleaseSelectThe_1.setText("Then, click on the map.");
		txtpnPleaseSelectThe_1.setBounds(10, 207, 187, 33);
		panel.add(txtpnPleaseSelectThe_1);

		btnNewButton = new JButton("Next Building!");
		btnNewButton.setBounds(70, 590, 120, 23);
		panel.add(btnNewButton);

		btnNewButton_1 = new JButton("Undo Last");
		btnNewButton_1.setBounds(10, 280, 119, 23);
		panel.add(btnNewButton_1);

		btnNewButton_1_1 = new JButton("Empty Map");
		btnNewButton_1_1.setBounds(10, 311, 119, 23);
		panel.add(btnNewButton_1_1);

	}

	public int getSelectedIndex() {
		int a = comboBox.getSelectedIndex();
		return a;
	}

	private void buttonActionPerformed() {

	}

}
