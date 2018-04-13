package telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dataBase.ComandosBanco;
import regras.Cliente;

public class Listar {

	private JFrame frame;
	DefaultTableModel modelo; //Cria o template da tabela


	public Listar() {
		initialize();
	}

	//Cria a tela para listar e chama as funções para os botões.
	private void initialize() {
		frame = new JFrame("Lista de Clientes");
		// Faz o cabeçalho da tabela
		modelo = new DefaultTableModel();
		modelo.addColumn("ID");
		modelo.addColumn("Nome");
		modelo.addColumn("Idade");
		modelo.addColumn("Telefone");

		JTable table = new JTable(modelo);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JRadioButton rdbtnClassificarPorIdade = new JRadioButton("CLassificar por Idade");
		rdbtnClassificarPorIdade.setBounds(6, 7, 184, 23);
		frame.getContentPane().add(rdbtnClassificarPorIdade);

		JButton btnListarClientes = new JButton("Listar Clientes");
		btnListarClientes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				List<Cliente> cliente = new ArrayList<Cliente>();
				
				//Zera a tabela antes de preencher com outro tipo de ordenação
				modelo.setRowCount(0);
				
				if (rdbtnClassificarPorIdade.isSelected()) {
					
					//list cliente recebe o resultado do list que buscou os dados no banco tipo 2 ordenado por idade 
					cliente = new ComandosBanco().listarClientes(2);
					
					//Preenche a tabela com o list 
					for (Cliente cliente2 : cliente) {
						modelo.addRow(new Object[] { cliente2.getId(), cliente2.getNome(), cliente2.getIdade(), cliente2.getTelefone() });
					}
				}else {
					//list cliente recebe o resultado do list que buscou os dados no banco tipo 1 sem ordenação, somente aparecendo ID e nome
					cliente = new ComandosBanco().listarClientes(1);
					
					//Preenche a tabela com o list 
					for (Cliente cliente2 : cliente) {
						modelo.addRow(new Object[] { cliente2.getId(), cliente2.getNome()});
					}
					
				}

			}

		});
		
		btnListarClientes.setBounds(209, 7, 127, 23);
		frame.getContentPane().add(btnListarClientes);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 41, 414, 209);
		frame.getContentPane().add(scrollPane);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
