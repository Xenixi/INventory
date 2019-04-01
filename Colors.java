package inventory.main;

import java.awt.Color;
import java.util.HashMap;

public class Colors {
	static HashMap<String, Color> values = new HashMap<String, Color>();

	public Colors() {
		values.put("InGreen", new Color(10, 255, 10));
		values.put("BackGray", new Color(30,  29, 27));
		values.put("BackField", new Color(57, 57, 54));
		values.put("BackFieldSelected", new Color(63,63,63));
		values.put("BackGrayToneUp", new Color(45,45,45));
		values.put("Border", new Color(10, 10, 10));
		values.put("FieldText", new Color(170, 255, 170));
		values.put("ButtonBack", new Color(25, 25, 25));
		values.put("BorderLight", new Color(2,2,2));
		values.put("lighttext", new Color(245,245,245));
		values.put("ButtonsMain", new Color(50,50,50));
		values.put("RED", new Color(255,0,0));
		values.put("RenBlue", new Color(18, 133, 248));
		values.put("Settings", new Color(124, 77, 189));
	}

	public Color getColor(String name) {
		return values.get(name);

	}
}
