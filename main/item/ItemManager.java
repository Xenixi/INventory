package inventory.main.item;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import inventory.main.Colors;

public class ItemManager implements Serializable {

	private static JPanel itemsPanel = new JPanel();
	private static final long serialVersionUID = 1L;
	private static ArrayList<Item> currentResults = new ArrayList<>();
	// static stuff
	protected static ArrayList<ItemManager> itemManagers = new ArrayList<>();

	public static List<ItemManager> getItemManagers() {
		return itemManagers;
	}

	public static void init() {
		itemsPanel.setBackground(new Colors().getColor("BackGray"));
		itemsPanel.setLayout(null);
		
		for(ItemManager im: itemManagers) {
			for(Item i: im.getItems()) {
				currentResults.add(i);
			}
		}
		currentResults.add(new Item("hi"));
		currentResults.add(new Item("ii"));
		updatePanel();
	}
	
	public static void updatePanel() {
		int y = 5;
		for(Item i: currentResults) {
			itemsPanel.add(i.getItemListUI(new Point(5,y)));
			y = y + 105;
		}
	}
	
	public static JPanel getPanel() {
		return itemsPanel;
	}

	// instance stuff
	public ItemManager() {
		ItemManager.itemManagers.add(this);
	}

	Item[] items = new Item[0];

	private ArrayList<Item> getTempItemList() {
		ArrayList<Item> temp = new ArrayList<Item>();
		for (Item i : items) {
			temp.add(i);
		}
		return temp;
	}

	private void setToArray(List<Item> temp) {
		items = new Item[temp.size()];
		items = temp.toArray(items);
	}

	public void addItem(Item i) {
		List<Item> temp = getTempItemList();
		temp.add(i);
		setToArray(temp);
	}

	public void removeItem(Item i) {
		List<Item> temp = getTempItemList();
		for (Item item : temp) {
			int a = 0;
			if (item.equals(i)) {

				temp.remove(a);
				setToArray(temp);
				break;
			}
			a++;
		}
	}

	public Item[] getItems() {
		return items;
	}

}
