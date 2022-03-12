package weka_app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.awt.GridLayout;

public class Panel extends JPanel {
	private static final long serialVersionUID = -1240238943911965669L;

	private final JPanel options_panel = new JPanel();
	private final JTextField expression = new JTextField(20);
	private final JComboBox<String> comboBox = new JComboBox<String>();
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
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Prefixed functions", "x+1", "2x+3" }));

		inputPanel.add(comboBox, BorderLayout.SOUTH);

		options_panel.add(logPanel, BorderLayout.SOUTH);
		log.setEditable(false);
		log.setRows(5);
		log.setColumns(50);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		logPanel.setLayout(new BorderLayout(0, 0));
		log.setText(dtf.format(now) + " : App launched");
		JScrollPane scrollPane = new JScrollPane(log);
		scrollPane.setHorizontalScrollBar(null);
		logPanel.add(scrollPane, BorderLayout.NORTH);

		logPanel.add(aux_panel_2, BorderLayout.SOUTH);

		options_panel.add(start_training, BorderLayout.CENTER);
		start_training.setLayout(new BorderLayout(0, 0));

		start_training.add(aux_panel_3, BorderLayout.NORTH);

		start_training.add(aux_panel_4, BorderLayout.SOUTH);

		start_training.add(create_panel, BorderLayout.CENTER);
		create_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		create_panel.add(more);

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
		clearLog.addActionListener(ctr);
		clearLog.setActionCommand("clearLog");
		training_set.addActionListener(ctr);
		training_set.setActionCommand("training_set");
	}

	public void openDialogBox() {
		JTextField noise_level = new JTextField();
		JComboBox<String> noise_type = new JComboBox<String>();
		noise_type.setModel(new DefaultComboBoxModel<String>(new String[] { "Gaussian", "Uniform" }));
		JTextField hidden_layer_size = new JTextField();
		JTextField training_size = new JTextField();
		JTextField validation_size = new JTextField();
		JComboBox<String> mode = new JComboBox<String>();
		mode.setModel(new DefaultComboBoxModel<String>(new String[] { "Complete", "Step by step" }));
		JTextField number_of_steps = new JTextField();
		JTextField learning_type = new JTextField();

		final JComponent[] inputs = new JComponent[] { new JLabel("Noise Level"), noise_level, new JLabel("Noise type"),
				noise_type, new JLabel("Hidden layer size"), hidden_layer_size, new JLabel("Training set size"),
				training_size, new JLabel("Validation set size"), validation_size, new JLabel("Mode"), mode,
				new JLabel("Number of steps"), number_of_steps, new JLabel("Learning type"), learning_type };

		JOptionPane.setDefaultLocale(Locale.ENGLISH);
		int result = JOptionPane.showConfirmDialog(null, inputs, "More options", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (result == 0) {
			updateLog("Parameters successfully changed");
		} else {
			updateLog("No modifications made");
		}

	}

	public void updateLog(String update) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		log.setText(log.getText() + "\n" + dtf.format(now) + " : " + update);
	}

	public void clearLog() {
		log.setText("");
	}

	public void createTrainingSet() {
		start.setEnabled(true);
		
		DrawChart dc = new DrawChart("Training set",expression.getText(),10);
		ChartPanel chart = dc.getChart();
		aux_training_draw.add(chart);
		aux_training_draw.validate();

		updateLog("training set creado");
	}
}
