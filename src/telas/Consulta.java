package telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import dataBase.ComandosBanco;
import javax.swing.JButton;

public class Consulta {

	private JFrame frame;
	private JTextField textField;
	private String resultado;

	public Consulta() {
		initialize();
	}
	
	//Cria a tela de consulta e suas funções
	private void initialize() {
		frame = new JFrame("Consulta Cliente");
		frame.setBounds(100, 100, 383, 183);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(36, 42, 285, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(36, 84, 89, 36);
		btnBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//chama a função para buscar clientes no banco
				resultado = new ComandosBanco().buscaCliente(textField.getText());
				
				//se o resultado for nulo, será retornando mensagem que o cliente não foi encontrado
				if(resultado == "") {
					JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
					frame.dispose();
				}else {
					JOptionPane.showMessageDialog(null, resultado);
				}
				
			}
		});
		frame.getContentPane().add(btnBuscar);
		
		JButton button = new JButton("Cancelar");
		button.setBounds(232, 84, 89, 36);
		button.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//fecha a janela de consulta sem fechar o programa
				frame.dispose();
				
			}
		});
		frame.getContentPane().add(button);
		
		JLabel lblInsiraONome = new JLabel("Insira o nome ou ID");
		lblInsiraONome.setBounds(36, 17, 136, 14);
		frame.getContentPane().add(lblInsiraONome);
		
		JButton button_1 = new JButton("Excluir");
		button_1.setBounds(135, 84, 89, 36);
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//se o resultado da busca de clientes for falsa, retorna ao menu principal, se for verdadeiro exclui o cliente indicado
				if(!new ComandosBanco().excluiCliente(textField.getText())) {
					frame.dispose();
				};
				
			}
		});
		frame.getContentPane().add(button_1);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
