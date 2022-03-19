package weka_app;

import java.io.FileReader;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

public class Prueba {

	public static void main(String[] args) throws Exception {
		FileReader trainreader = new FileReader("prueba.arff");
		Instances train = new Instances(trainreader);
		train.setClassIndex(1);

		// Instance of NN
		MultilayerPerceptron mlp = new MultilayerPerceptron();
		mlp.initializeClassifier(train);
		
		int x = 0;
		while (mlp.next()) {
			System.out.println("ITERATION " + x + " :" + mlp.toString());
			x++;
		}
		System.out.println(x);
	}

}
