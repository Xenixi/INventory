package inventory.main;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Projects {
	public static final int LOCAL = 0;
	public static final int CLOUD = 1;
	static ArrayList<Project> localProjectList = new ArrayList<>();
	static ArrayList<Project> cloudProjectList = new ArrayList<>();
	static ArrayList<Project> localProjectSearchList = new ArrayList<>();
	static JPanel projectPanelMain = new JPanel();
	static JScrollPane projectPanel = new JScrollPane(projectPanelMain);
	static File localProjectsFolder = new File("Projects");
	static int next = 2;
	static boolean searching = false;
	static String searchingText = null;

	public static void init(int loc) throws Exception {
		if (loc == LOCAL) {

			if (!localProjectsFolder.exists()) {
				localProjectsFolder.mkdir();
			}

		} else if (loc == CLOUD) {

		} else {
			throw new Exception(
					"Invalid constructor input for init() method. Must be either Projects.LOCAL or Projects.CLOUD");
		}
		updateInternal(loc);
		updatePanelUI();
	}

	public static void updateInternal(int loc) throws Exception {
		if (loc == LOCAL) {
			File[] projectFiles = localProjectsFolder.listFiles();
			localProjectList.clear();
			for (File f : projectFiles) {
				// local list add readData
				Project newProj = new Project(INPRJHandler.readData(f));
				if (!exists(newProj)) {
					localProjectList.add(newProj);
				}

			}
		} else if (loc == CLOUD) {

		} else {
			throw new Exception(
					"Invalid constructor input for init() method. Must be either Projects.LOCAL or Projects.CLOUD");
		}
		updatePanelUI();
	}

	public static boolean exists(Project p) {
		for (Project project : localProjectList) {
			if (p.getName().equalsIgnoreCase(project.getName())) {
				return true;
			}
		}
		return false;
	}

	public static boolean createProject(String name, boolean local, String desc, String[] tags) {
		if (local) {
			for (Project p : localProjectList) {
				if (p.getName().equalsIgnoreCase(name)) {
					System.err.println("Failed to create local project -- project '" + name + "' already exists!");
					return false;
				}
			}

			Project newProj = new Project(name, desc, local, tags);
			localProjectList.add(newProj);

			try {
				File newProjFile = new File("Projects/" + name + ".inprj");
				newProjFile.createNewFile();
				INPRJHandler.writeData(newProjFile, newProj);
			} catch (IOException e) {
				e.printStackTrace();
			}

			updatePanelUI();
			return true;
		} else {
			for (Project p : cloudProjectList) {
				if (p.getName().equalsIgnoreCase(name)) {
					System.err.println("Failed to create cloud project -- project '" + name + "' already exists!");
					return false;
				}
			}
			cloudProjectList.add(new Project(name, desc, local, tags));
			updatePanelUI();
			return true;
		}
	}

	public static void delProject(String name) {
		// W.I.P. Doesn't do anything! -- except this:
		System.out.println("This is a WIP, doesn't actually do anything yet!");
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
	public static void searchMode(String text) {
		searchingText = text;
		if (!searching) {
			searching = true;
		}
		localProjectSearchList.clear();
		for (Project p : localProjectList) {
			if (p.getName().toLowerCase().contains(text.toLowerCase())) {
				localProjectSearchList.add(p);
			}

		}
		updatePanelUI();

	}

	public static void closeSearch() {
		localProjectSearchList.clear();
		searching = false;
		updatePanelUI();
	}

	public static void updatePanelUI() {
		projectPanel.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));
		projectPanel.setBorder(BorderFactory.createEmptyBorder());
		projectPanel.setBackground(new Colors().getColor("BackGray"));

		projectPanelMain.setLayout(null);
		projectPanelMain.setBackground(new Colors().getColor("BackGray"));

		projectPanelMain.removeAll();
		projectPanelMain.validate();
		projectPanelMain.repaint();

		next = 2;
			String oldText = "";
			for (Project p : searching ? localProjectSearchList : localProjectList) {
				p.setUILoc(next);
				projectPanelMain.add(p.getPanelUI());
				next = next + 23;
				if(searching) {
				//add selection -- marking area where match was found
				}
			}
			projectPanelMain.validate();
			projectPanelMain.repaint();

			projectPanelMain.setPreferredSize(new Dimension(0, (localProjectList.size() * 23) + 2));
		

	}

	public static JScrollPane getProjectPanel() {
		updatePanelUI();
		return projectPanel;
	}

}
