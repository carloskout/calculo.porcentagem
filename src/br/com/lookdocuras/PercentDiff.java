package br.com.lookdocuras;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;

public class PercentDiff extends JFrame {

	private JPanel contentPane;
	private JTextField jtfCurrentValue;
	private JTextField jtfNewValue;
	private JTextField jtfResult;
	private JTextField jtfPercentCurrentValue;
	private JTextField jtfPercentNewValue;
	private JButton btnClear;
	DecimalFormat df = new DecimalFormat("###.##");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PercentDiff frame = new PercentDiff();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public PercentDiff() throws Exception {
		setResizable(false);
		setTitle("Calculo de porcentagem");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(338, 200);
		setLocationRelativeTo(null);
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0xef, 0xec, 0xec));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblCurrentValue = new JLabel("Valor Atual");
		lblCurrentValue.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCurrentValue.setBounds(10, 11, 66, 14);
		contentPane.add(lblCurrentValue);
		
		jtfCurrentValue = new JTextField();
		jtfCurrentValue.setFont(new Font("Arial", Font.PLAIN, 20));
		jtfCurrentValue.setBounds(10, 25, 84, 33);
		contentPane.add(jtfCurrentValue);
		jtfCurrentValue.setColumns(10);
		
		jtfCurrentValue.addKeyListener(new CurrentValueTextField());
		
		JLabel lblNewValue = new JLabel("Novo Valor");
		lblNewValue.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewValue.setBounds(118, 11, 66, 14);
		contentPane.add(lblNewValue);
		
		jtfNewValue = new JTextField();
		jtfNewValue.setFont(new Font("Arial", Font.PLAIN, 20));
		jtfNewValue.setColumns(10);
		jtfNewValue.setBounds(118, 25, 84, 33);
		contentPane.add(jtfNewValue);
		jtfNewValue.addKeyListener(new NewValueTextField());
		
		
		JLabel lblResult = new JLabel("Diferença");
		lblResult.setFont(new Font("Arial", Font.PLAIN, 12));
		lblResult.setBounds(228, 11, 66, 14);
		contentPane.add(lblResult);
		
		jtfResult = new JTextField();
		jtfResult.setEnabled(false);
		jtfResult.setFont(new Font("Arial", Font.PLAIN, 20));
		jtfResult.setColumns(10);
		jtfResult.setBounds(228, 25, 84, 33);
		contentPane.add(jtfResult);
		
		jtfPercentCurrentValue = new JTextField();
		jtfPercentCurrentValue.setBounds(61, 63, 33, 16);
		contentPane.add(jtfPercentCurrentValue);
		jtfPercentCurrentValue.setColumns(10);
		jtfPercentCurrentValue.addKeyListener(new CalcPercentActualField());
		
		JLabel lblPercent1 = new JLabel("%");
		lblPercent1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPercent1.setBounds(50, 63, 11, 14);
		contentPane.add(lblPercent1);
		
		JLabel lblPercent2 = new JLabel("%");
		lblPercent2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPercent2.setBounds(158, 63, 11, 14);
		contentPane.add(lblPercent2);
		
		jtfPercentNewValue = new JTextField();
		jtfPercentNewValue.setColumns(10);
		jtfPercentNewValue.setBounds(169, 63, 33, 16);
		contentPane.add(jtfPercentNewValue);
		jtfPercentNewValue.addKeyListener(new CalcPercentActualField());
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 90, 302, 1);
		contentPane.add(separator);
		
		btnClear = new JButton("Limpar");
		btnClear.setBounds(223, 110, 89, 23);
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
		contentPane.add(btnClear);
		
		JButton btnExit = new JButton("Sair");
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(10, 110, 89, 23);
		contentPane.add(btnExit);
		
		JLabel lblDev = new JLabel("Desenvolvido por Carlos Coutinho");
		lblDev.setForeground(Color.GRAY);
		lblDev.setFont(new Font("Arial", Font.PLAIN, 10));
		lblDev.setBounds(77, 144, 167, 14);
		contentPane.add(lblDev);
		
	}
	
	private void processEntry(KeyEvent e) {
		String key = String.valueOf(e.getKeyChar()).toLowerCase();
		JTextField source = (JTextField) e.getSource();
		if(!key.matches("[0-9]|,") 
				|| (key.equals(",") && source.getText().isEmpty()) ) {
			e.consume();
		}
	}
	
	private void calcPercentDiff() {
		if(jtfCurrentValue.getText().isEmpty() || jtfNewValue.getText().isEmpty())
			return;
		
		double curValue = Double.parseDouble(jtfCurrentValue.getText().replace(",", "."));
		double newValue = Double.parseDouble(jtfNewValue.getText().replace(",", "."));
		double result = ((newValue - curValue) * 100) / curValue;
		jtfResult.setText(df.format(result) + "%");
		btnClear.requestFocus();
	}
	
	private void calcPercent(KeyEvent e) {
		JTextField output = null;
		JTextField source = (JTextField) e.getSource();
		
		if(source.getText().isEmpty())
			return;
		
		double percent = Double.parseDouble(source.getText().replace(",", "."));
		double value = 0;
		
		if(source == jtfPercentCurrentValue && !jtfCurrentValue.getText().isEmpty()) {
			output = jtfCurrentValue;
			value = Double.parseDouble(jtfCurrentValue.getText().replace(",", "."));
		} else if(source == jtfPercentNewValue && !jtfNewValue.getText().isEmpty()){
			output = jtfNewValue;
			value = Double.parseDouble(jtfNewValue.getText().replace(",", "."));
		}
		
		double result = ((value * percent) / 100) + value;
		output.setText(String.valueOf(df.format(result)));
	}
	
	private class CurrentValueTextField extends KeyAdapter {
	
		private final int ENTER = 10;
		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			if(e.getKeyCode() == ENTER) {
				jtfNewValue.requestFocus();
			}
			
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			super.keyTyped(e);
			processEntry(e);
		} 
	}
	
	private class NewValueTextField extends KeyAdapter {
		private final int ENTER = 10;
		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			if(e.getKeyCode() == ENTER) {
				calcPercentDiff();
			}
			
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			super.keyTyped(e);
			processEntry(e);
		} 
	}
	
	private class CalcPercentActualField extends KeyAdapter {
		private final int ENTER = 10;
		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			if(e.getKeyCode() == ENTER) {
				calcPercent(e);
			}
			
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			super.keyTyped(e);
			processEntry(e);
		} 
	}
}
