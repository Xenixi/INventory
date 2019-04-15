package inventory.guitool;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import inventory.gui.comp.SettingsMenuEntry;
import inventory.interfaces.SettingsMenuCallable;
import inventory.main.Colors;
import inventory.main.Project;

public class ProjectSettingsFrame extends JFrame {
	static SettingsMenuEntry selectedEntry;
	public static void displayFrame(Project p) {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setSize(600, 400);
		frame.setIconImage(new ImageIcon("logo.png").getImage());
		frame.setTitle("Project Settings - " + p.getName());
		JPanel mainPanel = new JPanel();

		frame.add(mainPanel);
		mainPanel.setBackground(new Colors().getColor("BackGray"));
		mainPanel.setLayout(new BorderLayout());

		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();

		mainPanel.add(leftPanel, BorderLayout.WEST);
		leftPanel.setPreferredSize(new Dimension(150, 400));
		leftPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, new Colors().getColor("BorderLight")));
		mainPanel.add(rightPanel, BorderLayout.CENTER);

		leftPanel.setBackground(new Colors().getColor("BackGray"));
		rightPanel.setBackground(new Colors().getColor("BackGray"));
		leftPanel.setLayout(null);
		leftPanel.add(new SettingsMenuEntry("General", new Point(0, 4), new SettingsMenuCallable() {
			@Override
			public void onClick(SettingsMenuEntry e) {
				deselect(selectedEntry);
				setSelected(e);
				System.out.println("General - clicked");
			}
		}));
		leftPanel.add(new SettingsMenuEntry("Tags", new Point(0, 28), new SettingsMenuCallable() {

			@Override
			public void onClick(SettingsMenuEntry e) {
				deselect(selectedEntry);
				setSelected(e);
				System.out.println("Tags - clicked");
			}
		}));
		leftPanel.add(new SettingsMenuEntry("Advanced", new Point(0, 52), new SettingsMenuCallable() {

			@Override
			public void onClick(SettingsMenuEntry e) {
				deselect(selectedEntry);
				setSelected(e);
				System.out.println("Advanced - clicked");
			}
		}));
		
	}
	public static void setSelected(SettingsMenuEntry e) {
		selectedEntry = e;
		e.setSelected(true);
	}
	public static void deselect(SettingsMenuEntry e) {
		if(!(e == null)) {
		e.setSelected(false);
		selectedEntry = null;
		}
	}
}
