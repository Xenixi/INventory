package inventory.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class INPRJHandler {
	public static ProjectData readData(File f) {

		// reads project data from specified file.
		try {
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			ProjectData pd = (ProjectData)ois.readObject();
			ois.close();
			fis.close();
			return pd;
			
		} catch (Exception e) {
			return new ProjectData(true);
		}
		
		
	}

	public static void writeData(File f, Project p) {
		// writes project data to specified file.

		try {
			if (!f.exists()) {
				f.createNewFile();
			}

			 FileOutputStream fos = new FileOutputStream(f);
			 
			fos.write(p.getSerialized());
			fos.flush();
			fos.close();
			 
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static boolean destroy(File f) {
		return f.delete();
	}

	public static File searchFiles(Project p) {
		//WIP
		// searches for the file containing specified project.
		return null;
	}

}
