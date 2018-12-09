package controller;

import model.UnitTesterWorker;
import view.TesterFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

/**
 * Controller class for starting a test instance.
 * @UserID - tfy17jfo
 * @date - 2018-11-21
 * @version 1.0
 * @author Jakob Fridesjö
 */
public class Controller {
	private TesterFrame frame;

	/**
	 * Constructor for Controller
	 * @param frame - frame for input and manipulation
	 */
	public Controller(TesterFrame frame) {
		this.frame = frame;
		frame.setListeners(new Listeners());
	}

	class Listeners implements ActionListener {
		/**
		 * Defines default action on an event.
		 * @param actionEvent event
		 */
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if (actionEvent.getSource() == frame.getRunButton()) {
				try {
					String className = frame.getInput();
					UnitTesterWorker unitTesterWorker = new UnitTesterWorker(className, frame);
					unitTesterWorker.execute();
					unitTesterWorker.get();
				} catch (InterruptedException | ExecutionException e) {
					frame.setTextArea(e.getMessage());
				}
			} else if (actionEvent.getSource() == frame.getClearButton()) {
				frame.setTextArea(null);
				frame.clearTextField();
			}
		}
	}
}
