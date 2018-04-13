package telas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Main {

	private JFrame frame;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Main() {
		initialize();
	}

	
	//cria a tela do menu principal com os botões e suas ações
	private void initialize() {
		frame = new JFrame("Menu Principal");
		frame.setBounds(100, 100, 450, 338);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setBounds(120, 11, 190, 57);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					
				new Cadastro();

			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("Consultar");
		button.setBounds(120, 77, 190, 57);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new Consulta();
				
			}
		});
		frame.getContentPane().add(button);
		
		JButton button_2 = new JButton("Listar");
		button_2.setBounds(120, 145, 190, 57);
		button_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new Listar();
				
			}
		});
		frame.getContentPane().add(button_2);
		
		JButton button_1 = new JButton("Sair");
		button_1.setBounds(120, 213, 190, 57);
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		frame.getContentPane().add(button_1);
	}
}
