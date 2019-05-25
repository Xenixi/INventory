package inventory.gui.comp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;

import inventory.guitool.ProjectSettingsFrame;
import inventory.guitool.PromptFrame;
import inventory.interfaces.INventoryCallable;
import inventory.interfaces.SettingsMenuCallable;
import inventory.main.Colors;
import inventory.main.Fonts;
import inventory.main.Project;
import inventory.main.Projects;
import inventory.main.util.TimeMonitor;
import inventory.main.util.dev.DevConsole;

public class SettingsMenuEntry extends JPanel {
	JPanel rightPanelSub = new JPanel(), general = new JPanel(), tags = new JPanel(), advanced = new JPanel();
	JTextField label = new JTextField();
	String name = null;
	Project p = null;
	ProjectSettingsFrame psf = null;

	public SettingsMenuEntry(String title, Point loc, Project p, ProjectSettingsFrame psf,
			SettingsMenuCallable onClickCall) {
		this.setLocation(loc);
		this.name = title;
		this.p = p;
		this.psf = psf;
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(147, 20));
		this.add(label, BorderLayout.CENTER);
		label.setText(title);
		label.setFont(Fonts.getFont("OpenSans-regular", 13f));
		label.setBackground(new Colors().getColor("ButtonsMain"));
		label.setForeground(new Colors().getColor("lighttext"));
		label.setBorder(null);
		label.setEditable(false);
		label.setHorizontalAlignment(SwingConstants.CENTER);

		SettingsMenuEntry sme = this;
		label.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				onClickCall.onClick(sme);
			}
		});
	}

	public void setSelected(boolean selected) {
		if (selected) {
			label.setBackground(new Colors().getColor("ButtonsMainSelected"));
		} else {
			label.setBackground(new Colors().getColor("ButtonsMain"));
		}
	}

	public String getName() {
		return name;
	}

	public JPanel getPanel() {
		
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
		JScrollPane descScroll = new JScrollPane(descTextArea);
		UIManager.put("ScrollBar.track", new ColorUIResource(new Colors().getColor("BackGray")));
		UIManager.put("ScrollBar.trackHighlight", new ColorUIResource(new Colors().getColor("BackGray")));
		UIManager.put("ScrollBar.thumb", new ColorUIResource(new Colors().getColor("BackField")));
		UIManager.put("ScrollBar.thumbHighlight", new ColorUIResource(new Colors().getColor("BackFieldSelected")));
		UIManager.put("ScrollBar.thumbShadow", new ColorUIResource(new Colors().getColor("BackGrayToneUp")));
		UIManager.put("ScrollBar.thumbDarkShadow", new ColorUIResource(new Colors().getColor("BackGray")));
		UIManager.put("ScrollBar.foreground", new ColorUIResource(new Colors().getColor("BackGray")));
		// UIManager.put("ScrollBar.background", new ColorUIResource(Color.black));
		descScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		descScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		descScroll.setBorder(BorderFactory.createEtchedBorder());
		descScroll.setBackground(new Colors().getColor("BackGray"));
		descScroll.setForeground(new Colors().getColor("BackGray"));
		descScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI());

		//
		// lower third:
		// main subpanels
		JPanel tagsPanel = new JPanel(), refPanel = new JPanel(), imagesPanel = new JPanel();
		// sub tags:
		JPanel addDelTagsPanel = new JPanel(), searchTagsPanel = new JPanel(), tagsPanelMain = new JPanel();
		JTextField searchTagsField = new JTextField();
		// sub ref:
		JPanel addDelRefPanel = new JPanel(), searchRefPanel = new JPanel(), refFilesPanel = new JPanel();
		JTextField searchRefFilesField = new JTextField();
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

		left.setPreferredSize(new Dimension(25, 550));
		right.setPreferredSize(new Dimension(25, 550));
		top.setPreferredSize(new Dimension(650, 25));
		bottom.setPreferredSize(new Dimension(650, 25));
		rightPanelSub.setLayout(new BorderLayout());
		rightPanelSub.add(left, BorderLayout.WEST);
		rightPanelSub.add(right, BorderLayout.EAST);
		rightPanelSub.add(top, BorderLayout.NORTH);
		rightPanelSub.add(bottom, BorderLayout.SOUTH);
		rightPanelSub.add(general);
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
		projTitleField.setBackground(new Colors().getColor("BackFieldDark"));
		projTitleField.setFont(Fonts.getFont("CreteRound-Regular", 55f));
		projTitleField.setForeground(new Colors().getColor("BlueGreenTextMain"));
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
		midSpacer.setPreferredSize(new Dimension(650, 25));
		midSpacer.setBackground(new Colors().getColor("BackGray"));
		midThird.add(midSpacer, BorderLayout.NORTH);
		midThird.add(descScroll, BorderLayout.CENTER);

		descTextArea.setBackground(new Colors().getColor("BackFieldDark"));
		descTextArea.setForeground(new Colors().getColor("BlueGreenTextMain"));
		descTextArea.setFont(Fonts.getFont("CreteRound-Regular", 15f));
		descTextArea.setLineWrap(true);
		descTextArea.setWrapStyleWord(true);
		descTextArea.setCaretColor(new Colors().getColor("InGreen"));

		JLabel descLabel = new JLabel();
		midSpacer.setLayout(null);
		midSpacer.add(descLabel);

		descLabel.setLocation(0, 2);
		descLabel.setSize(new Dimension(120, 20));
		descLabel.setText("Description:");
		descLabel.setForeground(new Colors().getColor("InGreen"));
		descLabel.setFont(Fonts.getFont("CreteRound-Regular", 15f));

		JLabel titleLabel = new JLabel();
		titleLabel.setFont(Fonts.getFont("CreteRound-Regular", 15f));
		titleLabel.setForeground(new Colors().getColor("InGreen"));
		titleLabel.setSize(160, 20);
		titleLabel.setText("Project Name:");
		titleLabel.setLocation(25, 2);
		top.setLayout(null);
		// setup

		///////////////////////////
		projTitleField.setToolTipText("Title cannot contain any of the following characters: /\\*:?><\"|");

		projTitleField.addKeyListener(new KeyAdapter() {
			String lastChange = p.getName();
			volatile TimeMonitor monitor = new TimeMonitor();
			boolean firstTime = true;

			@Override
			public void keyReleased(KeyEvent e) {
				monitor.startTime();
				if (firstTime) {
					Thread titleFieldAutosave = new Thread(new Runnable() {

						public void run() {
							while (true) {
								if (monitor.getElapsedTimeMs() > 550) {
									/////
									boolean untitled = false;
									if (Projects.validateProjName(projTitleField.getText())) {
										if (projTitleField.getText().length() > 0) {
											Projects.renProject(p, projTitleField.getText());
										} else {
											Projects.renProject(p, "untitled#" + new Random().nextInt());
											untitled = true;
										}
										lastChange = projTitleField.getText();
										psf.refreshTitle();

									} else {
										projTitleField.setText(lastChange);

									}
									projTitleField.requestFocus();
									/////
									firstTime = true;
									return;
								}
							}
						}
					});
					firstTime = false;
					titleFieldAutosave.start();

				}
			}

		});
		descTextArea.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				Projects.setProjectDesc(p, descTextArea.getText());
			}
		});

		lowerThird.setBackground(Color.green);

		///////////////////////////////////////////////////////////////////////////////// *********************************************************************
		JPanel lowerDivider = new JPanel();
		lowerDivider.setPreferredSize(new Dimension(0, 30));
		lowerThird.add(lowerDivider, BorderLayout.NORTH);
		lowerDivider.setBackground(new Colors().getColor("BackGray"));
		JPanel lowerThirdMain = new JPanel();
		lowerThirdMain.setLayout(new BorderLayout());
		lowerThird.add(lowerThirdMain, BorderLayout.CENTER);
		JPanel leftLower = new JPanel(), rightLower = new JPanel();
		lowerThirdMain.setLayout(new BorderLayout());
		lowerThirdMain.add(leftLower, BorderLayout.WEST);
		lowerThirdMain.add(rightLower, BorderLayout.CENTER);

		leftLower.setPreferredSize(new Dimension(400, 0));
		leftLower.setBackground(Color.red);
		leftLower.setLayout(new BorderLayout());
		leftLower.add(tagsPanelMain, BorderLayout.WEST);
		leftLower.add(refFilesPanel, BorderLayout.EAST);

		JPanel leftLowerDivider = new JPanel();
		JPanel rightLowerDivider = new JPanel();
		rightLowerDivider.setPreferredSize(new Dimension(20, 0));
		leftLower.add(leftLowerDivider, BorderLayout.CENTER);
		tagsPanelMain.setPreferredSize(new Dimension(200, 0));
		refFilesPanel.setPreferredSize(new Dimension(180, 0));

		tagsPanelMain.setBorder(null);
		refFilesPanel.setBorder(BorderFactory.createEtchedBorder());

		leftLowerDivider.setBackground(new Colors().getColor("BackGray"));
		tagsPanelMain.setBackground(new Colors().getColor("BackGray"));
		refFilesPanel.setBackground(new Colors().getColor("BackGray"));
		rightLower.setLayout(new BorderLayout());
		rightLower.add(imagesMainPanel, BorderLayout.CENTER);
		rightLower.add(rightLowerDivider, BorderLayout.WEST);
		rightLowerDivider.setBackground(new Colors().getColor("BackGray"));
		imagesMainPanel.setBackground(new Colors().getColor("BackGray"));
		imagesMainPanel.setBorder(BorderFactory.createEtchedBorder());

		JPanel lowerTagsPanel = new JPanel(), midTagsPanel = new JPanel(), topTagsPanel = new JPanel();
		
		JScrollPane tagsScroll = new JScrollPane(lowerTagsPanel);
		tagsScroll.setBackground(new Colors().getColor("BackGray"));
		tagsScroll.setBorder(BorderFactory.createEtchedBorder());
		tagsScroll.setBackground(new Colors().getColor("BackGray"));
		tagsScroll.setForeground(new Colors().getColor("BackGray"));
		tagsScroll.setPreferredSize(new Dimension(200,190));
		tagsScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI());
		tagsScroll.getVerticalScrollBar().setPreferredSize(new Dimension(10,0));
		tagsScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tagsPanelMain.setLayout(new BorderLayout());
		tagsPanelMain.add(tagsScroll, BorderLayout.SOUTH);
		tagsPanelMain.add(topTagsPanel, BorderLayout.NORTH);
		tagsPanelMain.add(midTagsPanel, BorderLayout.CENTER);

		topTagsPanel.setPreferredSize(new Dimension(200, 25));
		midTagsPanel.setBackground(new Colors().getColor("BackGray"));
		midTagsPanel.setBorder(null);
		
		topTagsPanel.setBorder(null);
		topTagsPanel.setBackground(new Colors().getColor("BackGray"));
		lowerTagsPanel.setBackground(new Colors().getColor("BackGray"));

		topTagsPanel.setLayout(new BorderLayout());
		
		JPanel topTagsSubpanelLeft = new JPanel(), topTagsSubpanelRight = new JPanel();
		topTagsPanel.add(topTagsSubpanelLeft, BorderLayout.WEST);
		topTagsPanel.add(topTagsSubpanelRight, BorderLayout.EAST);
		JPanel topTagsPanelDivider = new JPanel();
		topTagsPanel.add(topTagsPanelDivider, BorderLayout.CENTER);

		topTagsPanelDivider.setBorder(null);
		topTagsPanelDivider.setBackground(new Colors().getColor("BackGray"));
		topTagsSubpanelLeft.setPreferredSize(new Dimension(45, 0));
		topTagsSubpanelRight.setPreferredSize(new Dimension(150, 0));

		topTagsSubpanelLeft.setBorder(BorderFactory.createEtchedBorder());
		topTagsSubpanelRight.setBorder(BorderFactory.createEtchedBorder());
		topTagsSubpanelLeft.setBackground(new Colors().getColor("BackGray"));
		topTagsSubpanelRight.setBackground(new Colors().getColor("BackGray"));
		lowerTagsPanel.setLayout(null);
		

		final class GeneralTags {
			protected void refreshTags(String[] tags) {
				lowerTagsPanel.removeAll();
				lowerTagsPanel.validate();
				lowerTagsPanel.repaint();
				
				int row = 0;
				int num = 0;
				for (String tag: tags) {
					TagMiniPanel tmp = new TagMiniPanel(tag.substring(0, Math.min(tag.length(), 5)), /*new Color(new Random().nextInt(20) * 12,
							new Random().nextInt(20) * 12, new Random().nextInt(20) * 12)*/ new Color(200,20,220));
					tmp.setLocation((num * 45) + 12 * (num + 1), row * 20 + 10 * (row + 1));
					if (num < 2) {
						num++;
					} else {
						num = 0;
						row++;
					}
					lowerTagsPanel.setPreferredSize(new Dimension(200, (row*20+10*(row+1)) + 30));
					lowerTagsPanel.add(tmp);
					lowerTagsPanel.validate();
					lowerTagsPanel.repaint();
				}
			}
		}
		GeneralTags gt = new GeneralTags();
		gt.refreshTags(p.getTags());
		topTagsSubpanelRight.setLayout(new BorderLayout());
		topTagsSubpanelRight.add(searchTagsField, BorderLayout.CENTER);
		searchTagsField.setBorder(null);
		searchTagsField.setBackground(new Colors().getColor("BackFieldDark"));
		searchTagsField.setForeground(new Colors().getColor("BlueGreenTextMain"));
		searchTagsField.setFont(Fonts.getFont("CreteRound-Regular", 13f));
		
		searchTagsField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				ArrayList<String> searchNarrowList = new ArrayList<>();
				for(String tag: p.getTags()) {
					if(tag.contains(searchTagsField.getText())) {
						searchNarrowList.add(tag);
					}
				}
				String[] tagsSearched = new String[searchNarrowList.size()];
				tagsSearched = searchNarrowList.toArray(tagsSearched);
				gt.refreshTags(tagsSearched);
			}
		});
		
		topTagsSubpanelLeft.setLayout(new BorderLayout());
		topTagsSubpanelLeft.add(addDelTagsPanel, BorderLayout.CENTER);
		JButton addTagsButton = new JButton(), removeTagsButton = new JButton();
		
		addDelTagsPanel.setLayout(new BorderLayout());
		addDelTagsPanel.add(removeTagsButton, BorderLayout.WEST);
		addDelTagsPanel.add(addTagsButton, BorderLayout.CENTER);
		removeTagsButton.setPreferredSize(new Dimension(15,0));
		addTagsButton.setBorder(BorderFactory.createEtchedBorder());
		addTagsButton.setBackground(new Colors().getColor("ButtonsMain"));
		removeTagsButton.setBorder(BorderFactory.createEtchedBorder());
		removeTagsButton.setBackground(new Colors().getColor("ButtonsMain"));
		
		addTagsButton.setText("+");
		removeTagsButton.setText("-");
		addTagsButton.setForeground(new Colors().getColor("InGreen"));
		removeTagsButton.setForeground(new Colors().getColor("RED"));
		addTagsButton.setFont(Fonts.getFont("CreteRound-Regular", 15f));
		removeTagsButton.setFont(Fonts.getFont("CreteRound-Regular", 19f));
		addTagsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PromptFrame pf = new PromptFrame();
				pf.promptMultiInput("Create a Tag", "Enter a tag name to create a new tag", new String[] {"Tag Name"}, new int[] {0}, new ImageIcon("logo.png"), new INventoryCallable() {
					
					@Override
					public void execute(String[] args) {
						Projects.writeTagAdd(p, args[0]);
						gt.refreshTags(p.getTags());
						DevConsole.printOut("Tag created: " + args[0]);
					}
					
					@Override
					public void cancelFallback() {
						DevConsole.printOut("Tag creation operation canceled.");
					}
				});
			}
		});

		// ****//

		switch (name) {
		case "General": {
			projTitleField.setText(p.getName());
			descTextArea.setText(p.getDesc());

			return rightPanelSub;
		}
		default:
			return null;
		}
	}
}
