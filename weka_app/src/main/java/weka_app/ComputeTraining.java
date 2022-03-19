package weka_app;

import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ComputeTraining {

	private ChartPanel chart;
	private File myFileTr, myFileTe;
	private Instances training_instances, test_instances;

	public ComputeTraining(String function, int training_size, double validation_size, int noise_type_idx,
			int noise_level) throws WekaException {

		try {
			myFileTr = new File("training.arff");
			myFileTr.deleteOnExit();
			if (myFileTr.createNewFile()) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(myFileTr));
				writer.write("@relation 'function'\n@attribute x NUMERIC\n@attribute y NUMERIC\n@data");
				writer.close();
			}
			myFileTe = new File("test.arff");
			myFileTe.deleteOnExit();
			if (myFileTe.createNewFile()) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(myFileTe));
				writer.write("@relation 'function'\n@attribute x NUMERIC\n@attribute y NUMERIC\n@data");
				writer.close();
			}

			DataSource sourceT = new DataSource(myFileTr.getAbsolutePath());
			training_instances = sourceT.getDataSet();
			DataSource source = new DataSource(myFileTe.getAbsolutePath());
			test_instances = source.getDataSet();

		} catch (Exception e) {
			throw new WekaException("File management error, check writing permission in this folder");
		}

		XYSeriesCollection dataset = new XYSeriesCollection();

		XYSeries training_values = new XYSeries("Training values");
		XYSeries val_values = new XYSeries("Testing values");

		Random r = new Random();
		double val_size = training_size * validation_size / 100;

		for (int x = 0; x < training_size; x++) {
			try {
				double x_val = -10 + r.nextDouble() * 20;
				Expression expression = new ExpressionBuilder(function).variable("x").build().setVariable("x", x_val);
				double y_val = expression.evaluate();
				y_val = addNoise(y_val, noise_type_idx, noise_level);
				training_values.add(x_val, y_val);

				Instance inst = new DenseInstance(2);
				inst.setValue(0, x_val);
				inst.setValue(1, y_val);
				training_instances.add(inst);

			} catch (Exception e) {
				throw new WekaException("Expression not correctly build. Example: 3x^2+log(x)");
			}

		}
		for (int x = 0; x < val_size; x++) {
			double x_val = -10 + r.nextDouble() * 20;
			Expression expression = new ExpressionBuilder(function).variable("x").build().setVariable("x", x_val);
			double y_val = expression.evaluate();
			y_val = addNoise(y_val, noise_type_idx, noise_level);
			val_values.add(x_val, y_val);

			Instance inst = new DenseInstance(2);
			inst.setValue(0, x_val);
			inst.setValue(1, y_val);
			test_instances.add(inst);
		}

		dataset.addSeries(training_values);
		dataset.addSeries(val_values);

		JFreeChart scatterPlot = ChartFactory.createScatterPlot("Training and Testing sets", "x", "y", dataset);
		scatterPlot.getTitle().setFont(new Font("Tahoma", Font.PLAIN, 20));
		chart = new ChartPanel(scatterPlot);
		chart.setMouseWheelEnabled(true);
	}

	public ChartPanel getChart() {
		return chart;
	}

	private double addNoise(double y_val, int idx, int level) {
		if (level < 0) {
			throw new WekaException("Noise level should be an integer greater than 0");
		}
		Random r = new Random();
		if (idx == 0) {
			return y_val + (-1 + r.nextDouble() * 2) * level;
		} else {
			return y_val + r.nextGaussian() * level;
		}
	}

	public Instances getTrainingInstances() {
		return training_instances;
	}

	public Instances getTestInstances() {
		return test_instances;
	}

}
