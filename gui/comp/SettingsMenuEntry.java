package inventory.gui.comp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import inventory.interfaces.SettingsMenuCallable;
import inventory.main.Colors;
import inventory.main.Fonts;

public class SettingsMenuEntry extends JPanel{
	static JPanel rightPanelSub = new JPanel(), general = new JPanel(), tags = new JPanel(), advanced = new JPanel();
	JTextField label = new JTextField();
	String name = null;
	public SettingsMenuEntry(String title, Point loc, SettingsMenuCallable onClickCall) {
		this.setLocation(loc);
		this.name = title;
		
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(147,20));
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
		if(selected) {
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
		projTitleField.setBackground(new Colors().getColor("BackField"));
		projTitleField.setFont(Fonts.getFont("CreteRound-Regular", 45f));
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
		midSpacer.setPreferredSize(new Dimension(650,25));
		midSpacer.setBackground(new Colors().getColor("BackGray"));
		midThird.add(midSpacer, BorderLayout.NORTH);
		midThird.add(descTextArea, BorderLayout.CENTER);
		descTextArea.setBorder(BorderFactory.createEtchedBorder());
		descTextArea.setBackground(new Colors().getColor("BackField"));
		descTextArea.setForeground(new Colors().getColor("BlueGreenTextMain"));
		descTextArea.setFont(Fonts.getFont("CreteRound-Regular", 15f));
		descTextArea.setLineWrap(true);
		descTextArea.setWrapStyleWord(true);
		descTextArea.setCaretColor(new Colors().getColor("InGreen"));
		
		
		JLabel descLabel = new JLabel();
		midSpacer.setLayout(null);
		midSpacer.add(descLabel);
	
		descLabel.setLocation(0,2);
		descLabel.setSize(new Dimension(120,20));
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
		///////////////////////////
		
		
		
		switch(name) {
		case "General" : return rightPanelSub;
		default : return null;
		}
	}
}
