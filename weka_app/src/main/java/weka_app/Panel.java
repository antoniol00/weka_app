package weka_app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartPanel;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

public class Panel extends JPanel {
	private static final long serialVersionUID = -1240238943911965669L;

	private final JPanel options_panel = new JPanel();
	private final JTextField expression = new JTextField(20);
	private final JComboBox<String> prefixed = new JComboBox<String>();
	private final JLabel or = new JLabel("or");
	private final JPanel logPanel = new JPanel();
	private final JTextArea log = new JTextArea();
	private final JPanel start_panel = new JPanel();
	private final JButton start = new JButton("START");
	private final JPanel aux_panel_1 = new JPanel();
	private final JPanel clear_panel = new JPanel();
	private final JButton clearLog = new JButton("Clear Log");
	private final JPanel aux_panel_2 = new JPanel();
	private final JPanel start_training = new JPanel();
	private final JPanel aux_panel_3 = new JPanel();
	private final JPanel aux_panel_4 = new JPanel();
	private final JPanel create_panel = new JPanel();
	private final JButton training_set = new JButton("Create training set");
	private final JButton more = new JButton("More options");
	private final JPanel draw_panel = new JPanel();
	private final JPanel aux_training_draw = new JPanel();
	private final JPanel aux_result_draw = new JPanel();
	private final JPanel training_draw = new JPanel();
	private final JPanel result_draw = new JPanel();
	private final JButton stop = new JButton("STOP");
	private final JProgressBar progressBar = new JProgressBar();

	private Instances training_instances;
	private Instances test_instances;
	double[] domain_values;
	private DialogBox db = new DialogBox();

	public Panel() {
		setLayout(new BorderLayout(0, 0));

		add(options_panel, BorderLayout.SOUTH);
		options_panel.setLayout(new BorderLayout(0, 0));

		JPanel buttonsPanel = new JPanel();
		options_panel.add(buttonsPanel, BorderLayout.EAST);
		buttonsPanel.setLayout(new BorderLayout(0, 0));

		buttonsPanel.add(start_panel, BorderLayout.CENTER);
		start.setEnabled(false);

		start_panel.add(start);
		stop.setEnabled(false);

		start_panel.add(stop);

		buttonsPanel.add(aux_panel_1, BorderLayout.NORTH);

		buttonsPanel.add(clear_panel, BorderLayout.SOUTH);
		clear_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		clear_panel.add(clearLog);

		JPanel inputPanel = new JPanel();
		options_panel.add(inputPanel, BorderLayout.WEST);
		inputPanel.setLayout(new BorderLayout(0, 0));

		inputPanel.add(expression, BorderLayout.NORTH);

		or.setHorizontalAlignment(SwingConstants.CENTER);
		inputPanel.add(or, BorderLayout.CENTER);
		prefixed.setModel(new DefaultComboBoxModel<String>(new String[] { "None (Prefixed functions)", "x^2", "x^3",
				"x^4", "sin(x)", "tan(x)", "sqrt(x)", "log(x)" }));

		inputPanel.add(prefixed, BorderLayout.SOUTH);

		options_panel.add(logPanel, BorderLayout.SOUTH);
		log.setEditable(false);
		log.setRows(10);
		log.setColumns(50);
		logPanel.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane(log);
		scrollPane.setHorizontalScrollBar(null);
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e) {
				e.getAdjustable().setValue(e.getAdjustable().getMaximum());
			}
		});
		logPanel.add(scrollPane, BorderLayout.NORTH);

		logPanel.add(aux_panel_2, BorderLayout.CENTER);
		aux_panel_2.setLayout(new BorderLayout(0, 0));

		progressBar.setStringPainted(true);
		aux_panel_2.add(progressBar);

		options_panel.add(start_training, BorderLayout.CENTER);
		start_training.setLayout(new BorderLayout(0, 0));

		start_training.add(aux_panel_3, BorderLayout.NORTH);

		start_training.add(aux_panel_4, BorderLayout.SOUTH);

		start_training.add(create_panel, BorderLayout.CENTER);
		create_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		more.setAlignmentY(Component.TOP_ALIGNMENT);
		more.setAlignmentX(Component.RIGHT_ALIGNMENT);

		create_panel.add(more);
		training_set.setAlignmentX(Component.CENTER_ALIGNMENT);
		training_set.setAlignmentY(Component.BOTTOM_ALIGNMENT);

		create_panel.add(training_set);
		draw_panel.setPreferredSize(new Dimension(250, 250));
		add(draw_panel, BorderLayout.CENTER);
		draw_panel.setLayout(new GridLayout(0, 2, 0, 0));

		draw_panel.add(aux_training_draw);
		aux_training_draw.setLayout(new BorderLayout(0, 0));

		aux_training_draw.add(training_draw, BorderLayout.CENTER);

		draw_panel.add(aux_result_draw);
		aux_result_draw.setLayout(new BorderLayout(0, 0));

		aux_result_draw.add(result_draw);

	}

	public void controller(ActionListener ctr) {
		more.addActionListener(ctr);
		more.setActionCommand("more");
		start.addActionListener(ctr);
		start.setActionCommand("start");
		stop.addActionListener(ctr);
		stop.setActionCommand("stop");
		clearLog.addActionListener(ctr);
		clearLog.setActionCommand("clearLog");
		training_set.addActionListener(ctr);
		training_set.setActionCommand("training_set");
		prefixed.addActionListener(ctr);
		prefixed.setActionCommand("prefixed");
	}

	public void openDialogBox() {
		db.showDialogBox();
	}

	public void updateLog(String update) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		log.setText(log.getText() + dtf.format(now) + " : " + update + "\n");
	}

	public void updateError(String error) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		log.setText(log.getText() + dtf.format(now) + " : " + "ERROR - " + error + "\n");
	}

	public void clearLog() {
		log.setText("");
	}

	public void prefixedSelected() {
		if (prefixed.getSelectedIndex() != 0) {
			expression.setText("");
			expression.setEnabled(false);
		} else {
			expression.setEnabled(true);
		}
	}

	public void createTrainingSet() {
		aux_training_draw.removeAll();
		try {
			String exp = "";
			if (expression.getText().isEmpty() && prefixed.getSelectedIndex() == 0) {
				throw new WekaException("Expression field is empty or there is no prefixed function chosen");
			} else if (prefixed.getSelectedIndex() != 0) {
				exp = (String) prefixed.getSelectedItem();
			} else {
				exp = expression.getText();
			}

			String[] domainS = db.getDomain().split("[,]");
			domain_values = new double[] { Double.parseDouble(domainS[0]), Double.parseDouble(domainS[1]) };

			if (domain_values[1] < domain_values[0])
				throw new WekaException("X domain values are not correctly specified");

			ComputeTraining dc = new ComputeTraining(exp, Integer.parseInt(db.getTraining_size()),
					Double.parseDouble(db.getTest_size()), db.getNoise_type(), Double.parseDouble(db.getNoise_level()),
					domain_values);

			ChartPanel chart = dc.getChart();
			aux_training_draw.add(chart);
			aux_training_draw.validate();
			start.setEnabled(true);
			updateLog("Training set created with " + db.getTraining_size() + " samples and test set created with "
					+ ((int) (Integer.parseInt(db.getTraining_size()) * Double.parseDouble(db.getTest_size()) / 100))
					+ " samples");
			training_instances = dc.getTrainingInstances();
			test_instances = dc.getTestInstances();

		} catch (NumberFormatException e) {
			updateError("Some parameters are not correctly specified. Check for spelling errors or see Help");
		} catch (WekaException e) {
			updateError(e.getMessage());
		}
	}

	public void createResult(MultilayerPerceptron mlp) {
		aux_result_draw.removeAll();
		try {
			ComputeResult dr = new ComputeResult(mlp, training_instances, domain_values, getExpression());
			ChartPanel chart = dr.getChart();
			aux_result_draw.add(chart);
			aux_result_draw.validate();
		} catch (Exception e) {
			updateError("Error detected in plotting result graphs");
		}
	}

	public Instances getTrainingInstances() {
		return training_instances;
	}

	public Instances getTestInstances() {
		return test_instances;
	}

	public Double getLearningRate() {
		return Double.parseDouble(db.getLearning_rate());
	}

	public String getHiddenLayers() {
		return db.getHidden_layer_size();
	}

	public int getTrainingTime() {
		return Integer.parseInt(db.getNumber_of_epochs());
	}

	public String getExpression() {
		if (expression.getText().isEmpty()) {
			return (String) prefixed.getSelectedItem();
		} else {
			return expression.getText();
		}
	}

	public boolean isComplete() {
		return db.getMode().getSelectedIndex() == 0 ? true : false;
	}

	public JButton getStopButton() {
		return this.stop;
	}

	public JButton getStartButton() {
		return this.start;
	}

	public void setProgress(int n, boolean stop) {
		progressBar.setValue(n);
		if (stop) {
			progressBar.setForeground(Color.RED);
		} else {
			if (n == 100)
				progressBar.setForeground(Color.GREEN);
			else
				progressBar.setForeground(Color.ORANGE);
		}
	}

}
