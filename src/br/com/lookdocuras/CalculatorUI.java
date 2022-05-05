package br.com.lookdocuras;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.lookdocuras.model.Div;
import br.com.lookdocuras.model.Mult;
import br.com.lookdocuras.model.Operation;
import br.com.lookdocuras.model.State;
import br.com.lookdocuras.model.Sub;
import br.com.lookdocuras.model.Sum;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import java.awt.Cursor;

public class CalculatorUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField jtfCurrentValue;
	private JTextField jtfNewValue;
	private JTextField jtfResult;
	private JTextField jtfPercentCurrentValue;
	private JTextField jtfPercentNewValue;
	private JButton btnClear;
	DecimalFormat df = new DecimalFormat("###.##");
	private JTextField jftDisplay;
	private Operation operation;
	private State state;

	private final Pattern inputIsValid = Pattern.compile("[0-9]|[\\-+*/%=]");
	private final Pattern isNumber = Pattern.compile("[0-9]");
	private final Pattern isOperator = Pattern.compile("[\\-+*/%=]");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorUI frame = new CalculatorUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public CalculatorUI() throws Exception {
		setResizable(false);
		setTitle("Calculo de porcentagem");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(360, 409);
		setLocationRelativeTo(null);
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0xef, 0xec, 0xec));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		state = State.INFINITY;

		JButton btnExit = new JButton("Sair");
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(9, 325, 89, 23);
		contentPane.add(btnExit);

		JLabel lblDev = new JLabel("Desenvolvido por Carlos Coutinho");
		lblDev.setForeground(Color.GRAY);
		lblDev.setFont(new Font("Arial", Font.PLAIN, 10));
		lblDev.setBounds(168, 329, 167, 14);
		contentPane.add(lblDev);

		JPanel panelPercent = new JPanel();
		panelPercent.setBorder(new TitledBorder(null, "C\u00E1lculo de Porcentagem", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelPercent.setBounds(9, 20, 326, 121);
		contentPane.add(panelPercent);
		panelPercent.setLayout(null);

		JLabel lblCurrentValue = new JLabel("Valor Atual");
		lblCurrentValue.setBounds(12, 26, 66, 14);
		panelPercent.add(lblCurrentValue);
		lblCurrentValue.setFont(new Font("Arial", Font.PLAIN, 12));

		jtfCurrentValue = new JTextField();
		jtfCurrentValue.setBounds(12, 40, 84, 33);
		panelPercent.add(jtfCurrentValue);
		jtfCurrentValue.setFont(new Font("Arial", Font.PLAIN, 20));
		jtfCurrentValue.setColumns(10);

		JLabel lblNewValue = new JLabel("Novo Valor");
		lblNewValue.setBounds(120, 26, 66, 14);
		panelPercent.add(lblNewValue);
		lblNewValue.setFont(new Font("Arial", Font.PLAIN, 12));

		jtfNewValue = new JTextField();
		jtfNewValue.setBounds(120, 40, 84, 33);
		panelPercent.add(jtfNewValue);
		jtfNewValue.setFont(new Font("Arial", Font.PLAIN, 20));
		jtfNewValue.setColumns(10);

		JLabel lblResult = new JLabel("Diferença");
		lblResult.setBounds(230, 26, 66, 14);
		panelPercent.add(lblResult);
		lblResult.setFont(new Font("Arial", Font.PLAIN, 12));

		jtfResult = new JTextField();
		jtfResult.setBounds(230, 40, 84, 33);
		panelPercent.add(jtfResult);
		jtfResult.setEnabled(false);
		jtfResult.setFont(new Font("Arial", Font.PLAIN, 20));
		jtfResult.setColumns(10);

		JLabel lblPercent1 = new JLabel("%");
		lblPercent1.setBounds(52, 84, 11, 14);
		panelPercent.add(lblPercent1);
		lblPercent1.setFont(new Font("Arial", Font.PLAIN, 12));

		jtfPercentCurrentValue = new JTextField();
		jtfPercentCurrentValue.setBounds(63, 78, 33, 23);
		panelPercent.add(jtfPercentCurrentValue);
		jtfPercentCurrentValue.setFont(new Font("Arial", Font.PLAIN, 13));
		jtfPercentCurrentValue.setColumns(10);

		JLabel lblPercent2 = new JLabel("%");
		lblPercent2.setBounds(160, 84, 11, 14);
		panelPercent.add(lblPercent2);
		lblPercent2.setFont(new Font("Arial", Font.PLAIN, 12));

		jtfPercentNewValue = new JTextField();
		jtfPercentNewValue.setBounds(171, 78, 33, 23);
		panelPercent.add(jtfPercentNewValue);
		jtfPercentNewValue.setFont(new Font("Arial", Font.PLAIN, 13));
		jtfPercentNewValue.setColumns(10);

		btnClear = new JButton("Limpar");
		btnClear.setBounds(230, 78, 83, 23);
		panelPercent.add(btnClear);

		JPanel panelCalc = new JPanel();
		panelCalc.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Calculadora", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCalc.setBounds(9, 154, 326, 137);
		contentPane.add(panelCalc);
		panelCalc.setLayout(null);

		jftDisplay = new JTextField();
		jftDisplay.setBackground(Color.WHITE);
		jftDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		jftDisplay.setFont(new Font("Arial", Font.PLAIN, 50));
		jftDisplay.setEditable(false);
		jftDisplay.setBounds(10, 32, 302, 78);
		jftDisplay.addKeyListener(new DisplayKeyListener());
		panelCalc.add(jftDisplay);
		jftDisplay.setColumns(10);

		JLabel lblOutput = new JLabel("5 + 5 = 10");
		lblOutput.setFont(new Font("Arial", Font.BOLD, 12));
		lblOutput.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOutput.setBounds(10, 14, 302, 14);
		panelCalc.add(lblOutput);
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jtfCurrentValue.setText("");
				jtfNewValue.setText("");
				jtfResult.setText("");
				jtfCurrentValue.requestFocus();
				jtfPercentCurrentValue.setText("");
				jtfPercentNewValue.setText("");
			}
		});
		jtfPercentNewValue.addKeyListener(new CalcPercentActualField());
		jtfPercentCurrentValue.addKeyListener(new CalcPercentActualField());
		jtfNewValue.addKeyListener(new NewValueTextField());

		jtfCurrentValue.addKeyListener(new CurrentValueTextField());

	}

	private void checkInput(KeyEvent e) {
		String key = String.valueOf(e.getKeyChar()).toLowerCase();
		JTextField source = (JTextField) e.getSource();
		if (!key.matches("[0-9]|,") || (key.equals(",") && source.getText().isEmpty())) {
			e.consume();
		}
	}

	private void calcPercentDiff() {
		if (jtfCurrentValue.getText().isEmpty() || jtfNewValue.getText().isEmpty())
			return;

		double curValue = Double.parseDouble(jtfCurrentValue.getText().replace(",", "."));
		double newValue = Double.parseDouble(jtfNewValue.getText().replace(",", "."));
		double result = ((newValue - curValue) * 100) / curValue;
		jtfResult.setText(df.format(result) + "%");
		btnClear.requestFocus();
	}

	// calculo de porcentagem positiva 5 + 20%
	private void calcPercent(KeyEvent e) {
		JTextField output = null;
		JTextField source = (JTextField) e.getSource();

		if (source.getText().isEmpty())
			return;

		double percent = Double.parseDouble(source.getText().replace(",", "."));
		double value = 0;

		if (source == jtfPercentCurrentValue && !jtfCurrentValue.getText().isEmpty()) {
			output = jtfCurrentValue;
			value = Double.parseDouble(jtfCurrentValue.getText().replace(",", "."));
		} else if (source == jtfPercentNewValue && !jtfNewValue.getText().isEmpty()) {
			output = jtfNewValue;
			value = Double.parseDouble(jtfNewValue.getText().replace(",", "."));
		}

		double result = ((value * percent) / 100) + value;
		output.setText(String.valueOf(df.format(result)));
	}

	private class CurrentValueTextField extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				jtfNewValue.requestFocus();
			}

		}

		@Override
		public void keyTyped(KeyEvent e) {
			super.keyTyped(e);
			checkInput(e);
		}
	}

	private class NewValueTextField extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				calcPercentDiff();
			}

		}

		@Override
		public void keyTyped(KeyEvent e) {
			super.keyTyped(e);
			checkInput(e);
		}
	}

	private class CalcPercentActualField extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				calcPercent(e);
			}

		}

		@Override
		public void keyTyped(KeyEvent e) {
			super.keyTyped(e);
			checkInput(e);
		}
	}

	/********************************************
	 *************** CALCULADORA*****************
	 *******************************************/

	private class DisplayKeyListener extends KeyAdapter {
		private String key = null;
		private String display = "";
		private double x = 0;
		private double y = 0;

		@Override
		public void keyReleased(KeyEvent e) {
			super.keyReleased(e);
			key = String.valueOf(e.getKeyChar());
			processInput(e);
		}

		private void processInput(KeyEvent e) {
			if (e.getKeyCode() == 27) { // esc
				display = "";
				operation = null;
				updateDisplay(display);
				return;
			}

			if (state == State.INFINITY) {

				if (isValid()) {
					if (isNumber()) {
						display += key;
						updateDisplay(display);
					} else {

						if (x > 0 && operation != null) {
							display = String.valueOf(operation.calc(x, toDouble(display)));
							updateDisplay(display);

							if (key.equals("=")) {
								state = State.CALCULATED;
								//display = "";
								operation = null;
							} else {
								prepareExpr();
							}
						} else {
							if (!display.isEmpty()) {
								prepareExpr();
							}
						}
					}
				}
			} else if(state == State.CALCULATED){
				if(isNumber()) {
					display = key;
					updateDisplay(display);
				} else if(!key.equals("=")) {
					prepareExpr();
				}
				state = State.INFINITY;
			}
		}

		private void prepareExpr() {
			x = Double.parseDouble(display);
			display = "";
			operation = getOperation();
		}

		private Operation getOperation() {
			Operation op = null;

			switch (key) {
			case "+":
				op = new Sum();
				break;
			case "-":
				op = new Sub();
				break;
			case "*":
				op = new Mult();
				break;
			case "/":
				op = new Div();
				break;
			}
			return op;
		}

		private double toDouble(String value) {
			return Double.parseDouble(value);
		}

		private boolean isValid() {
			if (inputIsValid.matcher(key).matches())
				return true;
			return false;
		}

		private boolean isNumber() {
			if (isNumber.matcher(key).matches())
				return true;
			return false;
		}

		private void updateDisplay(String content) {
			jftDisplay.setText(content);
		}

	}

}
