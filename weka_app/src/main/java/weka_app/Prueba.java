package weka_app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Prueba {

	public static void main(String[] args) throws Exception {

		// MultilayerPerceptron mlpc = new MultilayerPerceptron();
		// Create empty instance with three attribute values

		File myFile = new File("filename.arff");
		if (myFile.createNewFile()) {
			BufferedWriter writer = new BufferedWriter(new FileWriter(myFile));
			writer.write("@relation 'function'\n@attribute x NUMERIC\n@attribute y NUMERIC\n@data");
			writer.close();
		}

		DataSource source = new DataSource(myFile.getAbsolutePath());
		Instances insts = source.getDataSet();
		for (int x = 0; x < 100; x++) {

			Instance inst = new DenseInstance(2);

			inst.setValue(0, x);
			inst.setValue(1, 2*x);

			// Set instance's dataset to be the dataset "race"
			inst.setDataset(insts);
		}

		// Print the instance
		MultilayerPerceptron mlpc = new MultilayerPerceptron();
		insts.setClassIndex(1);

		mlpc.buildClassifier(insts);

		System.out.println("LR FORMULA : " + mlpc);

		/*
		 * instances.setClassIndex(1); mlpc.buildClassifier(instances);
		 * 
		 * Evaluation eval = new Evaluation(instances); eval.evaluateModel(mlpc,
		 * instances); System.out.println(eval.toSummaryString("\nResults\n======\n",
		 * false)); System.out.println(mlpc.classifyInstance(instances.get(1)));
		 */

	}

}
