package inventory.main.util.dev;

import java.util.ArrayList;
import java.util.HashMap;

import javax.print.attribute.standard.PDLOverrideSupported;

import inventory.interfaces.DevConsoleRun;
import inventory.main.Project;
import inventory.main.Projects;

public class DevConsoleBackend {
	private HashMap<String, DevConsoleRun> cmdMap = new HashMap<>();
	private boolean delConfirmActive = false;
	private Project pending = null;

	public DevConsoleBackend() {
		cmdMap.put("project loop add", new DevConsoleRun() {

			@Override
			public void runCMD(String[] args) {

				try {
					String iterations = args[0];

					if (args.length < 2) {
						DevConsole.printOut("Must enter project loop add:<iter> <name> is minimum");
						return;
					}

					String name = args[1];
					String desc = "";
					if (args.length > 2) {

						desc = args[2];
					}
					ArrayList<String> tags = new ArrayList<>();
					int a = 0;
					for (String arg : args) {
						if (a > 2) {
							tags.add(arg);
						}
						a++;
					}
					String[] tagsSA = new String[tags.size()];
					tagsSA = tags.toArray(tagsSA);

					for (int i = 0; i < Integer.parseInt(iterations); i++) {
						Projects.createProject(name + i, true, desc, tagsSA);
						DevConsole.printOut("Loop Created Project: '" + (name + i) + "'");
					}
				} catch (Exception e) {
					DevConsole.printOut("Error While Executing Command");
					DevConsole.printOut("Note: Make sure you don't leave space - cmd:args");
					e.printStackTrace();
				}
			}
		});
		cmdMap.put("exitall", new DevConsoleRun() {

			@Override
			public void runCMD(String[] args) {
				System.exit(0);
			}
		});
		cmdMap.put("exit", new DevConsoleRun() {

			@Override
			public void runCMD(String[] args) {
				DevConsole.displayConsole();
			}
		});
		cmdMap.put("reinit", new DevConsoleRun() {

			@Override
			public void runCMD(String[] args) {
				DevConsole.init();
				DevConsole.displayConsole();
			}
		});
		cmdMap.put("clear", new DevConsoleRun() {

			@Override
			public void runCMD(String[] args) {
				DevConsole.clear();
			}
		});
		cmdMap.put("project", new DevConsoleRun() {

			@Override
			public void runCMD(String[] args) {
				DevConsole.printOut("project modification args: loop, add, del, ren");
			}
		});
		cmdMap.put("project loop", new DevConsoleRun() {

			@Override
			public void runCMD(String[] args) {
				DevConsole.printOut("Must be followed by 'add' ie: project loop add:<args>");
			}
		});
		cmdMap.put("project add", new DevConsoleRun() {

			@Override
			public void runCMD(String[] args) {
				if (args.length < 1) {
					DevConsole.printOut("Project Add: Must use arg:<name>");
				}
				String name = args[0];
				String desc = "";
				if (args.length > 1) {
					desc = args[1];
				}
				ArrayList<String> tags = new ArrayList<>();
				int a = 0;
				for (String arg : args) {
					if (a > 1) {
						tags.add(arg);
					}
					a++;
				}
				String[] tagsSA = new String[tags.size()];
				tagsSA = tags.toArray(tagsSA);
				Projects.createProject(name, true, desc, tagsSA);
				DevConsole.printOut("add: Created Project: '" + name + "'");
			}
		});
		cmdMap.put("project del", new DevConsoleRun() {

			@Override
			public void runCMD(String[] args) {
				if (delConfirmActive) {
					DevConsole.printOut("Request still in progress...");
					return;
				}
				if (args.length < 1 || args[0].equals("")) {
					DevConsole.printOut("You must enter a project name!");
					return;
				}
				if (Projects.exists(Projects.fromName(args[0]))) {
					pending = Projects.getProject(args[0]);
					DevConsole.printOut("Confirm with pdelconfirm within 10 seconds:");
					delConfirmActive = true;
					Thread t1 = new Thread(new Runnable() {

						@Override
						public void run() {
							DevConsole.printOut("Time Remaining:");
							for (int i = 0; i < 10 && delConfirmActive; i++) {
								try {
									DevConsole.printOut("t: " + (10 - (i)));
									Thread.sleep(1000);

								} catch (InterruptedException e) {
									DevConsole.printOut("Failed to initiate sequence");

								}
							}
							if (delConfirmActive) {
								DevConsole.printOut("Deletion request timed out.");
								delConfirmActive = false;
							}
						}
					});
					t1.start();
				} else {
					DevConsole
							.printOut("Specified project does not exist! It's quite hard to delete nothing, you know");
				}

			}
		});
		cmdMap.put("pdelconfirm", new DevConsoleRun() {

			@Override
			public void runCMD(String[] args) {
				if (delConfirmActive && pending != null) {
					Projects.delProject(pending);

					DevConsole.printOut("Project '" + pending.getName() + "' deleted sucessfully");
					pending = null;
					delConfirmActive = false;
				} else {
					DevConsole.printOut(
							"Either no project has been requested for deletion, or the request timed out. Please try again.");
				}

			}
		});
		cmdMap.put("project ren", new DevConsoleRun() {

			@Override
			public void runCMD(String[] args) {
				if (args.length < 2 || args[0].equals("") || args[1].equals("")) {
					DevConsole.printOut("You must enter a project name and new name!");
					return;
				}
				if (Projects.exists(Projects.fromName(args[0]))) {
					Projects.renProject(Projects.getProject(args[0]), args[1]);
					DevConsole.printOut("Project '" + args[0] + "' sucessfully renamed to '" + args[1] + "'");

				} else {
					DevConsole.printOut("Cannot rename project '" + args[0]
							+ "' -fyi a project must first actually exist to be renamed.");
				}
			}
		});
		cmdMap.put("project gettags", new DevConsoleRun() {

			@Override
			public void runCMD(String[] args) {
				if (args.length < 1 | args[0] == "") {
					DevConsole.printOut("You must specify a project name!");
					return;
				}
				DevConsole.printOut("Project Tags for '" + Projects.getProject(args[0]).getName() + "'");
				int i = 0;
				for (String tag : Projects.getProject(args[0]).getTags()) {
					DevConsole.printOut("Tag-" + i + ": '" + tag + "'");
					i++;
				}

			}
		});
		cmdMap.put("project getselectedtags", new DevConsoleRun() {

			@Override
			public void runCMD(String[] args) {
				if (args.length < 1 | args[0] == "") {
					DevConsole.printOut("You must specify a project name!");
					return;
				}
				DevConsole.printOut("Project Selected Tags for '" + Projects.getProject(args[0]).getName() + "'");
				int i = 0;
				for (String tag : Projects.getProject(args[0]).getSelectedTags()) {
					DevConsole.printOut("Tag-" + i + ": '" + tag + "'");
					i++;
				}

			}
		});
	}

	public DevConsoleRun getCMD(String cmd) {
		return cmdMap.get(cmd);
	}

	public boolean checkCMD(String cmd) {
		for (String key : cmdMap.keySet()) {
			if (key.equals(cmd)) {
				return true;
			}
		}
		return false;
	}
}
