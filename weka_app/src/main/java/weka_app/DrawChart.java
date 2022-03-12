package weka_app;

import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class DrawChart {

	private ChartPanel chart;

	public DrawChart(String title, String function, int size) {
		XYSeriesCollection dataset = new XYSeriesCollection();

		XYSeries series1 = new XYSeries(title);
		
		Random r = new Random();
		for(int x = 0; x <= size; x++ ) {
			int x_val = r.nextInt(10);
			Expression expression = new ExpressionBuilder(function).variable("x").build().setVariable("x", x_val);
			double y_val = expression.evaluate();
			series1.add(x_val,y_val);
		}

		dataset.addSeries(series1);

		JFreeChart scatterPlot = ChartFactory.createScatterPlot(title, "x", "y", dataset);
		chart = new ChartPanel(scatterPlot);
		chart.setMouseWheelEnabled(true);
	}

	public ChartPanel getChart() {
		return chart;
	}

}
