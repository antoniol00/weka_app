package weka_app;

import javax.swing.SwingWorker;

public class Worker extends SwingWorker<Void, Integer> {

	private int n;
	private Panel panel;

	public Worker(int n, Panel panel) {
		this.n = n;
		this.panel = panel;
	}

	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
