package inventory.gui.comp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import inventory.main.Colors;
import inventory.main.Fonts;
import inventory.main.Project;

public class ProjectListElement extends JPanel {

	public ProjectListElement(Project p) {

		this.setSize(new Dimension(275, 20));
		JTextField nameLabel = new JTextField();
		nameLabel.setFont(Fonts.getFont("OpenSans-semibold", 11f));
		this.setLayout(new BorderLayout());
		this.add(nameLabel, BorderLayout.WEST);
		this.setBackground(new Colors().getColor("ButtonsMain"));
		nameLabel.setSize(200, 17);
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setText("   " + p.getName());
		nameLabel.setBackground(new Colors().getColor("BackGrayToneUp"));
		nameLabel.setBorder(null);
		nameLabel.setEditable(false);
		nameLabel.setPreferredSize(new Dimension(210, 20));
		nameLabel.setHorizontalAlignment(JTextField.CENTER);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BorderLayout());
		this.add(buttonsPanel, BorderLayout.CENTER);
		buttonsPanel.setBackground(new Colors().getColor("ButtonsMain"));

		// buttons:

		JButton delButton = new JButton(), renButton = new JButton(), settingsButton = new JButton();

		buttonsPanel.add(delButton, BorderLayout.WEST);
		buttonsPanel.add(renButton, BorderLayout.CENTER);
		buttonsPanel.add(settingsButton, BorderLayout.EAST);

		delButton.setPreferredSize(new Dimension(20, 20));
		renButton.setPreferredSize(new Dimension(20, 20));
		settingsButton.setPreferredSize(new Dimension(20, 20));
		delButton.setBorder(BorderFactory.createEtchedBorder());
		renButton.setBorder(BorderFactory.createEtchedBorder());
		settingsButton.setBorder(BorderFactory.createEtchedBorder());

		delButton.setBackground(new Colors().getColor("ButtonsMain"));
		renButton.setBackground(new Colors().getColor("ButtonsMain"));
		settingsButton.setBackground(new Colors().getColor("ButtonsMain"));

		JPanel bufferPanel = new JPanel();
		bufferPanel.setPreferredSize(new Dimension(5, 20));
		this.add(bufferPanel, BorderLayout.EAST);
		bufferPanel.setBackground(new Colors().getColor("ButtonsMain"));

		delButton.setText("-");
		delButton.setFont(Fonts.getFont("CreteRound-Italic", 28f));
		delButton.setForeground(Color.red);

		renButton.setFont(Fonts.getFont("CreteRound-Italic", 18f));
		renButton.setForeground(new Colors().getColor("RenBlue"));
		renButton.setText("~");
		settingsButton.setFont(Fonts.getFont("CreteRound-Italic", 24f));
		settingsButton.setText("*");
		settingsButton.setForeground(new Colors().getColor("Settings"));

	}
}
