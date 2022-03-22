package weka_app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Controller implements ActionListener, PropertyChangeListener {

	private Panel panel;
	private Worker w;

	public Controller(Panel panel) {
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "more":
			panel.openDialogBox();
			break;
		case "start":
			w = new Worker(panel, panel.getTrainingInstances(), panel.getTestInstances());
			try {
				w.addPropertyChangeListener(this);
				w.execute();
			} catch (Exception x) {
				panel.updateError(x.getMessage());
			}
			break;
		case "stop":
			w.cancel(true);
			panel.getStopButton().setEnabled(false);
			panel.setProgress(100,true);
			break;
		case "clearLog":
			panel.clearLog();
			break;
		case "training_set":
			panel.createTrainingSet();
			break;
		case "prefixed":
			panel.prefixedSelected();
			break;
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case "progress":
			panel.setProgress((Integer) evt.getNewValue(),false);
			break;
		}
	}
}