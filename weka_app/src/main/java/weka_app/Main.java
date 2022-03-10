package weka_app;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

	public static void create(JFrame window) {
		Panel panel = new Panel();
		Controller ctr = new Controller(panel);
		panel.controller(ctr);
		window.setContentPane(panel);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame window = new JFrame("MLP Weka App");
				window.setLocation(0, 0);
				window.setResizable(true);
				window.setLocationRelativeTo(null);
				create(window);
			}
		});

	}

}
