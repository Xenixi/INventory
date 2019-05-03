package inventory.gui.comp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import inventory.guitool.ProjectSettingsFrame;
import inventory.guitool.PromptFrame;
import inventory.interfaces.INventoryCallable;
import inventory.main.Colors;
import inventory.main.Fonts;
import inventory.main.Project;
import inventory.main.Projects;

public class ProjectListElement extends JPanel {
	static ArrayList<Project> selecting = new ArrayList<>();
	JTextField nameLabel = new JTextField();

	public JTextField getLabel() {
		return nameLabel;
	}

	Project project;

	public ProjectListElement(Project p) {
		this.project = p;
		this.setSize(new Dimension(275, 20));

		nameLabel.setFont(Fonts.getFont("OpenSans-semibold", 11f));
		this.setLayout(new BorderLayout());
		this.add(nameLabel, BorderLayout.WEST);
		this.setBackground(new Colors().getColor("ButtonsMain"));
		nameLabel.setSize(200, 17);
		nameLabel.setForeground(Color.WHITE);
		StringBuilder sb = new StringBuilder();
		if (p.getTags().length > 0) {
			sb.append("[");
			int i = 0;
			for (String tag : p.getTags()) {
				if (i == 0) {
					sb.append(tag);
					if (p.getTags().length > 1) {
						sb.append(", ");
					}

				} else if (i == 1) {
					sb.append(tag);
					if (p.getTags().length > 2) {
						sb.append("...");
					}
					break;
				}
				i++;
			}
			sb.append("]");
		}
		nameLabel.setText("   " + p.getName() + " " + sb.toString());
		nameLabel.setBackground(new Colors().getColor("BackGrayToneUp"));
		nameLabel.setBorder(null);
		nameLabel.setEditable(false);
		nameLabel.setPreferredSize(new Dimension(210, 20));
		nameLabel.setHorizontalAlignment(JTextField.CENTER);
		nameLabel.setForeground(new Colors().getColor("InGreen"));
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BorderLayout());
		this.add(buttonsPanel, BorderLayout.CENTER);
		buttonsPanel.setBackground(new Colors().getColor("ButtonsMain"));

		// buttons:

		JButton delButton = new JButton(), renButton = new JButton(), settingsButton = new JButton();

		buttonsPanel.add(delButton, BorderLayout.WEST);
		buttonsPanel.add(renButton, BorderLayout.CENTER);
		buttonsPanel.add(settingsButton, BorderLayout.EAST);

		delButton.setPreferredSize(new Dimension(20, 20));
		renButton.setPreferredSize(new Dimension(20, 20));
		settingsButton.setPreferredSize(new Dimension(20, 20));
		delButton.setBorder(BorderFactory.createEtchedBorder());
		renButton.setBorder(BorderFactory.createEtchedBorder());
		settingsButton.setBorder(BorderFactory.createEtchedBorder());

		delButton.setBackground(new Colors().getColor("ButtonsMain"));
		renButton.setBackground(new Colors().getColor("ButtonsMain"));
		settingsButton.setBackground(new Colors().getColor("ButtonsMain"));

		JPanel bufferPanel = new JPanel();
		bufferPanel.setPreferredSize(new Dimension(5, 20));
		this.add(bufferPanel, BorderLayout.EAST);
		bufferPanel.setBackground(new Colors().getColor("ButtonsMain"));

		delButton.setText("-");
		delButton.setFont(Fonts.getFont("CreteRound-Italic", 28f));
		delButton.setForeground(Color.red);

		renButton.setFont(Fonts.getFont("CreteRound-Italic", 18f));
		renButton.setForeground(new Colors().getColor("RenBlue"));
		renButton.setText("~");
		settingsButton.setFont(Fonts.getFont("CreteRound-Italic", 24f));
		settingsButton.setText("*");
		settingsButton.setForeground(new Colors().getColor("Settings"));

		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Projects.setSelected(p);
				Projects.delSelected();
			}
		});
		renButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PromptFrame().promptMultiInput("Rename Project: '" + p.getName() + "'", "Please enter a new project name:", new String[] {"Name"}, new int[] {0}, new ImageIcon("logo.png"), new INventoryCallable() {
					
					@Override
					public void execute(String[] args) {
						Projects.renProject(p, args[0]);
					}
					
					@Override
					public void cancelFallback() {
						
					}
				});
			}
		});
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ProjectSettingsFrame().displayFrame(p);
			}
		});
		nameLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isControlDown()) {
					Projects.addSelected(p);
				} else {
					Projects.setSelected(p);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		nameLabel.setToolTipText(p.getDesc().substring(0, p.getDesc().length() > 40 ? 40 : p.getDesc().length()) + (p.getDesc().length() > 40 ? "..." : ""));
		
		

	}

	public void refresh() {
		if (project.isSelected()) {
			
			nameLabel.setBackground(new Colors().getColor("ButtonsMainSelected"));
		} else {
			nameLabel.setBackground(new Colors().getColor("ButtonsMain"));
		}
		StringBuilder sb = new StringBuilder();
		if (project.getTags().length > 0) {
			sb.append("[");
			int i = 0;
			for (String tag : project.getTags()) {
				if (i == 0) {
					sb.append(tag);
					if (project.getTags().length > 1) {
						sb.append(", ");
					}

				} else if (i == 1) {
					sb.append(tag);
					if (project.getTags().length > 2) {
						sb.append("...");
					}
					break;
				}
				i++;
			}
			sb.append("]");
		}
		nameLabel.setText("   " + project.getName() + " " + sb.toString());
	}
}
