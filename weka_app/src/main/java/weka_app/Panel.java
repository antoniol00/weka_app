package weka_app;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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

public class Panel extends JPanel {
	private static final long serialVersionUID = -1240238943911965669L;

	private final JPanel panel = new JPanel();
	private final JTextField expression = new JTextField(20);
	private final JComboBox<String> comboBox = new JComboBox<String>();
	private final JLabel or = new JLabel("or");
	private final JPanel logPanel = new JPanel();
	private final JTextArea log = new JTextArea();
	private final JPanel panel_4 = new JPanel();
	private final JButton start = new JButton("START");
	private final JButton more = new JButton("More options");
	private final JPanel panel_5 = new JPanel();
	private final JPanel panel_6 = new JPanel();
	private final JButton clearLog = new JButton("Clear Log");

	public Panel() {
		setLayout(new BorderLayout(0, 0));

		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel buttonsPanel = new JPanel();
		panel.add(buttonsPanel, BorderLayout.EAST);
		buttonsPanel.setLayout(new BorderLayout(0, 0));

		buttonsPanel.add(panel_4, BorderLayout.CENTER);

		panel_4.add(more);
		start.setEnabled(false);

		panel_4.add(start);

		buttonsPanel.add(panel_5, BorderLayout.NORTH);

		buttonsPanel.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		clearLog.setIcon(new ImageIcon(Panel.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));

		panel_6.add(clearLog);

		JPanel inputPanel = new JPanel();
		panel.add(inputPanel, BorderLayout.WEST);
		inputPanel.setLayout(new BorderLayout(0, 0));

		inputPanel.add(expression, BorderLayout.NORTH);

		or.setHorizontalAlignment(SwingConstants.CENTER);
		inputPanel.add(or, BorderLayout.CENTER);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Prefixed functions", "x+1", "2x+3" }));

		inputPanel.add(comboBox, BorderLayout.SOUTH);

		panel.add(logPanel, BorderLayout.SOUTH);
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
	}

	public void controller(ActionListener ctr) {
		more.addActionListener(ctr);
		more.setActionCommand("more");
		start.addActionListener(ctr);
		start.setActionCommand("start");
		clearLog.addActionListener(ctr);
		clearLog.setActionCommand("clearLog");
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
}
