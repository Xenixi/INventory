package inventory.gui.comp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import inventory.main.Colors;

public class TagMiniPanel extends JPanel{
	String text = "";
	public TagMiniPanel(String text, Color tagColor) {
		this.setBackground(null);
		this.setSize(new Dimension(45,20));
		this.text = text;
		this.setLayout(new BorderLayout());
		
		JTextField field = new JTextField(text) {
			Color mainColor = tagColor;
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				this.setForeground(mainColor);
				RoundRectangle2D rr2d = new RoundRectangle2D.Float(0,3,44,15,15,15);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setColor(mainColor);
				g2d.draw(rr2d);
				
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
