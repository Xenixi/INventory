package inventory.main;

import java.io.Serializable;

import inventory.main.item.Item;
import inventory.main.item.ItemManager;

public class ProjectData implements Serializable {
	String name = "", desc = "";
	ItemManager itemM = new ItemManager();
	private static final long serialVersionUID = 1L;
	boolean local;
	String[] tags = new String[0];
	boolean corrupt = false;
	
	public ProjectData(boolean corrupt) {
		this.corrupt = corrupt;
	}
	public ProjectData() {
		
	}
	public boolean isCorrupt() {
		return corrupt;
	}
		

}
