package inventory.main;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

import inventory.guitool.PromptFrame;
import inventory.interfaces.INventoryCallable;
import inventory.main.util.TimeMonitor;
import inventory.main.util.dev.DevConsole;

public class ActionManager {
	private static boolean enabled = false;
	static KeyEventDispatcher dispatcherMain = new KeyEventDispatcher() {
		TimeMonitor timer = new TimeMonitor();
		boolean exitCall = false;
		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {
			if(e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				Projects.setSelected(null);
			} else if(e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_D && e.isControlDown() && e.isShiftDown()) {
				System.err.println("DevConsole");
				DevConsole.displayConsole();
			} else if(e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_X && e.isControlDown() && e.isShiftDown()) {
				if(!exitCall) {
					exitCall = true;
					timer.startTime();
				} else if(timer.getElapsedTimeMs() <= 250) {
					System.exit(0);
				} else {
					exitCall = false;
				}
				
				}
			return false;
		}
	};
	static HashMap<int[], INventoryCallable> keyMap = new HashMap<>();
	public static final ActionListener NEW_PROJECT = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			PromptFrame prompt = new PromptFrame();
			prompt.promptMultiInput("New Project", "Please enter project details",
					new String[] { "Name", "Description", "Tags (,)" }, new int[] { 0 },
					new ImageIcon("GUI/Icon/NewIcon.png"), new INventoryCallable() {
						public void execute(String[] args) {
							
							String[] tags = (args[2].startsWith(",") ? args[2].substring(1): args[2]).replace(" ", "").toLowerCase().split(",");
							Projects.createProject(args[0], true, args[1],
									tags[0].equalsIgnoreCase("") ? new String[0] : tags);

						}

						public void cancelFallback() {
							DevConsole.printOut("Operation canceled");
						}
					});

		}
	};
}
