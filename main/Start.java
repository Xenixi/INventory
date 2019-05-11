package inventory.main;

import inventory.main.util.dev.DevConsole;

public class Start {
	public static void main(String[] args) {
		DevConsole.init();
		ConfigEdit.checkFile();

		DevConsole.printOut("Starting INventory Manager");
		DevConsole.printOut("Checking args...");
		if (args.length > 0) {
			// for custom integration
			String mode = args[0];
			if (args.length > 3) {

				String action = args[1];
				String value = args[2];
				String enter = args[3];

				switch (mode) {

				case "input": {
					DevConsole.printOut("Headless Input");
					// enter headless input code here - once ready

					break;
				}
				case "output": {
					DevConsole.printOut("Headless Output");
					// enter headless output code here - once ready

					break;
				}
				default: {
					DevConsole.printOut("Invalid Mode - Must be 'input' or 'output' for headless input/output");

					break;
				}

				}

			} else {
				DevConsole.printOut("Please enter full arguments: <mode> <action> <value> <enter>");
			}
		} else {
			GUI gui = new GUI();
		}

	}

}
