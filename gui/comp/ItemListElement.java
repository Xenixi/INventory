package inventory.gui.comp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import inventory.main.Colors;
import inventory.main.Fonts;
import inventory.main.item.Item;

public class ItemListElement extends JPanel{
	Item i = null;
	//JButton settingsButton = new JButton();
	public ItemListElement(Item i) {
		this.i = i;
		this.setSize(new Dimension(600,50));
		this.setBackground(new Colors().getColor("ButtonsMain"));
		this.setBorder(BorderFactory.createEtchedBorder());
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(new Colors().getColor("ButtonsMain"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(new Colors().getColor("InGreen"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.setFont(Fonts.getFont("CreteRound-Regular", 25f));
		g.drawString(i.getName() + " \\ " + i.getDesc() + " \\ " + i.getItemInstructions(), 30, 30);
		// ^^ no description currently -- 
		
		
	}
}
