package inventory.gui.comp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import inventory.main.Colors;

public class TagMiniPanel extends JPanel{
	String text = "";
	public TagMiniPanel(String text) {
		this.setBackground(null);
		this.setSize(new Dimension(45,20));
		this.text = text;
		this.setLayout(new BorderLayout());
		
		JTextField field = new JTextField(text) {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(new Colors().getColor("BlueGreenTextMain"));
				g.drawRect(0, 2, 44, 18);
			}
		};
		field.setHorizontalAlignment(SwingConstants.CENTER);
		field.setEditable(false);
		this.add(field, BorderLayout.CENTER);
		field.setBackground(null);
		field.setBorder(null);
		field.setForeground(new Colors().getColor("BlueGreenTextMain"));
		field.setCaretPosition(0);
		
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}
}
