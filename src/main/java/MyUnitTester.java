import controller.Controller;
import view.TesterFrame;

import javax.swing.SwingUtilities;

/**
 * Program for testing classes in a similar fashion to 
 * testing them using the JUnit framework.
 * @UserID tfy17jfo
 * @date 2018-11-21
 * @version 1.0
 * @author Jakob Fridesjö
 */
public class MyUnitTester	{
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			TesterFrame view = new TesterFrame("MyUnitTester");
			new Controller(view);
		});
	}
}
