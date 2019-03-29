package inventory.main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Fonts {
	public static Font getFont(String font, float size) {
		try {
			Font fontReturn;

			fontReturn = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/" + font + ".ttf")).deriveFont(size);

			return fontReturn;

		} catch (FileNotFoundException e) {
			System.err.println("Font not found! Load Failed!");
		} catch (FontFormatException e) {
			System.err.println("Font Format Exception Thrown!");
		} catch (IOException e) {
			System.err.println("IOException thrown while loading font!");
			e.printStackTrace();
		}

		return null;

	}
}
