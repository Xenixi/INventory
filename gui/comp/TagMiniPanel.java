package inventory.gui.comp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import inventory.main.Colors;
import inventory.main.Project;
import inventory.main.util.dev.DevConsole;

public class TagMiniPanel extends JPanel{
	String text = "";
	String fullText = "";
	boolean selected = false;
	public TagMiniPanel(Project p, String text, Color tagColor) {
		this.setBackground(null);
		this.setSize(new Dimension(45,20));
		this.fullText = text;
		this.text = text.length() <= 5 ? text : (text.substring(0, Math.min(text.length(), 5)) + "*");
		this.setLayout(new BorderLayout());
		
		JTextField field = new JTextField(this.text) {
			Color mainColor = tagColor;
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				this.setForeground((selected ? Color.white: mainColor));
				RoundRectangle2D rr2d = new RoundRectangle2D.Float(0,3,44,15,15,15);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setColor((selected ? Color.white: mainColor));
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
		field.setToolTipText(fullText);
		field.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				DevConsole.printOut("Tag - " + (selected ? "deselected" : "selected")  + ": '" + fullText + "'");
				p.setTagSelected(text ,!selected);
				selected = !selected;
				repaint();
				
			}
		});
		
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}
}
