package weka_app;

import java.io.FileReader;

import javax.swing.SwingWorker;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instance;
import weka.core.Instances;

public class Worker extends SwingWorker<Void, Void> {

	private Panel panel;

	public Worker(Panel panel) {
		this.panel = panel;
	}

	@Override
	protected Void doInBackground() throws Exception {
		MultilayerPerceptron mlpc = new MultilayerPerceptron();
		Instances instances = new Instances(new FileReader("test.arff"));
		instances.setClassIndex(1);
		mlpc.buildClassifier(instances);
		
		Evaluation eval = new Evaluation(instances);
		eval.evaluateModel(	mlpc, instances);
		panel.updateLog(eval.toSummaryString("\nResults\n======\n", false));
		return null;
	}

}
