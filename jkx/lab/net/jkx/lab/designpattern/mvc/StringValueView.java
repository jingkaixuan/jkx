package net.jkx.lab.designpattern.mvc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StringValueView implements Observer, IView {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	private IController controller;

	public IController getController() {
		return controller;
	}

	public void setController(IController controller) {
		this.controller = controller;
	}

	public IModel getModel() {
		return model;
	}

	public void setModel(IModel model) {
		this.model = model;
	}

	private IModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StringValueView window = new StringValueView();
					StringValueModel model = new StringValueModel();
					model.addObserver(window);
					StringValueController controller = new StringValueController(window, model);
					window.setController(controller);
					window.setModel(model);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StringValueView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 144);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 434, 0 };
		gridBagLayout.rowHeights = new int[] { 30, 23, 30, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		frame.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("\u62F7\u8D1D");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StringValueView.this.controller.setValue();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 0;
		gbc_textField_1.gridy = 2;
		frame.getContentPane().add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
	}

	@Override
	public void update(IModel model) {
		this.textField_1.setText(model.getString());
		
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return this.textField.getText();
	}
}
