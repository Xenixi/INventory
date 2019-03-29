package inventory.main;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Projects {
	static ArrayList<Project> projectList = new ArrayList<>();
	static JPanel projectPanelMain = new JPanel();
	static JScrollPane projectPanel = new JScrollPane(projectPanelMain);
	public static boolean createProject(String name, boolean local, String desc) {
		for (Project p : projectList) {
			if (p.getName().equalsIgnoreCase(name)) {
				System.err.println("Failed to create project -- project '" + name + "' already exists!");
				return false;
			}
		}
		projectList.add(new Project(name, desc, local));
		updatePanelUI();
		return true;
	}

	public static void delProject(String name) {
		int i = 0;
		for (Project p : projectList) {
			if (p.getName().equalsIgnoreCase(name)) {
				projectList.remove(i);
			}
			i++;
		}
		updatePanelUI();
	}

	public static void renProject(String name, String newName) {

		for (Project p : projectList) {
			if (p.getName().equalsIgnoreCase(name)) {
				p.setName(newName);
			}
		}
		updatePanelUI();

	}

	public static Project getProject(String name) {
		return null;
	}

	/// project panel
	public static void updatePanelUI() {
		projectPanel.getVerticalScrollBar().setPreferredSize(new Dimension(15,0));
		projectPanel.setBorder(BorderFactory.createEmptyBorder());
		projectPanel.setBackground(new Colors().getColor("BackGray"));
		
		projectPanelMain.setLayout(null);
		projectPanelMain.setBackground(new Colors().getColor("BackGray"));
		int next = 2;
		projectPanelMain.removeAll();
		
		for (Project p : projectList) {
			p.setUILoc(next);
			projectPanelMain.add(p.getPanelUI());
			next = next + 23;
		}
		
		projectPanelMain.setPreferredSize(new Dimension(0, (projectList.size() * 23) + 2));

	}

	public static JScrollPane getProjectPanel() {
		updatePanelUI();
		return projectPanel;
	}

}
