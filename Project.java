package inventory.main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import inventory.gui.comp.ProjectListElement;

public class Project implements Serializable {

	private static final long serialVersionUID = 2019001L;

	String name, desc;
	boolean local;
	ProjectListElement ple;
	
	public Project(String name, String desc, boolean local) {
		this.name = name;
		this.desc = desc;
		this.local = local;
		 ple = new ProjectListElement(this);
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public boolean isLocal() {
		return local;
	}
	public void rename(String newName) {
		name = newName;
	}

	public byte[] getSerialized() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(this);
			return bos.toByteArray();
		} catch (IOException e) {
			System.err.println("Error while serializing object - - Project: " + name + " - - - Desc: " + desc);
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
		this.name = name;
	}

}
