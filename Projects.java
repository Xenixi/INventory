package inventory.main;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Projects {
	public static final int LOCAL = 0;
	public static final int CLOUD = 1;
	static ArrayList<Project> localProjectList = new ArrayList<>();
	static ArrayList<Project> cloudProjectList = new ArrayList<>();
	static JPanel projectPanelMain = new JPanel();
	static JScrollPane projectPanel = new JScrollPane(projectPanelMain);

	public static void init(int loc) throws Exception {
		if (loc == LOCAL) {
			File localProjectsFolder = new File("Projects");
			if(!localProjectsFolder.exists()) {
				localProjectsFolder.mkdir();
			}
			File[] projectFiles = localProjectsFolder.listFiles();
			
			for(File f: projectFiles) {
				localProjectList.clear();
				//local list add readData
			}
			
		} else if (loc == CLOUD) {

		} else {
			throw new Exception(
					"Invalid constructor input for init() method. Must be either Projects.LOCAL or Projects.CLOUD");
		}
	}

	public static void updateInternal(int loc) throws Exception {
		if (loc == LOCAL) {

		} else if (loc == CLOUD) {

		} else {
			throw new Exception(
					"Invalid constructor input for init() method. Must be either Projects.LOCAL or Projects.CLOUD");
		}
	}

	public static boolean createProject(String name, boolean local, String desc) {
		if (local) {
			for (Project p : localProjectList) {
				if (p.getName().equalsIgnoreCase(name)) {
					System.err.println("Failed to create local project -- project '" + name + "' already exists!");
					return false;
				}
			}
			localProjectList.add(new Project(name, desc, local));
			updatePanelUI();
			return true;
		} else {
			for (Project p : cloudProjectList) {
				if (p.getName().equalsIgnoreCase(name)) {
					System.err.println("Failed to create cloud project -- project '" + name + "' already exists!");
					return false;
				}
			}
			cloudProjectList.add(new Project(name, desc, local));
			updatePanelUI();
			return true;
		}
	}

	public static void delProject(String name) {
		int i = 0;
		for (Project p : localProjectList) {
			if (p.getName().equalsIgnoreCase(name)) {
				localProjectList.remove(i);
			}
			i++;
		}
		for (Project p : cloudProjectList) {
			if (p.getName().equalsIgnoreCase(name)) {
				cloudProjectList.remove(i);
				// must actually remove on server -- not completed yet
			}
		}
		updatePanelUI();
	}

	public static void renProject(String name, String newName) {

		for (Project p : localProjectList) {
			if (p.getName().equalsIgnoreCase(name)) {
				p.setName(newName);
			}
		}
		for (Project p : cloudProjectList) {
			if (p.getName().equalsIgnoreCase(name)) {
				p.setName(newName);
				// must actually change name on server -- not completed yet
			}
		}
		updatePanelUI();

	}

	public static Project getProject(String name) {
		Project toReturn = null;
		for (Project p : localProjectList) {
			if (p.getName().equalsIgnoreCase(name)) {
				toReturn = p;
			}
		}
		for (Project p : cloudProjectList) {
			if (p.getName().equalsIgnoreCase(name)) {
				toReturn = p;
			}
		}

		return toReturn;
	}

	/// project panel
	public static void updatePanelUI() {
		projectPanel.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));
		projectPanel.setBorder(BorderFactory.createEmptyBorder());
		projectPanel.setBackground(new Colors().getColor("BackGray"));

		projectPanelMain.setLayout(null);
		projectPanelMain.setBackground(new Colors().getColor("BackGray"));
		int next = 2;
		projectPanelMain.removeAll();

		for (Project p : localProjectList) {
			p.setUILoc(next);
			projectPanelMain.add(p.getPanelUI());
			next = next + 23;
		}

		projectPanelMain.setPreferredSize(new Dimension(0, (localProjectList.size() * 23) + 2));

	}

	public static JScrollPane getProjectPanel() {
		updatePanelUI();
		return projectPanel;
	}

}
