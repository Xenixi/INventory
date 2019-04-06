package inventory.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ConfigEdit {
	private static boolean init = false;

	//
	static ArrayList<String> newLines = new ArrayList<>();
	static File datFile = new File("INM.dat");
	static ArrayList<String> linesFromDat = new ArrayList<String>();
	static ArrayList<String> linesToWrite = new ArrayList<String>();
	static HashMap<String, String> datVars = new HashMap<String, String>();
	//

	public static void checkFile() {
		newLines = new ArrayList<String>();
		linesFromDat = new ArrayList<String>();
		linesToWrite = new ArrayList<String>();

		if (!datFile.exists()) {
			try {
				System.out.println("Data file not found -- Creating File...");
				datFile.createNewFile();
				System.out.println("File created succesfully...");

			} catch (IOException e) {
				System.err.println("Failed to create data file - Exiting...");
				System.exit(0);
			}
		}

		//
		// add variable handles here-ex: FirstTime=
		// First input, before colon, is default value - other input is legal values.
		// (Can be true/false, or for integers, use interval notation with variable i)
		// ALWAYS PUT '=' AFTER THE VAR NAME
		/// '?' marks wildcard vars
		//// Edit Below Only Unless Modifying functionality!!!/////////////////

		datVars.put("Username=", "?");
		datVars.put("Organization=", "?");
		datVars.put("FirstTime=", "True:False");

		//// Edit Above Only Unless Modifying functionality!!!/////////////////

		try {

			Scanner scanDat = new Scanner(datFile);
			while (scanDat.hasNextLine()) {
				String valueAdd = scanDat.nextLine();
				linesFromDat.add(valueAdd);
				linesToWrite.add(valueAdd);
			}

			for (String varCheck : datVars.keySet()) {
				boolean missing = true;
				int iterator = 0;

				for (String line : linesFromDat) {
					if (line.startsWith(varCheck)) {
						String value = line.replace(varCheck, "");
						if (datVars.get(varCheck).contains(":")) {
							String[] valueType = datVars.get(varCheck).split(":");
							if (!(valueType.length > 2)) {
								String defaultValue = valueType[0];
								String legalValues = valueType[1];

								if (legalValues.contains("<i<")) {
									String[] intNotationValues = legalValues.split("<i<");
									try {
										int minValue = Integer.parseInt(intNotationValues[0]);
										int maxValue = Integer.parseInt(intNotationValues[1]);
										try {
											int datValueParsed = Integer.parseInt(value);
											if (!(datValueParsed < maxValue) || !(datValueParsed > minValue)) {

												// number out of range
												// set to default
												// replace the value in the text file --- figure this one out
												linesToWrite.remove(iterator);
												linesToWrite.add(varCheck + defaultValue);
											}
										} catch (Exception y) {

											// not even a number - must be fixed, so:
											// set to default
											// replace the value in text file --- figure this one out
											linesToWrite.remove(iterator);
											linesToWrite.add(varCheck + defaultValue);
										}

									} catch (Exception x) {
										System.err.println("Invalid ValueType for datVar: " + varCheck);
									}
								} else {
									if ((legalValues.equalsIgnoreCase("True")
											|| legalValues.equalsIgnoreCase("False"))) {
										if (!(value.equalsIgnoreCase("True") || value.equalsIgnoreCase("False"))) {
											// fix value, must be true/false
											linesToWrite.remove(iterator);
											linesToWrite.add(varCheck + defaultValue);

										}
									} else {
										System.err.println("Invalid ValueType for datVar: " + varCheck);
									}

								}

							} else {
								System.err.println("Invalid ValueType for datVar: " + varCheck);
							}

						} else {
							if (!datVars.get(varCheck).contains("?")) {
								System.err.println("Invalid ValueType for datVar: " + varCheck);
							}
						}
						missing = false;

					}
					iterator++;
				}
				if (missing) {

					linesToWrite.add(varCheck + datVars.get(varCheck).split(":")[0]);
					linesFromDat.add(varCheck + datVars.get(varCheck).split(":")[0]);
				}
			}
			PrintWriter pw = new PrintWriter(datFile);
			for (String line : linesToWrite) {
				pw.println(line);
			}
			pw.flush();
			pw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		init = true;

	}

	public static String getValue(String entry) {
		// work on this when ready.
		if (!init) {
			checkFile();
		}
		for (String line : linesFromDat) {
			String[] values = line.split("=");
			String entryValueRead = values[0];
			String valueValueRead = values[1];
			if (entryValueRead.equalsIgnoreCase(entry)) {
				return valueValueRead;
			}
		}

		return null;
	}

	public static boolean setValue(String findEntry, String setValue) {
		if (!init) {
			checkFile();
		}

		newLines = linesFromDat;

		int i = 0;
		for (String test : datVars.keySet()) {
			if (test.equalsIgnoreCase(findEntry)) {
				String validValues = datVars.get(test);
				if (validValues.contains(":")) {
					if (!(setValue.equalsIgnoreCase("true") || setValue.equalsIgnoreCase("false"))) {
						System.err.println("Failed to set value of '" + findEntry + "' to '" + setValue
								+ "' -- must be true/false");
						return false;
					}
				} else if (validValues.contains("<i<")) {
					String[] minMax = validValues.split("<i<");
					int minValue = (Integer.parseInt(minMax[0]));
					int maxValue = (Integer.parseInt(minMax[1]));

					if (!(Integer.parseInt(setValue) < maxValue || Integer.parseInt(setValue) > minValue)) {
						System.err.println("Failed to set value of '" + findEntry + "' to '" + setValue
								+ "' -- int out of bounds [" + validValues + "]");
						return false;
					}
				}
			}
		}
		for (String line : newLines) {
			if (line.contains("=")) {

				String[] values = line.split("=");
				String entry = values[0];
				String value = values[1];
				if (entry.equalsIgnoreCase(findEntry)) {
					newLines.remove(i);
					newLines.add(entry + "=" + setValue);
					System.err.println("Value of '" + entry + "' changed: '" + value + "' -->> '" + setValue + "'");
					finalizeFile();
					return true;
				}
			}
			i++;

		}

		return false;
	}

	public static boolean isInitialized() {
		return init;
	}

	private static void finalizeFile() {

		try {
			PrintWriter pw = new PrintWriter(datFile);
			for (String line : newLines) {
				pw.println(line);
				System.out.println(line);

			}
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		init = false;
	}
}
