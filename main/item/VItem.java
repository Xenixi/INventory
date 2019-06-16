package inventory.main.item;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VItem extends Item{
	public VItem(String name, String desc, double itemCost, double itemPrice) {
		super(name, desc, itemCost, itemPrice);
	}
	public VItem(String name) {
		super(name);
	}
	public VItem(String name, String desc) {
		super(name, desc);
	}
	public VItem(String name, String desc, String instructions, double itemCost, double itemPrice) {
		super(name, desc, instructions, itemCost, itemPrice);
	}
	public VItem(String name, String desc, String instructions) {
		super(name, desc, instructions);
	}
	/////
	private ArrayList<File> filesIncluded = new ArrayList<>();
	private HashMap<File, String> runnableFileShortcuts = new HashMap<>();
	
	public void addFileInclude(File f) {
		filesIncluded.add(f);
	}
	public void addRunnableFileShortcut(File f, String args) {
		runnableFileShortcuts.put(f, args);
	}
	public List<File> getIncludedFiles(){
		return this.filesIncluded;
	}
	public HashMap<File, String> getRunnableFileShortcuts(){
		return this.runnableFileShortcuts;
	}
	public void resetFileIncludes() {
		filesIncluded.clear();
	}
	public void resetRunnableFileShortcuts() {
		runnableFileShortcuts.clear();
	}
	public void removeRunnableFileShortcut(File f) {
		for(File file: runnableFileShortcuts.keySet()) {
			int i = 0;
			if(file.getAbsolutePath().equalsIgnoreCase(f.getAbsolutePath())) {
				runnableFileShortcuts.remove(i);
			}
			i++;
		}
	}
	
}
