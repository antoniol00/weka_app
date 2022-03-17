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

	public ComputeResult(MultilayerPerceptron mlp, Instances i, double[] max_base_values, Panel panel)
			throws Exception {

		XYSeriesCollection dataset = new XYSeriesCollection();

		XYSeries result_values = new XYSeries("Result values");
		XYSeries real_values = new XYSeries("Real values");

		for (double x = -10; x <= 10; x += 0.01) {
			Instance inst = new DenseInstance(2);
			inst.setValue(0, x);
			i.add(inst);

			double y = mlp.classifyInstance(i.lastInstance());
			result_values.add(x, y);
		}
		
		for (double x = -10; x <= 10; x += 0.01) {
			if(panel.getExpression().isEmpty()) {
				Expression expression = new ExpressionBuilder(panel.getExpression()).variable("x").build().setVariable("x", x);
				double y = expression.evaluate();
				real_values.add(x, y);
			}else {
				Expression expression = new ExpressionBuilder(panel.getExpression()).variable("x").build().setVariable("x", x);
				double y = expression.evaluate();
				real_values.add(x, y);
			}
			
		}

		dataset.addSeries(result_values);
		dataset.addSeries(real_values);

		JFreeChart lineChart = ChartFactory.createXYLineChart("Predicted function", "x", "y", dataset);
		lineChart.getTitle().setFont(new Font("Tahoma", Font.PLAIN, 20));
		chart = new ChartPanel(lineChart);
	}

	public ChartPanel getChart() {
		return chart;
	}
}
