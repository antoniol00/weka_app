package weka_app;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Prueba {

	public static void main(String[] args) throws Exception {
		/*
		 * MultilayerPerceptron mlpc = new MultilayerPerceptron(); Instances instances =
		 * new Instances(new FileReader("test.arff")); instances.setClassIndex(1);
		 * mlpc.buildClassifier(instances);
		 * 
		 * Evaluation eval = new Evaluation(instances); eval.evaluateModel(mlpc,
		 * instances); System.out.println(eval.toSummaryString("\nResults\n======\n",
		 * false)); System.out.println(mlpc.classifyInstance(instances.get(1)));
		 * 
		 * for(int x = 1; x< 1000; x++) { System.out.println(x+","+(2*x)); }
		 */

		 Expression expression = new ExpressionBuilder("3x").variable("x").build().setVariable("x", 2);
		 double result = expression.evaluate();
		 System.out.println(result);

	}

}
