package inventory.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class GUI extends JFrame {
	public GUI() {
		beginGUI();
	}

	public void beginGUI() {
		System.out.println("GUI staring");
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				if (ConfigEdit.getValue("FirstTime").equalsIgnoreCase("true")) {

					JFrame menuFrame = new JFrame();
					menuFrame.setTitle("INventory Elite Edition - First Time Setup");
					menuFrame.setSize(600, 400);
					menuFrame.setLocationRelativeTo(null);
					menuFrame.setVisible(true);
					menuFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
					ImageIcon icon = new ImageIcon("logo.png");
					menuFrame.setIconImage(icon.getImage());
					JPanel mainPanel = new JPanel();
					menuFrame.add(mainPanel);
					menuFrame.setResizable(false);

					JLabel titleLabel = new JLabel();
					titleLabel.setSize(600, 40);

					titleLabel.setFont(Fonts.getFont("Lalezar-regular", 25f));

					titleLabel.setText("Welcome to INventory!");
					mainPanel.setLayout(null);

					mainPanel.add(titleLabel);

					titleLabel.setLocation(162, 50);

					mainPanel.setBackground(new Colors().getColor("BackGray"));
					titleLabel.setForeground(new Colors().getColor("InGreen"));

					//// stuffs ;-)

					JLabel instructionLabel = new JLabel();
					instructionLabel.setText("It looks like this is your first time! Let's get you setup.");
					JLabel instructionLabel2 = new JLabel();
					instructionLabel2.setText("Please enter your name: ");

					mainPanel.add(instructionLabel);
					mainPanel.add(instructionLabel2);

					instructionLabel.setSize(450, 20);
					instructionLabel2.setSize(200, 20);
					instructionLabel.setFont(Fonts.getFont("Lalezar-regular", 17f));
					instructionLabel2.setFont(Fonts.getFont("Lalezar-regular", 17f));
					instructionLabel.setForeground(new Colors().getColor("InGreen"));
					instructionLabel2.setForeground(new Colors().getColor("InGreen"));

					instructionLabel.setLocation(92, 100);
					instructionLabel2.setLocation(200, 135);

					JTextField nameField = new JTextField();

					mainPanel.add(nameField);
					nameField.setSize(250, 30);
					nameField.setLocation(163, 160);
					nameField.setBackground(new Colors().getColor("BackField"));
					nameField.setBorder(BorderFactory.createEtchedBorder(new Colors().getColor("Border"),
							new Colors().getColor("BackField")));
					nameField.setForeground(new Colors().getColor("FieldText"));
					nameField.setHorizontalAlignment(SwingConstants.CENTER);
					nameField.setFont(Fonts.getFont("CreteRound-Regular", 15f));

					JLabel instructionLabel3 = new JLabel();
					instructionLabel3.setLocation(140, 205);
					instructionLabel3.setSize(400, 20);
					instructionLabel3.setText("Please enter your company/organization");
					instructionLabel3.setForeground(new Colors().getColor("InGreen"));
					instructionLabel3.setFont(Fonts.getFont("Lalezar-regular", 17f));
					mainPanel.add(instructionLabel3);

					JTextField compField = new JTextField();
					compField.setLocation(163, 230);
					compField.setSize(250, 30);
					compField.setBorder(BorderFactory.createEtchedBorder(new Colors().getColor("Border"),
							new Colors().getColor("BackField")));
					compField.setForeground(new Colors().getColor("FieldText"));
					compField.setHorizontalAlignment(SwingConstants.CENTER);
					compField.setBackground(new Colors().getColor("BackField"));
					compField.setFont(Fonts.getFont("CreteRound-Regular", 15f));
					mainPanel.add(compField);

					JButton okButton = new JButton();
					okButton.setSize(90, 30);
					okButton.setLocation(240, 295);
					mainPanel.add(okButton);

					okButton.setBackground(new Colors().getColor("ButtonBack"));
					okButton.setBorder(BorderFactory.createEtchedBorder());
					okButton.setForeground(new Colors().getColor("InGreen"));
					okButton.setText("OK");
					okButton.setFont(Fonts.getFont("lalezar-regular", 16f));

					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							ConfigEdit.setValue("Username", nameField.getText());
							ConfigEdit.setValue("Organization", compField.getText());
							menuFrame.setVisible(false);
							menuFrame.dispose();
							System.out.println("First time setup completed!");
							ConfigEdit.setValue("FirstTime", "False");
							//
							// call a different method with the main GUI
							new INventoryGUI();
							return;
							//
							
						}
					});
				} else {
					System.out.println("FALSE NOT OPENING");
					//
					// call a different method with the main GUI
					new INventoryGUI();
					return;
					//

				}

			}
		});

	}
}
