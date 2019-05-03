package inventory.guitool;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	JPanel rightPanel = new JPanel();
	JPanel currentPanel = null;
	SettingsMenuEntry current = null;
	JFrame frame = new JFrame();
	Project p = null;

	public void displayFrame(Project p) {
		this.p = p;
		SettingsMenuEntry generalSettingsEntry = new SettingsMenuEntry("General", new Point(0, 4), p, this,
				new SettingsMenuCallable() {
					@Override
					public void onClick(SettingsMenuEntry e) {
						deselect(current);
						setSelected(e);
						System.out.println("General - clicked");
					}
				});

		SettingsMenuEntry tagsSettingsEntry = new SettingsMenuEntry("Tags", new Point(0, 28), p, this,
				new SettingsMenuCallable() {

					@Override
					public void onClick(SettingsMenuEntry e) {
						deselect(current);
						setSelected(e);
						System.out.println("Tags - clicked");
					}
				});
		SettingsMenuEntry advancedSettingsEntry = new SettingsMenuEntry("Advanced", new Point(0, 52), p, this,
				new SettingsMenuCallable() {

					@Override
					public void onClick(SettingsMenuEntry e) {
						deselect(current);
						setSelected(e);
						System.out.println("Advanced - clicked");
					}
				});

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

		leftPanel.add(generalSettingsEntry);
		leftPanel.add(tagsSettingsEntry);
		leftPanel.add(advancedSettingsEntry);

	
		rightPanel.setLayout(new BorderLayout());

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				resetAll();
			}
		});
		setSelected(generalSettingsEntry);
	}
	
	public void refreshPage() {
		if (current != null && current.getPanel() != null) {
			rightPanel.add(current.getPanel(), BorderLayout.CENTER);
			rightPanel.repaint();

		} else {
			System.out.println("Fallback");
			if (currentPanel != null) {
				rightPanel.remove(currentPanel);
			}
			rightPanel.revalidate();
			rightPanel.repaint();

		}
		frame.setTitle("Project Settings - " + p.getName());
	}
	public void refreshTitle() {
		frame.setTitle("Project Settings - " + p.getName());
	}

	public void setSelected(SettingsMenuEntry e) {
		e.setSelected(true);
		currentPanel = e.getPanel();
		current = e;
		refreshPage();
	}

	public void deselect(SettingsMenuEntry e) {
		System.out.println("Des");
		if (e != null) {
			e.setSelected(false);
			current = null;
			refreshPage();
		}
	}

	private static void resetAll() {
	}
}
