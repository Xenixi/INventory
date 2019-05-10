package inventory.main.util.dev;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import inventory.main.Colors;
import inventory.main.Fonts;

public class DevConsole extends JFrame{
	static final JFrame devConFrame = new JFrame();
	static final JTextArea devTextArea = new JTextArea();
	static final JTextField consoleEnterField = new JTextField();
	static final JScrollPane scroll = new JScrollPane(devTextArea);
	public static void init() {
		devConFrame.setSize(450,600);
		devConFrame.setTitle("Developer Console (CTRL+SHIFT+D)");
		devConFrame.setLocationRelativeTo(null);
		devConFrame.setLayout(new BorderLayout());
		devConFrame.setIconImage(new ImageIcon("logo.png").getImage());
		devConFrame.add(consoleEnterField, BorderLayout.SOUTH);
		consoleEnterField.setPreferredSize(new Dimension(300, 20));
		devConFrame.add(scroll, BorderLayout.CENTER);
		
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		devConFrame.setVisible(false);
		devConFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		devTextArea.setBackground(new Colors().getColor("BackGray"));
		consoleEnterField.setBackground(new Colors().getColor("ButtonsMain"));
		consoleEnterField.setForeground(new Colors().getColor("InGreen"));
		consoleEnterField.setBorder(null);
		consoleEnterField.setFont(Fonts.getFont("CreteRound-Regular", 15f));
		devTextArea.setForeground(new Colors().getColor("BlueGreenTextMain"));
		devTextArea.setWrapStyleWord(true);
		devTextArea.setLineWrap(true);
		devTextArea.setFont(Fonts.getFont("CreteRound-Regular", 13f));
		devTextArea.setEditable(false);
		
		consoleEnterField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String cmd = consoleEnterField.getText();
					consoleEnterField.setText("");
					appendLine("CMD: " + cmd, false);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		appendLine("DevConsole V1.0.0: Enter Command:", true);
	}
	public static void displayConsole() {
		if(devConFrame.isVisible()) {
			devConFrame.setVisible(false);
		} else {
		devConFrame.setVisible(true);
		}
	
		
	}
	private static void appendLine(String line, boolean sameLine) {
		StringBuilder sb = new StringBuilder();
		sb.append(devTextArea.getText());
		sb.append((sameLine ? "" : "\n") + line);
		devTextArea.setText(sb.toString());
	}
	public static void printOut(String debug) {
		
	}
}
