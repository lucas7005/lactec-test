package telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dataBase.ComandosBanco;

public class Cadastro {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_1;
	private JTextField textField_2;
	private JLabel label;


	public Cadastro() {
		initialize();
	}

	//Cria a tela de cadastro
	private void initialize() {
		frame = new JFrame("Cadastrar Cliente");
		frame.setBounds(100, 100, 356, 219);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 320, 158);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(44, 8, 39, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(88, 5, 185, 25);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_1.setColumns(10);
		panel.add(textField_1);
		
		lblNewLabel_1 = new JLabel("Idade");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(44, 42, 46, 14);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(88, 37, 185, 25);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_2.setColumns(10);
		textField_2.setBounds(88, 67, 185, 25);
		panel.add(textField_2);
		
		label = new JLabel("Tel");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(44, 72, 46, 14);
		panel.add(label);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.setBounds(44, 124, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//Executa a função de cadastrar o cliente no banco de dados
					new ComandosBanco().cadastraCliente(textField_1.getText(), Integer.parseInt(textField.getText()), textField_2.getText());
					frame.dispose();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro!");
				}
				
			}
		});
		
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBounds(184, 124, 89, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//fecha a janela de consulta sem fechar o programa
				frame.dispose();
				
			}
		});
		panel.add(btnNewButton_1);
		frame.setVisible(true);
	}
}
