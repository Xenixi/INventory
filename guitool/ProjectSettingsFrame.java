package inventory.guitool;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import inventory.gui.comp.SettingsMenuEntry;
import inventory.interfaces.SettingsMenuCallable;
import inventory.main.Colors;
import inventory.main.Project;

public class ProjectSettingsFrame extends JFrame {
	static SettingsMenuEntry selectedEntry;
	static JPanel general = new JPanel(), tags = new JPanel(), advanced = new JPanel();

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

		general.setBackground(new Colors().getColor("BackGray"));
		general.setLayout(new BorderLayout());
		JPanel subPanel = new JPanel();
		JPanel topThird = new JPanel(), midThird = new JPanel(), lowerThird = new JPanel();
		// All components (add here as needed):
		// top third:
		JPanel projTitlePanel = new JPanel(), starredPanel = new JPanel(), linkedPanel = new JPanel();
		JTextField projTitleField = new JTextField();
		//
		// mid third:
		JTextArea descTextArea = new JTextArea();
		//
		// lower third:
		// main subpanels
		JPanel tagsPanel = new JPanel(), refPanel = new JPanel(), imagesPanel = new JPanel();
		// sub tags:
		JPanel addDelTagsPanel = new JPanel(), searchTagsPanel = new JPanel(), tagsPanelMain = new JPanel();
		JTextArea searchTagsField = new JTextArea();
		// sub ref:
		JPanel addDelRefPanel = new JPanel(), searchRefPanel = new JPanel(), refFilesPanel = new JPanel();
		JTextArea searchRefFilesField = new JTextArea();
		// sub image(s) panel:
		JPanel multiImageDisplayPanel = new JPanel(), imagesMainPanel = new JPanel();

		// -------------- //
		// add all the layout managers...(To specific ones):
		subPanel.setLayout(new BorderLayout());
		topThird.setLayout(new BorderLayout());
		midThird.setLayout(new BorderLayout());
		lowerThird.setLayout(new BorderLayout());
		projTitlePanel.setLayout(new BorderLayout());
		linkedPanel.setLayout(new BorderLayout());
		tagsPanel.setLayout(new BorderLayout());
		refPanel.setLayout(new BorderLayout());
		imagesPanel.setLayout(new BorderLayout());
		addDelTagsPanel.setLayout(new BorderLayout());
		addDelRefPanel.setLayout(new BorderLayout());
		searchTagsPanel.setLayout(new BorderLayout());
		tagsPanelMain.setLayout(new BorderLayout());
		searchRefPanel.setLayout(new BorderLayout());
		refFilesPanel.setLayout(new BorderLayout());
		multiImageDisplayPanel.setLayout(new BorderLayout());
	}

	public static void refreshPage() {
		if (selectedEntry.getName().equals("General")) {

		}
	}

	public static void setSelected(SettingsMenuEntry e) {
		selectedEntry = e;
		e.setSelected(true);
	}

	public static void deselect(SettingsMenuEntry e) {
		if (!(e == null)) {
			e.setSelected(false);
			selectedEntry = null;
		}
	}
}
