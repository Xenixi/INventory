package inventory.main.util.dev;

import java.util.ArrayList;
import java.util.HashMap;

import inventory.interfaces.DevConsoleRun;
import inventory.main.Projects;

public class DevConsoleBackend {
	private HashMap<String, DevConsoleRun> cmdMap = new HashMap<>();

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
				DevConsole.printOut("project modification args: loop, add, del*, ren* -- *not available yet");
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
