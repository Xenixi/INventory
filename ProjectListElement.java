package inventory.gui.comp;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import inventory.main.Colors;
import inventory.main.Fonts;
import inventory.main.Project;

public class ProjectListElement extends JPanel{
	
	public ProjectListElement(Project p) {
		this.setSize(new Dimension(275, 20));
		JLabel nameLabel = new JLabel();
		nameLabel.setFont(Fonts.getFont("OpenSans-semibold", 14f));
		this.setLayout(new BorderLayout());
		this.add(nameLabel, BorderLayout.WEST);
		this.setBackground(new Colors().getColor("ButtonsMain"));
		this.setBorder(BorderFactory.createEtchedBorder());
		//fixy this stuff
		
		
		
	}
}
