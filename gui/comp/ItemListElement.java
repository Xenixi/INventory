package inventory.gui.comp;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import inventory.main.Colors;
import inventory.main.item.Item;

public class ItemListElement extends JPanel{
	Item i = null;
	public ItemListElement(Item i) {
		this.i = i;
		this.setSize(new Dimension(945,100));
		this.setBackground(new Colors().getColor("RenBlue"));
		this.setBorder(BorderFactory.createEtchedBorder());
	}
}
