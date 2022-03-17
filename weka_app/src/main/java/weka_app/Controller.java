package weka_app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

public class Controller implements ActionListener {

	private Panel panel;

	public Controller(Panel panel) {
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("more")) {
			panel.openDialogBox();
		}
		if (e.getActionCommand().equals("start")) {
			Worker w = new Worker(panel, panel.getTrainingInstances(), panel.getTestInstances());
			w.execute();
			try {
				panel.setPerceptron(w.get());
				panel.createResult();
			} catch (InterruptedException | ExecutionException x) {

				x.printStackTrace();
			}
		}
		if (e.getActionCommand().equals("clearLog")) {
			panel.clearLog();
		}
		if (e.getActionCommand().equals("training_set")) {
			panel.createTrainingSet();
		}
		if (e.getActionCommand().equals("prefixed")) {
			panel.prefixedSelected();
		}

	}

}
