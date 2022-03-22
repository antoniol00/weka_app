package weka_app;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class ComputeResult {

	private ChartPanel chart;

	public ComputeResult(MultilayerPerceptron mlp, Instances i, double[] domain_values, String exp) throws Exception {

		XYSeriesCollection dataset = new XYSeriesCollection();

		XYSeries result_values = new XYSeries("Computed regression");
		XYSeries real_values = new XYSeries("Expected function");

		double y;
		for (double x = domain_values[0]; x <= domain_values[1]; x += 0.5) {
			Instance inst = new DenseInstance(2);
			inst.setValue(0, x);
			i.add(inst);

			y = mlp.classifyInstance(i.lastInstance());
			result_values.add(x, y);

			Expression expression = new ExpressionBuilder(exp).variable("x").build().setVariable("x", x);
			y = expression.evaluate();
			real_values.add(x, y);

		}

		dataset.addSeries(result_values);
		dataset.addSeries(real_values);

		JFreeChart lineChart = ChartFactory.createXYLineChart("Results", "x", "y", dataset);
		lineChart.getTitle().setFont(new Font("Tahoma", Font.PLAIN, 20));
		chart = new ChartPanel(lineChart);
	}

	public ChartPanel getChart() {
		return chart;
	}
}
