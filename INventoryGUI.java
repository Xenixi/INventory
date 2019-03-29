package inventory.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class INventoryGUI extends JFrame {
	public INventoryGUI() {
		start();
	}

	public void start() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// All the stuffs ;) *_* -_- (: *-*

				JFrame menuFrame = new JFrame();
				JPanel menuPanelMain = new JPanel();
				// left:
				JPanel leftBackbonePanel = new JPanel();
				// in above panel:
				JPanel subAndContain = new JPanel();
				JPanel mainControlMenuPanel = new JPanel();
				JPanel subControlMenuPanel = new JPanel();
				JPanel projectsContainerPanel = new JPanel();

				///
				// right:
				JPanel rightBackbonePanel = new JPanel();
				// in above panel:
				JPanel searchPanel = new JPanel();
				JPanel searchResultsEditPanel = new JPanel();
				///

				// testing purposes
				mainControlMenuPanel.setBackground(new Colors().getColor("BackGray"));
				subControlMenuPanel.setBackground(new Colors().getColor("BackGray"));
				projectsContainerPanel.setBackground(new Colors().getColor("BackGray"));

				searchPanel.setBackground(new Colors().getColor("BackGray"));
				searchResultsEditPanel.setBackground(new Colors().getColor("BackGray"));
				///
				menuFrame.setLocationRelativeTo(null);
				menuFrame.setLocation(50, 50);
				menuFrame.setSize(1300, 800);
				menuFrame.setVisible(true);
				menuFrame.setTitle("INventory - Elite Edition | " + ConfigEdit.getValue("Username") + " ["
						+ ConfigEdit.getValue("Organization") + "]");
				menuFrame.setIconImage(new ImageIcon("logo.png").getImage());
				menuFrame.add(menuPanelMain);
				menuFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				menuPanelMain.setBackground(new Colors().getColor("BackGray"));
				menuPanelMain.setLayout(new BorderLayout());
				menuFrame.add(leftBackbonePanel, BorderLayout.WEST);
				menuFrame.add(rightBackbonePanel, BorderLayout.CENTER);
				leftBackbonePanel.setLayout(new BorderLayout());
				rightBackbonePanel.setLayout(new BorderLayout());
				subAndContain.setLayout(new BorderLayout());
				leftBackbonePanel.add(mainControlMenuPanel, BorderLayout.NORTH);

				subAndContain.add(subControlMenuPanel, BorderLayout.NORTH);
				subAndContain.add(projectsContainerPanel, BorderLayout.CENTER);
				leftBackbonePanel.add(subAndContain, BorderLayout.CENTER);
				rightBackbonePanel.add(searchPanel, BorderLayout.NORTH);
				rightBackbonePanel.add(searchResultsEditPanel, BorderLayout.CENTER);

				leftBackbonePanel.setPreferredSize(new Dimension(275, 800));
				mainControlMenuPanel.setPreferredSize(new Dimension(275, 50));
				subControlMenuPanel.setPreferredSize(new Dimension(275, 30));
				searchPanel.setPreferredSize(new Dimension(1025, 50));

				// borders:
				leftBackbonePanel
						.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, new Colors().getColor("BorderLight")));
				mainControlMenuPanel
						.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Colors().getColor("BorderLight")));
				subControlMenuPanel
						.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Colors().getColor("BorderLight")));
				searchPanel
						.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Colors().getColor("BorderLight")));

				// other UI
				searchPanel.setLayout(new BorderLayout());
				JTextField searchBarField = new JTextField();
				JPanel splitViewPanel = new JPanel();
				JPanel fillerLeftSearchPanel = new JPanel();
				searchPanel.add(fillerLeftSearchPanel, BorderLayout.WEST);
				searchPanel.add(searchBarField, BorderLayout.CENTER);
				searchPanel.add(splitViewPanel, BorderLayout.EAST);
				fillerLeftSearchPanel.setPreferredSize(new Dimension(20, 30));
				splitViewPanel.setPreferredSize(new Dimension(60, 30));
				searchBarField.setBorder(null);

				fillerLeftSearchPanel.setBackground(new Colors().getColor("BackField"));
				searchBarField.setBackground(new Colors().getColor("BackField"));
				splitViewPanel.setBackground(new Colors().getColor("BackGray"));

				splitViewPanel
						.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, new Colors().getColor("BorderLight")));

				searchBarField.setForeground(new Colors().getColor("lighttext"));
				searchBarField.setFont(Fonts.getFont("OpenSans-lightitalic", 26f));
				searchBarField.setText("Search...");

				mainControlMenuPanel.setLayout(new BorderLayout());

				JPanel displayNamePanel = new JPanel();
				mainControlMenuPanel.add(displayNamePanel, BorderLayout.CENTER);
				JPanel mainButtonsSubpanel = new JPanel();
				mainControlMenuPanel.add(mainButtonsSubpanel, BorderLayout.EAST);
				mainButtonsSubpanel.setPreferredSize(new Dimension(75, 50));

				JButton saveAllButton = new JButton();
				JButton syncButton = new JButton();
				JButton profileButton = new JButton();

				mainButtonsSubpanel.setLayout(new BorderLayout());
				mainButtonsSubpanel.add(saveAllButton, BorderLayout.CENTER);
				mainButtonsSubpanel.add(profileButton, BorderLayout.WEST);
				mainButtonsSubpanel.add(syncButton, BorderLayout.EAST);
				profileButton.setPreferredSize(new Dimension(25, 50));
				syncButton.setPreferredSize(new Dimension(25, 50));
				saveAllButton.setPreferredSize(new Dimension(25, 50));

				profileButton.setBackground(new Colors().getColor("ButtonsMain"));
				profileButton.setBorder(BorderFactory.createEtchedBorder());
				syncButton.setBackground(new Colors().getColor("ButtonsMain"));
				syncButton.setBorder(BorderFactory.createEtchedBorder());
				saveAllButton.setBackground(new Colors().getColor("ButtonsMain"));
				saveAllButton.setBorder(BorderFactory.createEtchedBorder());
				displayNamePanel.setBackground(new Colors().getColor("BackGray"));
				displayNamePanel.setLayout(new BorderLayout());
				JTextField profileNameLabel = new JTextField();
				profileNameLabel.setForeground(new Colors().getColor("InGreen"));
				profileNameLabel.setFont(Fonts.getFont("CreteRound-Regular", 18f));
				StringBuilder abbreviationSB = new StringBuilder();
				for (String word : ConfigEdit.getValue("Organization").split(" ")) {
					abbreviationSB.append(word.charAt(0));
				}
				String abbreviationFinal = abbreviationSB.toString();

				profileNameLabel.setText(ConfigEdit.getValue("Username") + " [" + abbreviationFinal + "]");
				profileNameLabel.setHorizontalAlignment(JTextField.CENTER);
				profileNameLabel.setBorder(null);
				profileNameLabel.setEditable(false);
				profileNameLabel.setBackground(new Colors().getColor("BackGray"));
				displayNamePanel.add(profileNameLabel, BorderLayout.CENTER);

				profileButton.setText("P");
				syncButton.setText("s^");
				saveAllButton.setText("s");

				profileButton.setForeground(new Colors().getColor("InGreen"));
				syncButton.setForeground(new Colors().getColor("InGreen"));
				saveAllButton.setForeground(new Colors().getColor("InGreen"));
				profileButton.setFont(Fonts.getFont("CreteRound-Italic", 20f));

				saveAllButton.setFont(Fonts.getFont("CreteRound-Italic", 20f));

				syncButton.setFont(Fonts.getFont("CreteRound-Italic", 20f));

				JButton newButton = new JButton(), delButton = new JButton(), colorButton = new JButton();
				JPanel subSubMenuButtonsPanel = new JPanel();
				subControlMenuPanel.setLayout(new BorderLayout());
				subControlMenuPanel.add(subSubMenuButtonsPanel, BorderLayout.EAST);
				subSubMenuButtonsPanel.setPreferredSize(new Dimension(75, 30));
				subSubMenuButtonsPanel.setLayout(new BorderLayout());
				subSubMenuButtonsPanel.add(newButton, BorderLayout.WEST);
				subSubMenuButtonsPanel.add(delButton, BorderLayout.CENTER);
				subSubMenuButtonsPanel.add(colorButton, BorderLayout.EAST);
				newButton.setPreferredSize(new Dimension(25, 30));
				delButton.setPreferredSize(new Dimension(25, 30));
				colorButton.setPreferredSize(new Dimension(25, 30));
				newButton.setBackground(new Colors().getColor("ButtonsMain"));
				delButton.setBackground(new Colors().getColor("ButtonsMain"));
				colorButton.setBackground(new Colors().getColor("ButtonsMain"));
				newButton.setBorder(BorderFactory.createEtchedBorder());
				delButton.setBorder(BorderFactory.createEtchedBorder());
				colorButton.setBorder(BorderFactory.createEtchedBorder());

				newButton.setText("+");
				delButton.setText("-");
				colorButton.setText("c");

				newButton.setFont(Fonts.getFont("CreteRound-Italic", 17f));
				delButton.setFont(Fonts.getFont("CreteRound-Italic", 17f));
				colorButton.setFont(Fonts.getFont("CreteRound-Italic", 17f));

				newButton.setForeground(new Colors().getColor("InGreen"));
				delButton.setForeground(new Colors().getColor("InGreen"));
				colorButton.setForeground(new Colors().getColor("InGreen"));

				JTextField searchProjectsField = new JTextField();
				subControlMenuPanel.add(searchProjectsField, BorderLayout.CENTER);
				JPanel projectsSearchBufferPanel = new JPanel();
				projectsSearchBufferPanel.setBackground(new Colors().getColor("BackField"));
				subControlMenuPanel.add(projectsSearchBufferPanel, BorderLayout.WEST);
				projectsSearchBufferPanel.setPreferredSize(new Dimension(10, 30));

				searchProjectsField.setBorder(null);
				searchProjectsField.setText("Search...");
				searchProjectsField.setBackground(new Colors().getColor("BackField"));
				searchProjectsField.setForeground(new Colors().getColor("lighttext"));
				searchProjectsField.setFont(Fonts.getFont("OpenSans-lightitalic", 17f));

				searchProjectsField.addMouseListener(new MouseListener() {
					boolean isSelected = false;

					@Override
					public void mouseClicked(MouseEvent e) {
						if (!isSelected) {
							searchProjectsField.setSelectionStart(0);
							searchProjectsField.setSelectionEnd(searchProjectsField.getText().length());
							isSelected = true;
						} else {
							searchProjectsField.setSelectionEnd(0);
							isSelected = false;
						}
					}

					@Override
					public void mousePressed(MouseEvent e) {

					}

					@Override
					public void mouseReleased(MouseEvent e) {

					}

					@Override
					public void mouseEntered(MouseEvent e) {
						searchProjectsField.setBackground(new Colors().getColor("BackFieldSelected"));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						searchProjectsField.setBackground(new Colors().getColor("BackField"));
					}

				});
				searchBarField.addMouseListener(new MouseListener() {
					boolean isSelected = false;

					@Override
					public void mouseClicked(MouseEvent e) {
						if (!isSelected) {
							searchBarField.setSelectionStart(0);
							searchBarField.setSelectionEnd(searchBarField.getText().length());
							isSelected = true;
						} else {
							searchBarField.setSelectionEnd(0);
							isSelected = false;
						}
					}

					@Override
					public void mousePressed(MouseEvent e) {

					}

					@Override
					public void mouseReleased(MouseEvent e) {

					}

					@Override
					public void mouseEntered(MouseEvent e) {
						searchBarField.setBackground(new Colors().getColor("BackFieldSelected"));
						fillerLeftSearchPanel.setBackground(new Colors().getColor("BackFieldSelected"));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						searchBarField.setBackground(new Colors().getColor("BackField"));
						fillerLeftSearchPanel.setBackground(new Colors().getColor("BackField"));
					}

				});
				searchProjectsField.setCaretColor(new Colors().getColor("lighttext"));
				searchBarField.setCaretColor(new Colors().getColor("lighttext"));
				projectsContainerPanel.setLayout(new BorderLayout());
				projectsContainerPanel.add(Projects.getProjectPanel(), BorderLayout.CENTER);
				
				for(int i = 0; i<500; i++) {
					
					Projects.createProject("TestName" + i, true, "Just some stuffs.");
				}
				

			}
		});
	}
}
