package weka_app;

import javax.swing.SwingWorker;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

public class Worker extends SwingWorker<Void, Void> {

	private Panel panel;
	private Instances training;
	private Instances test;
	private int sleep;

	public Worker(Panel panel, Instances training, Instances test) {
		this.panel = panel;
		this.training = training;
		this.test = test;
	}

	@Override
	public Void doInBackground() throws WekaException {
		panel.getStartButton().setEnabled(false);
		this.setProgress(0);
		training.setClassIndex(1);
		test.setClassIndex(1);
		MultilayerPerceptron mlpc = new MultilayerPerceptron();
		try {
			if (panel.getLearningRate() < 0 || panel.getLearningRate() > 1) {
				throw new WekaException();
			}
			mlpc.setLearningRate(panel.getLearningRate());
			mlpc.setHiddenLayers(panel.getHiddenLayers());
			mlpc.setTrainingTime(panel.getTrainingTime());

			if (!panel.isComplete()) {
				if (mlpc.getTrainingTime() > 40) {
					sleep = 30 * 1000 / mlpc.getTrainingTime();
				} else {
					sleep = 750;
				}
				panel.getStopButton().setEnabled(true);
				mlpc.initializeClassifier(training);
				int x = 1;
				do {
					panel.createResult(mlpc);
					Evaluation e = new Evaluation(test);
					e.evaluateModel(mlpc, test);
					Evaluation et = new Evaluation(training);
					et.evaluateModel(mlpc, training);
					panel.updateLog("\n ======= EVALUATION RESULTS ITERATION " + x
							+ "=======\nUsing test case:\n\tTotal instances: " + e.numInstances()
							+ "\tRoot mean squared error: " + e.rootMeanSquaredError()
							+ "\nUsing training case:\n\tTotal instances: " + et.numInstances()
							+ "\tRoot mean squared error: " + et.rootMeanSquaredError());
					this.setProgress(x * 100 / mlpc.getTrainingTime());
					x++;
					Thread.sleep(sleep);
				} while (mlpc.next());
			} else {
				mlpc.buildClassifier(training);

				Evaluation e = new Evaluation(test);
				e.evaluateModel(mlpc, test);
				Evaluation et = new Evaluation(training);
				et.evaluateModel(mlpc, training);
				panel.updateLog("\n ======= EVALUATION RESULTS =======\nUsing test case:\n\tTotal instances: "
						+ e.numInstances() + "\n\tRoot mean squared error: " + e.rootMeanSquaredError()
						+ "\nUsing training case:\n\tTotal instances: " + et.numInstances()
						+ "\n\tRoot mean squared error: " + et.rootMeanSquaredError());
				panel.createResult(mlpc);
				this.setProgress(100);
			}
		} catch (InterruptedException x) {

		} catch (Exception e) {
			panel.updateError("Check for Learning Rate, Hidden Layers or Number of epochs inputs\n" + e.getMessage());
		}
		return null;
	}

	@Override
	public void done() {
		panel.updateLog("Execution finished or terminated");
		panel.getStopButton().setEnabled(false);
		panel.getStartButton().setEnabled(true);
	}

}
