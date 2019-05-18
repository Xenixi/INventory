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
	static DevConsoleBackend backend = new DevConsoleBackend();
	public static void init() {
		devTextArea.setText("");
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
		consoleEnterField.setForeground(new Colors().getColor("BlueGreenTextMain"));
		consoleEnterField.setBorder(null);
		consoleEnterField.setFont(Fonts.getFont("CreteRound-Regular", 14f));
		devTextArea.setForeground(new Colors().getColor("BlueGreenTextMain"));
		devTextArea.setWrapStyleWord(true);
		devTextArea.setLineWrap(true);
		devTextArea.setFont(Fonts.getFont("CreteRound-Regular", 13f));
		devTextArea.setEditable(false);
		consoleEnterField.setCaretColor(new Colors().getColor("BlueGreenTextMain"));
		consoleEnterField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String cmd = consoleEnterField.getText();
					consoleEnterField.setText("");
					execute(cmd);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		appendLine(" INventory DevConsole V1.0.0:", true);
		appendLine(" *--------------------------------------------*", false);
	}
	public static void displayConsole() {
		if(devConFrame.isVisible()) {
			devConFrame.setVisible(false);
		} else {
		devConFrame.setVisible(true);
		consoleEnterField.requestFocus();
		}
	
		
	}
	private static void appendLine(String line, boolean sameLine) {
		StringBuilder sb = new StringBuilder();
		sb.append(devTextArea.getText());
		sb.append((sameLine ? "" : "\n") + line);
		devTextArea.setText(sb.toString());
		devTextArea.setCaretPosition(devTextArea.getDocument().getLength());
	}
	public static void printOut(String debug) {
		appendLine(" " + debug, false);
	}
	private static void execute(String cmd) {
		if(cmd.startsWith("^")) {
			appendLine("               " + cmd, false);
			return;
		}
		
		appendLine("    #" + cmd, false);
		if(!cmd.contains(":")) {
			appendLine(" !-Invalid Command Syntax!", false);
			appendLine(" Make sure you add ':' after every command!", false);
			return;
		}
		String cmdToCheck = cmd.split(":")[0];
		String cmdArgs = (cmd.split(":").length > 1) ? (cmd.split(":")[1]) : "";
		if(!backend.checkCMD(cmdToCheck)) {
			appendLine(" !-Command Not Recognized.", false);
		} else {
			backend.getCMD(cmdToCheck).runCMD(cmdArgs.split(" "));
		}
	
		
	}
	public static void clear() {
		devTextArea.setText("");
		appendLine(" INventory DevConsole V1.0.0:", true);
		appendLine(" *--------------------------------------------*", false);
	}
}
