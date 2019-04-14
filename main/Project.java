package inventory.main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import inventory.gui.comp.ProjectListElement;

public class Project {

	ProjectListElement ple;

	ProjectData data = new ProjectData();;
	
	boolean selected;

	public Project(String name, String desc, boolean local, String[] tags) {

		data.name = name;
		data.desc = desc;
		data.local = local;
		data.tags = tags;
		ple = new ProjectListElement(this);

	}

	public Project(ProjectData data) {
		this.data = data;
		ple = new ProjectListElement(this);
	}

	public String getName() {
		return data.name;
	}

	public String getDesc() {
		return data.name;
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
		data.name = name;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		ple.refresh();
	}
	public boolean isSelected() {
		return selected;
	}
	
	public String[] getTags() {
		return data.tags;
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
