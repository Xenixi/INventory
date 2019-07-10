package inventory.main.item;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import inventory.main.Colors;
import inventory.main.Project;
import inventory.main.Projects;
import inventory.main.util.dev.DevConsole;

public class ItemManager implements Serializable {
	private static ArrayList<Double> managerIDs = new ArrayList<>();
	private Double ID = 0.0;
	private static JPanel itemsPanel = new JPanel();
	private static final long serialVersionUID = 1L;
	private static ArrayList<Item> currentResults = new ArrayList<>();
	String name;
	// static stuff
	protected static ArrayList<ItemManager> itemManagers = new ArrayList<>();
	public static void addManager(ItemManager i) {
		itemManagers.add(i);
	}
	public static List<ItemManager> getItemManagers() {
		return itemManagers;
	}

	public static void init() {
		itemsPanel.setBackground(new Colors().getColor("BackGray"));
		itemsPanel.setLayout(null);
		/// Get rid of this code:
		
		///
		refresh();

		updatePanel();
	}
	public static void refresh() {
		currentResults.clear();
		updatePanel();
		if(Projects.getSelected().size() == 0) {
			System.err.println("Nothing selected");
		for (ItemManager im : itemManagers) {
			for (Item i : im.getItems()) {
				DevConsole.printOut("Item read: " + i.getName());
				currentResults.add(i);
			}
		}
		} else {
			System.err.println("selected");
			for(Project p: Projects.getSelected()) {
				System.out.println(p.getName() + " | " + p.ID);
				//
				for(Item i : p.getProjectItems()) {
					
					DevConsole.printOut("Item read: " + i.getName());
					currentResults.add(i);
					
				}
			}
		}
		updatePanel();
	}
	public static void updatePanel() {
		itemsPanel.removeAll();
		int y = 5;
		for (Item i : currentResults) {
			System.err.println("Adding item");
			itemsPanel.add(i.getItemListUI(new Point(5, y)));
			y = y + 105;
		}
		itemsPanel.validate();
		itemsPanel.repaint();
	}

	public static JPanel getPanel() {
		return itemsPanel;
	}

	// instance stuff
	
	
	
	public ItemManager(Project p) {
		/*The whole point of this is to make sure only one item manager is added for each project
		 * projects are created many times (when loading from the file, creating a new one, saving - so duplicates are possible (That's why the console was going crazy every time
		 * I created a new project. 
		 * This prevents that --- one per project (based on name)
		 * yep - works
		 */
		name = p.getName();
		StringBuilder sb = new StringBuilder();
		for(char c: name.toCharArray()) {
			int i = c;
			sb.append(i);
		}
		ID = 0.000001 * (Double.parseDouble(sb.toString()));
		boolean contains = false;
		for(double d: managerIDs) {
			if(d == ID) {
				contains = true;
			}
		}
		if(!contains) {
			managerIDs.add(ID);
			itemManagers.add(this);
		}
		
	}
	
	//maybe ditch everything below here....
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
