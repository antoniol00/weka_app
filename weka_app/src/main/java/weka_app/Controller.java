package weka_app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

	private Panel panel;
	private Worker w;

	public Controller(Panel panel) {
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("more")) {
			panel.openDialogBox();
		}
		if (e.getActionCommand().equals("start")) {

			Worker w = new Worker(panel);
			w.execute();

		}
		if (e.getActionCommand().equals("clearLog")) {
			panel.clearLog();
		}
		if (e.getActionCommand().equals("training_set")) {
			panel.createTrainingSet();
		}

	}

}
