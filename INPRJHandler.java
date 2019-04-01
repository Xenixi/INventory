package inventory.main;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class INPRJHandler {
	public static Project readData(File f) {

		// reads project data from specified file.
		Scanner readFile;
		try {
			readFile = new Scanner(f);
			byte[] fileBytes = readFile.nextLine().getBytes("UTF-8");
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(fileBytes));
			return (Project)ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	public static void writeData(File f, Project p) {
		// writes project data to specified file.

		try {
			if (!f.exists()) {
				f.createNewFile();
			}

			PrintWriter fileOut = new PrintWriter(f);

			fileOut.println(new String(p.getSerialized(), "UTF-8"));
			fileOut.flush();
			fileOut.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static boolean destroy(File f) {
		return f.delete();
		// deletes the specified project file

	}

	public static File searchFiles(Project p) {
		//WIP
		// searches for the file containing specified project.
		return null;
	}

}
