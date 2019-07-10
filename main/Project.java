package inventory.main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import inventory.gui.comp.ProjectListElement;
import inventory.main.item.Item;
import inventory.main.item.ItemManager;
import inventory.main.util.dev.DevConsole;

public class Project {
	public int ID = new Random().nextInt();
	ProjectListElement ple;
	File linkedData;
	ProjectData data = new ProjectData();
	boolean selected;
	ArrayList<String> nameHistory = new ArrayList<>();
	ArrayList<String> tagsSelected = new ArrayList<>();

	public Project(String name, String desc, boolean local, String[] tags) {

		data.name = name;
		data.desc = desc;
		data.local = local;

		ArrayList<String> tagsList = new ArrayList<>();

		for (String tag : tags) {
			if (!tagsList.contains(tag)) {
				tagsList.add(tag);
			}
		}
		data.tags = new String[tagsList.size()];
		data.tags = tagsList.toArray(data.tags);
		ple = new ProjectListElement(this);
		linkedData = new File("Projects/" + data.name + ".inprj");
		nameHistory.add(data.name);
	}

	public Project(ProjectData data) {
		
		this.data = data;
		
		ple = new ProjectListElement(this);
		linkedData = new File("Projects/" + data.name + ".inprj");
		nameHistory.add(data.name);
	}

	public String getName() {
		return data.name;
	}

	public String getDesc() {
		return data.desc;
	}

	public boolean isLocal() {
		return data.local;
	}

	public byte[] getSerialized() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(data);
			return bos.toByteArray();
		} catch (IOException e) {
			System.err
					.println("Error while serializing object - - Project: " + data.name + " - - - Desc: " + data.desc);
			e.printStackTrace();
		}

		return null;
	}

	public ProjectListElement getPanelUI() {
		return ple;
	}

	public void setUILoc(int verticle) {
		ple.setLocation(0, verticle);
	}

	public void setName(String name) {
		nameHistory.add(data.name);
		data.name = name;
		updateFileName();
	}

	public void updateFileName() {
		File newFile = new File("Projects/" + data.name + ".inprj");
		linkedData.renameTo(newFile);
		linkedData = newFile;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		ple.refresh();
	}

	public void setDesc(String desc) {
		data.desc = desc;
	}

	public boolean isSelected() {
		return selected;
	}

	public String[] getTags() {
		return data.tags;
	}

	public boolean isFoundIn(List<Project> projs) {
		for (Project p : projs) {
			if (this.equals(p)) {
				return true;
			}
		}
		return false;
	}

	public Project locate(List<Project> projs) {
		for (Project p : projs) {
			if (this.equals(p)) {
				return p;
			}
		}
		return null;
	}

	public boolean equalsHistory(Project p) {
		for (String name : nameHistory) {
			if (name.equalsIgnoreCase(p.getName())) {
				return true;
			}
		}
		return false;
	}

	public void addTag(String tag) {
		ArrayList<String> tags = new ArrayList<>();
		for (String tagOld : data.tags) {
			tags.add(tagOld);
		}
		tags.add(tag);
		String[] newTags = new String[tags.size()];
		newTags = tags.toArray(newTags);
		data.tags = newTags;
	}

	public void setTagSelected(String tag, boolean selected) {
		if (selected) {
			tagsSelected.add(tag.toLowerCase());
			DevConsole.printOut("Added tag to selected list: '" + tag.toLowerCase() + "'");
		} else {
			int i = 0;
			for (String tagSelected : tagsSelected) {
				if (tagSelected.equalsIgnoreCase(tag)) {
					tagsSelected.remove(i);

					DevConsole.printOut("Removed tag from selected list: '" + tag.toLowerCase() + "'");
					break;
				}
				i++;
			}
		}
	}

	public List<String> getSelectedTags() {
		return tagsSelected;
	}

	public void removeTag(String tagName) {
		ArrayList<String> tagsList = new ArrayList<>();
		for (String tag : data.tags) {
			if (!tag.equalsIgnoreCase(tagName))
				tagsList.add(tag);
		}
		String[] tagsArrayNew = new String[tagsList.size()];
		tagsArrayNew = tagsList.toArray(tagsArrayNew);
		data.tags = tagsArrayNew;
	}

	public void createProjectItem(String name, String desc, String instructions) {
		ArrayList<Item> itemMod = new ArrayList<>();
		if (data.items.length > 0) {
			for (Item i : data.items) {
				itemMod.add(i);
			}
		}
		itemMod.add(new Item(name, desc, instructions));
		data.items = new Item[itemMod.size()];
		data.items = itemMod.toArray(data.items);
	}

	public void createProjectItem(String name, String desc) {
		ArrayList<Item> itemMod = new ArrayList<>();
		if (data.items.length > 0) {
			for (Item i : data.items) {
				itemMod.add(i);
			}
		}
		itemMod.add(new Item(name, desc));
		data.items = new Item[itemMod.size()];
		data.items = itemMod.toArray(data.items);
		Projects.saveAll();
		ItemManager.refresh();
	}

	public void createProjectItem(String name) {
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -- use DevConsole to add items for
		// test - cmd: project additemstest:
		// figure out why this is throwing a NPE
		ArrayList<Item> itemMod = new ArrayList<>();
		
			for (Item i : data.items) {
				itemMod.add(i);
			}
		itemMod.add(new Item(name));
		data.items = new Item[itemMod.size()];
		data.items = itemMod.toArray(data.items);
		ItemManager.refresh();
		Projects.saveAll();
	}

	public Item[] getProjectItems() {
		return data.items;
	}

	// override
	public boolean equals(Object o) {
		if (((Project) o).getName().equalsIgnoreCase(this.getName())) {
			return true;
		}
		return false;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String tag : data.tags) {
			sb.append("|");
			sb.append(tag);
		}
		return "'" + data.name + "' -- Tags: " + sb;
	}

}
