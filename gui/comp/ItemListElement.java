package inventory.gui.comp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import inventory.main.Colors;
import inventory.main.Fonts;
import inventory.main.item.Item;

public class ItemListElement extends JPanel {
	Item i = null;

	// JButton settingsButton = new JButton();
	public ItemListElement(Item i) {
		this.i = i;
		this.setSize(new Dimension(600, 50));
		this.setBackground(new Colors().getColor("ButtonsMain"));
		this.setBorder(BorderFactory.createEtchedBorder());

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.setFont(Fonts.getFont("CreteRound-Regular", 25f));
		g.drawString(i.getName() + " \\ " + i.getDesc() + " \\ " + i.getItemInstructions(), 60, 30);
		g.setColor(new Color(255,255,0));
		g.drawRect(50, 0, 500, 60);
		// ^^ no description currently --
		/*
		 * this.addMouseListener(new MouseAdapter() {
		 * 
		 * @Override public void mouseExited(MouseEvent e) { setBackground(new
		 * Colors().getColor("ButtonsMain")); }
		 * 
		 * @Override public void mouseEntered(MouseEvent e) { setBackground(new
		 * Colors().getColor("InGreen")); }
		 * 
		 * 
		 * });
		 */
	}
}
