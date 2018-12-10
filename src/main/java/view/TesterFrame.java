package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Creates gui and sets listeners and buttons.
 * @UserID - tfy17jfo
 * @date - 2018-11-21
 * @version 1.0
 * @author Jakob Fridesjö
 */
public class TesterFrame extends JFrame {
    
	private static final long serialVersionUID = 1L;
	private final int F_WIDTH = 640;
	private final int F_HEIGHT = 480;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JButton runButton;
	private JButton clearButton;
	public JTextArea textArea;

	/**
	 * Constructor for MyUnitTester.
	 * Creates an frame and adds the elements.
	 * */
	public TesterFrame(String title){

		textArea = createTextArea();
		try {
			//Set look and feel according to system
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException 
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			textArea.append(e.getMessage());
		}

		JFrame frame = new JFrame(title);
		frame.setMinimumSize(new Dimension(400, 300));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(F_WIDTH, F_HEIGHT);
        frame.setVisible(true);
        textField = createTextField();
		JPanel upperPanel = createUpperPanel();
		JPanel lowerPanel = createLowerPanel();
        scrollPane = createScrollPane();
		JPanel midPanel = createMidPanel();
        frame.add(upperPanel, BorderLayout.NORTH);
        frame.add(midPanel, BorderLayout.CENTER);
        frame.add(lowerPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Creates an JTextField.
	 * @return JTextField object.
	 */
	private JTextField createTextField() {
        JTextField textField = new JTextField(20);
        textField.setEditable(true);
        textField.setVisible(true);
        return textField;
	}
	
	/**
	 * Creates an JTextArea.
	 * @return JtextArea object.
	 */
	private JTextArea createTextArea() {
		JTextArea textArea = new JTextArea(20, 1);
        textArea.setVisible(true);
        textArea.setEditable(false);
        return textArea;
	}
	
	/**
	 * Creates the upper panel and adds a JButton
	 * with an actionListener.
	 * @return JPanel object.
	 */
	private JPanel createUpperPanel() {
		JPanel upperPanel = new JPanel(new BorderLayout());
		upperPanel.setVisible(true);
		runButton = new JButton("Run tests");
		upperPanel.add(textField, BorderLayout.CENTER);
		upperPanel.add(runButton, BorderLayout.EAST);
		return upperPanel;
	}
	
	/**
	 * Creates the lower panel and adds a JButton
	 * with an actionListener.
	 * @return JPanel object.
	 */
	private JPanel createLowerPanel() {
		JPanel lowerPanel = new JPanel(new BorderLayout());
		lowerPanel.setVisible(true);
		clearButton = new JButton("Clear");
		lowerPanel.add(clearButton);
		return lowerPanel;
	}
	
	/**
	 * Creates the midpanel and adds an scrollpane.
	 * @return JPanel object.
	 */
	private JPanel createMidPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setVisible(true);
		panel.add(scrollPane);
		return panel;
	}
	
	/**
	 * Adds a scrollpane to the textArea.
	 * @return JScrollPane object.
	 */
	private JScrollPane createScrollPane() {
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setHorizontalScrollBarPolicy
		(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		return scrollPane;
	}
	
	/**
	 * Gets input from user.
	 * @return Text entered by user.
	 */
	public String getInput(){
		return textField.getText();
	}
	
	/**
	 * Sets the text in the textArea.
	 * @param str String to append.
	 */
	public void setTextArea(String str) {
		if (str == null) {
			textArea.setText(null);
		} else {
			textArea.append(str);
		}
	}

	public void clearTextField() {
		textField.setText(null);
	}

	/**
	 * Setter for listeners.
	 */
	public void setListeners(ActionListener l){
		runButton.addActionListener(l);
		clearButton.addActionListener(l);
	}

	/**
	 * Getter for runButton.
	 * @return runButton
	 */
	public JButton getRunButton() {
		return runButton;
	}

	/**
	 * Getter for clearButton
	 * @return clearButton
	 */
	public JButton getClearButton() {
		return clearButton;
	}
}
