package inventory.main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import inventory.gui.comp.ProjectListElement;

public class Project {

	ProjectListElement ple;
	File linkedData;
	ProjectData data = new ProjectData();;
	boolean selected;
	ArrayList<String> nameHistory = new ArrayList<>();

	public Project(String name, String desc, boolean local, String[] tags) {

		data.name = name;
		data.desc = desc;
		data.local = local;
		data.tags = tags;
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
		for(Project p: projs) {
			if(this.equals(p)) {
				return true;
			}
		}
		return false;
	}
	public Project locate(List<Project> projs) {
		for(Project p : projs) {
			if(this.equals(p)) {
				return p;
			}
		}
		return null;
	}
	public boolean equalsHistory(Project p) {
		for(String name : nameHistory) {
			if(name.equalsIgnoreCase(p.getName())) {
				return true;
			}
		}
		return false;
	}
	//override
	public boolean equals(Object o) {
		if(((Project)o).getName().equalsIgnoreCase(this.getName())) {
			return true;
		}
		return false;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(String tag: data.tags) {
			sb.append("|");
			sb.append(tag);
		}
		return "'" + data.name + "' -- Tags: " + sb;
	}
}
