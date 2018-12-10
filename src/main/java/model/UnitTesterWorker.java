package model;

import view.TesterFrame;
import javax.swing.*;

/**
 * Class for creating a worker to avoid locking the GUI while testing.
 * @UserID - tfy17jfo
 * @date - 2018-12-10
 * @version 1.0
 * @author Jakob Fridesjö
 */
public class UnitTesterWorker extends SwingWorker<Object, Object> {
    private TesterFrame frame;
    private boolean append;
    private String className;
    private UnitTester unitTester;

    /**
     * Constructor for the UnitTester worker.
     * @param classname name to test.
     * @param frame frame to apped results to.
     */
    public UnitTesterWorker(String classname, TesterFrame frame) {
        this.className = classname;
        this.frame = frame;
    }

    /**
     * Thread for testing.
     */
    @Override
    protected Object doInBackground() {
        try {
            append = true;
            unitTester = new UnitTester(className);
            unitTester.verifyTheClass();
            unitTester.testTheClass();
        } catch (ClassNotFoundException e) {
            if (className.equals("")) {
                frame.textArea.append("Specify class name above\n");
                append = false;
            } else {
                frame.textArea.append("Class \"" +
                                       className + "\" not found\n");
                append = false;
            }
        }
        return unitTester;
    }

    /**
     * Adds results from testing to the frame.
     */
    @Override
    protected void done() {
        if (append) {
            for (String s : unitTester.results) {
                frame.setTextArea(s);
            }
            frame.setTextArea("\n" + unitTester.success
                              + " test(s) succeeded\n");
            frame.setTextArea(unitTester.fail
                              + " test(s) failed\n");
            frame.setTextArea(unitTester.exceptionFail
                              + " test(s) failed because of an exception\n");
        }
    }
}
