package inventory.main;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import inventory.guitool.PromptFrame;
import inventory.interfaces.INventoryCallable;
import inventory.main.item.ItemManager;
import inventory.main.util.dev.DevConsole;

public class Projects {
	public static final int LOCAL = 0;
	public static final int CLOUD = 1;
	static List<Project> localProjectList = new ArrayList<>();
	static List<Project> cloudProjectList = new ArrayList<>();
	static List<Project> localProjectSearchList = new ArrayList<>();
	static JPanel projectPanelMain = new JPanel();
	static JScrollPane projectPanel = new JScrollPane(projectPanelMain);
	static File localProjectsFolder = new File("Projects");
	static int next = 2;
	static boolean searching = false;
	static String searchingText = null;
	static ArrayList<Project> currentlySelected = new ArrayList<>();
	private static boolean floodSelect = false;
	static Project floodSelectStart, floodSelectEnd;

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
				ProjectData pd = INPRJHandler.readData(f);

				Project newProj = pd.isCorrupt() ? null : new Project(INPRJHandler.readData(f));

				if ((!(newProj == null) && !exists(newProj))) {
					localProjectList.add(newProj);
				}

			}
		} else if (loc == CLOUD) {

		} else {
			throw new Exception(
					"Invalid constructor input for init() method. Must be either Projects.LOCAL or Projects.CLOUD");
		}
		deepRefresh();
	}
	public static void deepRefresh() {
		if(searching)
			searchMode(searchingText);
		else
			searchMode("");
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
			saveAll();

			deepRefresh();
			
			return true;
		} else {
			for (Project p : cloudProjectList) {
				if (p.getName().equalsIgnoreCase(name)) {
					System.err.println("Failed to create cloud project -- project '" + name + "' already exists!");
					return false;
				}
			}
			cloudProjectList.add(new Project(name, desc, local, tags));
			deepRefresh();
			return true;
		}
	}

	public static void delProject(Project... proj) {
		DevConsole.printOut("Deleteing project: '" + (proj[0].getName() + ((proj.length > 1) ? "' -- etc." : "'")));
		for (Project p : proj) {
			for (File f : localProjectsFolder.listFiles()) {
				if (new Project(INPRJHandler.readData(f)).equals(p)) {
					f.delete();
					try {
						
						setSelected(null);
						updateInternal(LOCAL);
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		}

	}

	public static boolean renProject(Project p, String newName) {
		if (exists(Projects.fromName(newName))) {
			return false;
		}
		p.setName(newName);
		saveAll();
		p.getPanelUI().refresh();
		deepRefresh();
		return true;
	}

	public static void setProjectDesc(Project p, String desc) {
		p.setDesc(desc);
		saveAll();
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
			if (text.startsWith("[tag] ")) {
				for (String tag : p.getTags()) {
					if (tag.toLowerCase().contains(text.replace("[tag] ", "").toLowerCase())) {
						localProjectSearchList.add(p);
						break;
					}
				}
			}
			
			else if (p.getName().toLowerCase().contains(text.toLowerCase())) {
				localProjectSearchList.add(p);
			}
			

		}
		Collections.sort(localProjectSearchList);
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
			if (searching) {
				// add selection -- marking area where match was found
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

	// **********
	public static void setSelected(Project p) {
		for (Project project : localProjectList) {
			if (project.isSelected()) {
				project.setSelected(false);
			}
		}
		cancelFloodSelect();
		currentlySelected.clear();
		if (!(p == null)) {
			p.setSelected(true);
			currentlySelected.add(p);
			
		}
		ItemManager.refresh();
		
	}

	public static void addSelected(Project p) {
		boolean selectedAlready = false;
		for (Project project : currentlySelected) {
			if (project.equals(p)) {
				selectedAlready = true;
			}
		}
		if (!selectedAlready) {
			if (!(p == null)) {
				p.setSelected(true);
			}
			currentlySelected.add(p);
		}
		ItemManager.refresh();
	}
	//flood select is broken -- very much
	public static void setFloodSelectStart(Project p) {
		floodSelectStart = p;
		floodSelect = true;
	}
	public static void setFloodSelectComplete(Project p) {
		if(floodSelect) {
			floodSelectEnd = p;
			ArrayList<Project> toSelect = new ArrayList<>();
			boolean selectingOn = false;
			for(Project x : localProjectList) {
				if(x.equals(floodSelectStart)) {
					selectingOn = true;
					toSelect.add(x);
				} else if(selectingOn) {
					if(x.equals(floodSelectEnd)) {
						selectingOn = false;
					} else {
						toSelect.add(x);
					}
				} else {
					break;
				}
			}
			//Didn't do it all in one step to make it neater
			for(Project x : toSelect) {
				addSelected(x);
				
			}
			cancelFloodSelect();
			
		}
		else 
			DevConsole.printOut("Flood Select Start Never Set!");
	}
	public static void cancelFloodSelect() {
		floodSelect = false;
		
	}
	public static boolean floodSelectTrue() {
		return floodSelect;
	}
	// **********

	public static void delSelected() {
		PromptFrame pf = new PromptFrame();
		String[] names = new String[currentlySelected.size()];
		int[] numbers = new int[currentlySelected.size()];
		int a = 0;
		for (Project project : currentlySelected) {
			names[a] = project.getName();
			numbers[a] = a;
			a++;
		}
		if (currentlySelected.size() > 0) {
			pf.promptMultiInput("Delete Project(s)", "Please type project name(s) to confirm deletion.", names, numbers,
					new ImageIcon("gui/icon/removered.png"), new INventoryCallable() {

						@Override
						public void execute(String[] args) {
							int b = 0;
							boolean confirmed = true;
							for (String name : names) {
								if (!(args[b].equals(name))) {
									confirmed = false;
								}
								b++;
							}
							if (confirmed) {
								Project[] ps = new Project[currentlySelected.size()];
								int i = 0;
								for (Project p : currentlySelected) {
									DevConsole.printOut("Selected:");
									System.out.println(p);
									ps[i] = p;
									i++;
								}
								delProject(ps);
							} else {

								new PromptFrame().promptMultiInput("Delete Projects - Confirmation Failure",
										"Not all project names were confirmed.", new String[] {}, new int[] {},
										new ImageIcon("GUI/icon/removered.png"), new INventoryCallable() {

											@Override
											public void execute(String[] args) {
												return;
											}

											@Override
											public void cancelFallback() {
												return;
											}
										});
							}
						}

						@Override
						public void cancelFallback() {
							return;
						}

					});
		}
		ItemManager.refresh();
	}

	public static void saveAll() {
		for (Project p : localProjectList) {
			boolean match = false;
			for (File f : localProjectsFolder.listFiles()) {
				if ((new Project(INPRJHandler.readData(f.getAbsoluteFile()))).equalsHistory(p)) {
					INPRJHandler.writeData(f, p);

					match = true;
				}

			}
			if (!match) {
				File projFile = new File("Projects/" + p.getName() + ".inprj");
				try {
					projFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				INPRJHandler.writeData(projFile, p);
			}
		}
	}

	public static boolean validateProjName(String name) {
		if ((!name.contains("\\")) && (!name.contains("/")) && (!name.contains(":")) && (!name.contains("*"))
				&& (!name.contains("?")) && (!name.contains("\"")) && (!name.contains("<")) && (!name.contains(">"))
				&& (!name.contains("|"))) {
			return true;
		}

		return false;
	}

	public static Project fromName(String name) {
		return new Project(name, "", true, new String[] {});
	}

	public static void writeTagAdd(Project p, String tag) {
		p.addTag(tag);
		saveAll();
	}

	public static void writeTagRemove(Project p, String tag) {
		p.removeTag(tag);
		saveAll();
	}

	public static List<Project> getProjectList(int loc) {
		if (loc == LOCAL) {
			try {
				updateInternal(LOCAL);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return localProjectList;
		}
		return null;
	}

	public static List<Project> getSelected() {
		return currentlySelected;
	}
	

}
