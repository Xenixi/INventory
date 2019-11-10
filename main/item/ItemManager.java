package inventory.main.item;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import inventory.gui.comp.ItemListElement;
import inventory.main.Colors;
import inventory.main.Project;
import inventory.main.Projects;
import inventory.main.util.dev.DevConsole;

public class ItemManager implements Serializable {
	//probs can remove Serializable implementation
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
		for (Project p : Projects.getProjectList(Projects.LOCAL)) {
			for (Item i : p.getProjectItems()) {
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
			ItemListElement i1 = i.getItemListUI(new Point(5, y));
			i1.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseExited(MouseEvent e) {
					i1.setBackground(new Colors().getColor("ButtonsMain"));
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					i1.setBackground(new Colors().getColor("InGreen"));
				}

			
			});
			itemsPanel.add(i1);
			y = y + 55;
		}
		itemsPanel.validate();
		itemsPanel.repaint();
	}

	public static JPanel getPanel() {
		return itemsPanel;
	}
	
	public static void searchMode(String text) {
		currentResults.clear();
		if(Projects.getSelected().size() < 1) {
			for(Project p : Projects.getProjectList(Projects.LOCAL)) {
				for(Item i : p.getProjectItems()) {
					currentResults.add(i);
				}
			}
		} else {
		for(Project p : Projects.getSelected()) {
			for(Item i: p.getProjectItems()) {
				currentResults.add(i);
			}
		}
		}
		ArrayList<Item> searchResults = new ArrayList<Item>();
		for(Item i: currentResults) {
			if(i.getName().toLowerCase().contains(text.toLowerCase())) {
				searchResults.add(i);
			}
		}
		currentResults = searchResults;
		for(Item i : currentResults) {
		}
		updatePanel();
	}

}
