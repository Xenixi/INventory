package inventory.gui.comp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import inventory.main.Colors;
import inventory.main.Fonts;
import inventory.main.item.Item;

public class ItemListElement extends JPanel{
	Item i = null;
	public ItemListElement(Item i) {
		this.i = i;
		this.setSize(new Dimension(945,100));
		this.setBackground(new Colors().getColor("RenBlue"));
		this.setBorder(BorderFactory.createEtchedBorder());
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.setFont(Fonts.getFont("CreteRound-Regular", 45f));
		g.drawString(i.getName() + " \\ " + i.getDesc() + " \\ " + i.getItemInstructions(), 30, 70);
		// ^^ no description currently -- 
		
		
	}
}
