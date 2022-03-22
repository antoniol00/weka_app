package weka_app;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DialogBox {

	private static JDialog dialog;
	private final JFrame f = new JFrame();

	private final JTextField domain = new JTextField("-10,10");
	private final JTextField noise_level = new JTextField("0");
	private final JComboBox<String> noise_type = new JComboBox<String>();
	private final JTextField training_size = new JTextField("100");
	private final JTextField test_size = new JTextField("20");

	private final JComboBox<String> mode = new JComboBox<String>();
	private final JTextField hidden_layer_size = new JTextField("a");
	private final JTextField number_of_epochs = new JTextField("500");
	private final JTextField learning_rate = new JTextField("0.3");

	private final JPanel panelLeft = new JPanel();
	private final JPanel panelRight = new JPanel();
	private final JPanel panelAux = new JPanel();
	private final JLabel lbl1 = new JLabel("Training and data settings");
	private final JLabel lbl2 = new JLabel("Regression setings");
	private final JButton help = new JButton("Help");
	private final JButton ok = new JButton("OK");

	public DialogBox() {

		noise_type.setModel(new DefaultComboBoxModel<String>(new String[] { "Uniform", "Gaussian" }));
		mode.setModel(new DefaultComboBoxModel<String>(new String[] { "Complete", "Step by step" }));

		dialog = new JDialog(f, "Options", true);
		dialog.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));

		dialog.getContentPane().add(panelLeft);
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));

		lbl1.setHorizontalAlignment(SwingConstants.LEFT);
		panelLeft.add(lbl1);
		panelLeft.add(new JLabel("X Domain"));
		panelLeft.add(domain);
		panelLeft.add(new JLabel("Noise Level"));
		panelLeft.add(noise_level);
		panelLeft.add(new JLabel("Noise type"));
		panelLeft.add(noise_type);
		panelLeft.add(new JLabel("Training set size"));
		panelLeft.add(training_size);
		panelLeft.add(new JLabel("Test set size (% of Training set)"));
		panelLeft.add(test_size);

		dialog.getContentPane().add(panelRight);
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));

		panelRight.add(lbl2);
		panelRight.add(new JLabel("Mode"));
		panelRight.add(mode);
		panelRight.add(new JLabel("Hidden nodes per layer"));
		panelRight.add(hidden_layer_size);
		panelRight.add(new JLabel("Number of epochs"));
		panelRight.add(number_of_epochs);
		panelRight.add(new JLabel("Learning rate"));
		panelRight.add(learning_rate);
		panelRight.add(panelAux);
		panelAux.add(help);
		panelAux.add(ok);

		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
		});

		help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "===== FUNCTION =====\n"
						+ "The function to be computed by the regressor must have only a variable \"x\" (in lower case), and could be a constant, lineal, polynomial, rational, exponential or logarithmic function.\n"
						+ "You can also choose between the prefixed functions to try.\n"
						+ "\n===== TRAINING AND DATA SETTINGS =====\n"
						+ "X Domain: input two values separated by \",\", being the second one greater than the first one. These values indicates the limits of the domain of the function that should be computed.\n"
						+ "Noise level: indicates the multiplicative factor of the type of noise applied.\n"
						+ "Noise type: can be gaussian (noise distribution follows a normal distribution) or uniform (noise distribution follows a uniform distribution).\n"
						+ "Training set size: number of training instances to create (The creation process evaluate the funcion in the X domain chosen, and then it applies selected noise options).\n"
						+ "Test set size: number of test instances to creates (it should be expressed as a percentage of the training set size).\n"
						+ "\n===== REGRESSION SETTINGS =====\n"
						+ "Mode: execution mode can be complete (only shows the last iteration) or step by step (shows and plots each epoch).\n"
						+ "Hidden nodes per layer: indicates the number of hidden nodes en each layer, sepparated by commas.\n"
						+ "Learning rate: value between 0.0 and 1.0 that determines the size of the step in each epoch of the training", "Help", JOptionPane.DEFAULT_OPTION);
			}
		});

		dialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		dialog.pack();
	}

	public void showDialogBox() {
		dialog.setVisible(true);
	}

	public String getDomain() {
		return domain.getText();
	}

	public String getNoise_level() {
		return noise_level.getText();
	}

	public int getNoise_type() {
		return noise_type.getSelectedIndex();
	}

	public String getTraining_size() {
		return training_size.getText();
	}

	public String getTest_size() {
		return test_size.getText();
	}

	public JComboBox<String> getMode() {
		return mode;
	}

	public String getHidden_layer_size() {
		return hidden_layer_size.getText();
	}

	public String getNumber_of_epochs() {
		return number_of_epochs.getText();
	}

	public String getLearning_rate() {
		return learning_rate.getText();
	}

}
