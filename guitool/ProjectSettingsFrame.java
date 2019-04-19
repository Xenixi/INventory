package inventory.guitool;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import inventory.gui.comp.SettingsMenuEntry;
import inventory.interfaces.SettingsMenuCallable;
import inventory.main.Colors;
import inventory.main.Fonts;
import inventory.main.Project;

public class ProjectSettingsFrame extends JFrame {
	static SettingsMenuEntry selectedEntry;
	static JPanel general = new JPanel(), tags = new JPanel(), advanced = new JPanel(), rightPanel = new JPanel();
	static JPanel current = general;
	public static void displayFrame(Project p) {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setSize(850, 800);
		frame.setIconImage(new ImageIcon("logo.png").getImage());
		frame.setTitle("Project Settings - " + p.getName());
		JPanel mainPanel = new JPanel();

		frame.add(mainPanel);
		mainPanel.setBackground(new Colors().getColor("BackGray"));
		mainPanel.setLayout(new BorderLayout());
		frame.setResizable(false);
		JPanel leftPanel = new JPanel();
		

		mainPanel.add(leftPanel, BorderLayout.WEST);
		leftPanel.setPreferredSize(new Dimension(150, 400));
		leftPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, new Colors().getColor("BorderLight")));
		mainPanel.add(rightPanel, BorderLayout.CENTER);

		leftPanel.setBackground(new Colors().getColor("BackGray"));
		rightPanel.setBackground(new Colors().getColor("BackGray"));
		leftPanel.setLayout(null);
		SettingsMenuEntry generalSettingsEntry = new SettingsMenuEntry("General", new Point(0, 4), new SettingsMenuCallable() {
			@Override
			public void onClick(SettingsMenuEntry e) {
				deselect(selectedEntry);
				setSelected(e);
				System.out.println("General - clicked");
			}
		});
		
		SettingsMenuEntry tagsSettingsEntry = new SettingsMenuEntry("Tags", new Point(0, 28), new SettingsMenuCallable() {

			@Override
			public void onClick(SettingsMenuEntry e) {
				deselect(selectedEntry);
				setSelected(e);
				System.out.println("Tags - clicked");
			}
		});
		SettingsMenuEntry advancedSettingsEntry = new SettingsMenuEntry("Advanced", new Point(0, 52), new SettingsMenuCallable() {

			@Override
			public void onClick(SettingsMenuEntry e) {
				deselect(selectedEntry);
				setSelected(e);
				System.out.println("Advanced - clicked");
			}
		});
		leftPanel.add(generalSettingsEntry);
		leftPanel.add(tagsSettingsEntry);
		leftPanel.add(advancedSettingsEntry);

		general.setBackground(new Colors().getColor("BackGray"));
		general.setLayout(new BorderLayout());
		JPanel subPanel = new JPanel();
		general.add(subPanel, BorderLayout.CENTER);
		// Borders:
		JPanel left = new JPanel(), right = new JPanel(), top = new JPanel(), bottom = new JPanel();
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
		// ************************************************************///
		rightPanel.setLayout(new BorderLayout());

		

		left.setPreferredSize(new Dimension(25, 550));
		right.setPreferredSize(new Dimension(25, 550));
		top.setPreferredSize(new Dimension(650, 25));
		bottom.setPreferredSize(new Dimension(650, 25));

		rightPanel.add(left, BorderLayout.WEST);
		rightPanel.add(right, BorderLayout.EAST);
		rightPanel.add(top, BorderLayout.NORTH);
		rightPanel.add(bottom, BorderLayout.SOUTH);
		left.setBackground(new Colors().getColor("BackGray"));
		right.setBackground(new Colors().getColor("BackGray"));
		top.setBackground(new Colors().getColor("BackGray"));
		bottom.setBackground(new Colors().getColor("BackGray"));
		subPanel.setBackground(new Colors().getColor("BackGray"));
		subPanel.add(topThird, BorderLayout.NORTH);
		subPanel.add(midThird, BorderLayout.CENTER);
		subPanel.add(lowerThird, BorderLayout.SOUTH);
		topThird.setPreferredSize(new Dimension(650, 120));
		lowerThird.setPreferredSize(new Dimension(650, 250));
		// temp:
		topThird.setBackground(new Colors().getColor("BackGray"));
		midThird.setBackground(new Colors().getColor("BackGray"));
		lowerThird.setBackground(new Colors().getColor("BackGray"));

		//

		JPanel topThirdDivider = new JPanel();
		JPanel topThirdBottomPanel = new JPanel();
		JPanel topThirdBottomPanelParent = new JPanel();
		topThird.add(projTitleField, BorderLayout.NORTH);
		projTitleField.setPreferredSize(new Dimension(650, 70));
		projTitleField.setBackground(new Colors().getColor("BackField"));
		projTitleField.setFont(Fonts.getFont("CreteRound-Regular", 45f));
		projTitleField.setForeground(new Colors().getColor("InGreen"));
		projTitleField.setBorder(BorderFactory.createEtchedBorder());
		projTitleField.setCaretColor(new Colors().getColor("InGreen"));
		projTitleField.setHorizontalAlignment(SwingConstants.CENTER);
		topThird.add(topThirdDivider, BorderLayout.CENTER);
		topThird.add(topThirdBottomPanelParent, BorderLayout.SOUTH);
		topThirdBottomPanelParent.setLayout(new BorderLayout());
		topThirdBottomPanelParent.add(topThirdBottomPanel, BorderLayout.CENTER);
		topThirdBottomPanel.setPreferredSize(new Dimension(650, 40));

		topThirdDivider.setBackground(new Colors().getColor("BackGray"));
		topThirdBottomPanel.setBackground(new Colors().getColor("BackGray"));

		topThirdBottomPanel.setLayout(new BorderLayout());
		topThirdBottomPanel.add(starredPanel, BorderLayout.WEST);
		topThirdBottomPanel.add(linkedPanel, BorderLayout.EAST);
		starredPanel.setPreferredSize(new Dimension(220, 40));
		linkedPanel.setPreferredSize(new Dimension(400, 40));
		starredPanel.setBorder(BorderFactory.createEtchedBorder());
		linkedPanel.setBorder(BorderFactory.createEtchedBorder());

		starredPanel.setBackground(new Colors().getColor("BackGray"));
		linkedPanel.setBackground(new Colors().getColor("BackGray"));
		JPanel midSpacer = new JPanel();
		midSpacer.setPreferredSize(new Dimension(650,25));
		midSpacer.setBackground(new Colors().getColor("BackGray"));
		midThird.add(midSpacer, BorderLayout.NORTH);
		midThird.add(descTextArea, BorderLayout.CENTER);
		descTextArea.setBorder(BorderFactory.createEtchedBorder());
		descTextArea.setBackground(new Colors().getColor("BackField"));
		descTextArea.setForeground(new Colors().getColor("InGreen"));
		descTextArea.setFont(Fonts.getFont("CreteRound-Regular", 15f));
		descTextArea.setLineWrap(true);
		descTextArea.setWrapStyleWord(true);
		setSelected(generalSettingsEntry);
	}

	public static void refreshPage() {
		if (selectedEntry != null && selectedEntry.getName().equals("General")) {
			rightPanel.add(general, BorderLayout.CENTER);
			rightPanel.repaint();
			
		} else {
			System.out.println("Fallback");
			if(current != null) {
			rightPanel.remove(current);
			}
			rightPanel.revalidate();
			rightPanel.repaint();
			
		}
		
	}

	public static void setSelected(SettingsMenuEntry e) {
		selectedEntry = e;
		e.setSelected(true);
		current = general;
		refreshPage();
	}

	public static void deselect(SettingsMenuEntry e) {
		if (e != null) {
			e.setSelected(false);
			selectedEntry = null;
			current = null;
			refreshPage();
		}
	}
}
