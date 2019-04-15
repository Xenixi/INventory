package inventory.gui.comp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import inventory.interfaces.SettingsMenuCallable;
import inventory.main.Colors;
import inventory.main.Fonts;

public class SettingsMenuEntry extends JPanel{
	JTextField label = new JTextField();
	public SettingsMenuEntry(String title, Point loc, SettingsMenuCallable onClickCall) {
		this.setLocation(loc);
		
		
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(147,20));
		this.add(label, BorderLayout.CENTER);
		label.setText(title);
		label.setFont(Fonts.getFont("OpenSans-regular", 13f));
		label.setBackground(new Colors().getColor("ButtonsMain"));
		label.setForeground(new Colors().getColor("lighttext"));
		label.setBorder(null);
		label.setEditable(false);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		SettingsMenuEntry sme = this;
		label.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				onClickCall.onClick(sme);
			}
		});
	}
	public void setSelected(boolean selected) {
		if(selected) {
			label.setBackground(new Colors().getColor("ButtonsMainSelected"));
		} else {
			label.setBackground(new Colors().getColor("ButtonsMain"));
		}
	}
}
