package weka_app;

import javax.swing.SwingWorker;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

public class Worker extends SwingWorker<MultilayerPerceptron, Void> {

	private Panel panel;
	private Instances training;
	// private Instances test;

	public Worker(Panel panel, Instances training, Instances test) {
		this.panel = panel;
		this.training = training;
		// this.test = test;
	}

	@Override
	protected MultilayerPerceptron doInBackground() throws Exception {
		training.setClassIndex(1);
		MultilayerPerceptron mlpc = new MultilayerPerceptron();
		try {
			mlpc.setLearningRate(panel.getLearningRate());
			mlpc.setHiddenLayers(panel.getHiddenLayers());
			mlpc.setTrainingTime(panel.getTrainingTime());
			mlpc.buildClassifier(training);

		} catch (Exception e) {
			panel.updateLog("Unexpected error in training set parsing:\n" + e);
		}
		return mlpc;
	}

}
