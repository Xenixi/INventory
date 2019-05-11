package inventory.guitool;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import inventory.interfaces.INventoryCallable;
import inventory.main.Colors;
import inventory.main.Fonts;
import inventory.main.util.dev.DevConsole;

public class PromptFrame extends JFrame {
	private String[] toReturn;
	JPanel mainPanel = new JPanel();
	JTextPane msgLabel = new JTextPane();
	private static final long serialVersionUID = 1L;
	int[] require;
	INventoryCallable call;
	JTextField[] entryFields = null;
	KeyEventDispatcher dispatcher = null;

	int verticle = 65;

	public PromptFrame(Point loc) {
		this.setLocation(loc);
		this.setVisible(false);
	}

	public PromptFrame() {
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}

	public String[] promptMultiInput(String title, String message, String[] fields, int[] require, ImageIcon icon,
			INventoryCallable call) {
		LinkedHashMap<String, Dimension> fieldsMap = new LinkedHashMap<>();
		for (String field : fields) {
			fieldsMap.put(field, new Dimension(200, 25));
		}

		promptMultiInput(title, message, fieldsMap, require, icon, call);
		return null;
	}

	public void promptMultiInput(String title, String message, LinkedHashMap<String, Dimension> fields, int[] require,
			ImageIcon icon, INventoryCallable call) {
		this.require = require;
		this.call = call;

		setup(title, message, icon);
		entryFields = new JTextField[fields.keySet().size()];
		JTextField[] labels = new JTextField[entryFields.length];

		int i = 0;
		for (Entry e : fields.entrySet()) {
			String name = (String) e.getKey();
			Dimension dimension = (Dimension) e.getValue();
			entryFields[i] = new JTextField();
			mainPanel.add(entryFields[i]);
			entryFields[i].setSize(dimension);
			entryFields[i].setLocation((400 - (int) dimension.getWidth()) / 2, verticle);
			entryFields[i].setBackground(new Colors().getColor("BackField"));
			entryFields[i].setBorder(BorderFactory.createEtchedBorder(new Colors().getColor("Border"),
					new Colors().getColor("BackField")));
			entryFields[i].setFont(Fonts.getFont("CreteRound-Regular", 13f));
			entryFields[i].setForeground(new Colors().getColor("FieldText"));
			entryFields[i].setHorizontalAlignment(SwingConstants.CENTER);
			entryFields[i].setCaretColor(new Colors().getColor("FieldText"));
			labels[i] = new JTextField();
			labels[i].setText(name);
			labels[i].setFont(Fonts.getFont("CreteRound-Regular", 13f));
			labels[i].setForeground(new Colors().getColor("InGreen"));
			labels[i].setBackground(new Colors().getColor("BackGray"));
			mainPanel.add(labels[i]);
			labels[i].setHorizontalAlignment(SwingConstants.RIGHT);
			labels[i].setSize(((400 - (int) dimension.getWidth()) / 2) - 10, 25);
			labels[i].setLocation(((400 - (int) dimension.getWidth()) / 2) - (labels[i].getWidth() + 5),
					(verticle + dimension.height / 2) - labels[i].getHeight() / 2);
			labels[i].setBorder(null);
			labels[i].setEditable(false);

			verticle = verticle + ((int) dimension.getHeight() + 20);
			i++;
		}
		this.setSize(400, verticle + 90);
		JButton okButton = new JButton();
		JButton cancelButton = new JButton();

		okButton.setSize(90, 30);
		cancelButton.setSize(90, 30);

		okButton.setLocation(220, verticle);
		cancelButton.setLocation(70, verticle);

		okButton.setBackground(new Colors().getColor("ButtonBack"));
		okButton.setForeground(new Colors().getColor("InGreen"));
		okButton.setFont(Fonts.getFont("CreteRound-Regular", 14f));
		okButton.setBorder(BorderFactory.createEtchedBorder());
		okButton.setText("OK");

		cancelButton.setBackground(new Colors().getColor("ButtonBack"));
		cancelButton.setForeground(new Colors().getColor("InGreen"));
		cancelButton.setFont(Fonts.getFont("CreteRound-Regular", 14f));
		cancelButton.setBorder(BorderFactory.createEtchedBorder());
		cancelButton.setText("Cancel");

		mainPanel.add(okButton);
		mainPanel.add(cancelButton);

		cancelButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				cancel();
			}
		});
		okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				ok();
			}
		});
		dispatcher = new KeyEventDispatcher() {

			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_ENTER) {
					ok();
				} else if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					cancel();
				}
				return false;
			}
		};

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(dispatcher);

		// reset();
	}

	private void exit() {
		DevConsole.printOut("Exiting Prompt...");
		reset();
		repaint();
	}

	private void setup(String title, String message, ImageIcon icon) {
		this.setIconImage(icon.getImage());

		msgLabel.setText(message);
		msgLabel.setFont(Fonts.getFont("CreteRound-Regular", 15f));
		msgLabel.setForeground(new Colors().getColor("InGreen"));
		msgLabel.setSize(350, 25);
		msgLabel.setLocation(25, 15);
		this.setAlwaysOnTop(true);
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		mainPanel.add(msgLabel);
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Colors().getColor("BackGray"));
		msgLabel.setEditable(false);
		msgLabel.setBackground(new Colors().getColor("BackGray"));
		this.setResizable(false);

		this.setTitle(title);
		this.setVisible(true);

		SimpleAttributeSet centerAtt = new SimpleAttributeSet();
		StyleConstants.setAlignment(centerAtt, StyleConstants.ALIGN_CENTER);
		msgLabel.setParagraphAttributes(centerAtt, false);

	}

	public void reset() {
		this.dispose();
		toReturn = null;
		msgLabel = new JTextPane();
		mainPanel = new JPanel();
		require = null;
		call = null;
		entryFields = null;
	}

	public String[] getAllInput() {
		return toReturn;
	}

	public JPanel getPanel() {
		return mainPanel;
	}

	public int getVerticleNext() {
		return verticle;
	}

	private void ok() {
		boolean validated = true;
		int a = 0;
		for (JTextField field : entryFields) {
			for (int requiredIndex : require) {
				if (requiredIndex == a && field.getText().equalsIgnoreCase("")) {
					validated = false;
				}
			}
			a++;
		}
		if (validated) {
			int i = 0;
			toReturn = new String[entryFields.length];
			for (JTextField field : entryFields) {
				toReturn[i] = field.getText();
				i++;
			}
			setVisible(false);
			KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(dispatcher);
			call.execute(toReturn);
		} else {
			return;
		}
	}

	private void cancel() {
		call.cancelFallback();
		KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(dispatcher);
		exit();

	}

}
