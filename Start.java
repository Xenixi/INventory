package inventory.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Start {
	public static void main(String[] args) {

		ConfigEdit.checkFile();

		System.out.println("Starting INventory Manager");
		System.out.println("Checking args...");
		if (args.length > 0) {
			// for custom integration
			String mode = args[0];
			if (args.length > 3) {

				String action = args[1];
				String value = args[2];
				String enter = args[3];

				switch (mode) {

				case "input": {
					System.out.println("Headless Input");
					// enter headless input code here - once ready

					break;
				}
				case "output": {
					System.out.println("Headless Output");
					// enter headless output code here - once ready

					break;
				}
				default: {
					System.out.println("Invalid Mode - Must be 'input' or 'output' for headless input/output");

					break;
				}

				}

			} else {
				System.out.println("Please enter full arguments: <mode> <action> <value> <enter>");
			}
		} else {
			GUI gui = new GUI();
		}

	}

}
