package inventory.main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import inventory.gui.comp.ProjectListElement;

public class Project {

	ProjectListElement ple;

	ProjectData data = new ProjectData();;

	public Project(String name, String desc, boolean local) {

		data.name = name;
		data.desc = desc;
		data.local = local;
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

	// PLE Use only -- Yes I know it just calls the other methods in Projects, but it's for organization (Or at least my best attempt at it).

	public void delete() {
		Projects.delProject(getName());
	}
	public void rename(String newName) {
		Projects.renProject(getName(), newName);
	}
	public void changeSettings(String[] changes) {
		
	}

}
