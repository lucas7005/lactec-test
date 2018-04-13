package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import regras.Cliente;

public class ComandosBanco {

	private Connection conn;
	private ResultSet rs;
	private PreparedStatement pstmts;
	//Configuração do banco
	private final String url = "jdbc:mysql://localhost:3306/lactec"; //Endereço do banco de dados
	private final String user = "root"; //usuario do banco de dados
	private final String password = "12345"; //senha do banco de dados
	private final String driver = "com.mysql.jdbc.Driver"; //driver do banco de dados
	private final String banco = "mydb"; //Aqui deve ser inserido o nome do banco de dados
	
	//Realiza a conexão com o banco de dados
	public Connection connect() {

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return conn;
		
	}

	// Realiza o cadastro do cliente
	public void cadastraCliente(String nome, int idade, String telefone) {

		try {
			
			pstmts = connect().prepareStatement("INSERT INTO "+banco+".cliente (nome, idade, telefone) VALUES ('" + nome + "',"
					+ idade + ",'" + telefone + "');");
			pstmts.execute();
			
			JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");

		}

		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro!");
			e.printStackTrace();
		} 
		
		finally {
			if (conn != null) {
				try {
					conn.close();

				} catch (SQLException e1) {

					e1.getStackTrace();

				}
			}
		}

		

	}
	
	//Realiza a busca do cliente e retorna em String
	public String buscaCliente(String dado) {
		String result = "";
		
		//tenta realizar a busca pelo ID do cliente
		try {

			pstmts = connect().prepareStatement(
					"SELECT id,nome,idade,telefone FROM "+banco+".cliente WHERE id =" + Integer.parseInt(dado) + ";");
			rs = pstmts.executeQuery();

			if (rs.next()) {
				result = "Id: " + rs.getString(1) + "\nNome: " + rs.getString(2) + "\nIdade: " + rs.getString(3)
						+ "\nTelefone: " + rs.getString(4);
			}

		}

		catch (java.lang.NumberFormatException e) {
			
			//Caso entre no erro por não conseguir converter uma palavra em número, é realizada a busca pelo nome
			try {

				pstmts = connect().prepareStatement(
						"SELECT id,nome,idade,telefone FROM "+banco+".cliente WHERE nome ='" + dado + "';");
				rs = pstmts.executeQuery();
				if (rs.next()) {
					result = "Id: " + rs.getString(1) + "\nNome: " + rs.getString(2) + "\nIdade: " + rs.getString(3)
							+ "\nTelefone: " + rs.getString(4);
					System.out.println(result);
				}

			} catch (Exception e2) {

				JOptionPane.showMessageDialog(null, "Cliente Não encontrado!");
				e2.printStackTrace();

			}

		}

		catch (Exception e) {

			e.printStackTrace();
		}

		finally {
			if (conn != null) {
				try {
					conn.close();

				} catch (SQLException e1) {

					System.out.print(e1.getStackTrace());

				}
			}
		}
		return result;

	}

	//Exclui o cliente, retorna boolean para confirmar a exclusão
	public boolean excluiCliente(String dado) {

		boolean resultado = false;

		try {
			
			//realiza a busca do cliente primeiro para verificar se o mesmo existe, caso exista ele delte pelo ID.
			if (buscaCliente(dado) != "") {
				
				pstmts = connect().prepareStatement("DELETE FROM "+banco+".cliente WHERE id =" + Integer.parseInt(dado) + ";");
				pstmts.execute();
				JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso!");
				resultado = true;

			} else {
				JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
			}

		}
		
		
		catch (java.lang.NumberFormatException e) {
			
			//se não conseguir converter o dado informado para número, é passado o nome do cliente para exclusão. 
			try {

				pstmts = conn.prepareStatement("DELETE FROM "+banco+".cliente WHERE nome ='" + dado + "';");

				pstmts.execute();

				JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso!");

				resultado = true;

			} catch (Exception e2) {

				JOptionPane.showMessageDialog(null, "Cliente não encontrado!");

			}

			e.printStackTrace();
		}

		catch (Exception e) {

			e.printStackTrace();
		}

		finally {
			if (conn != null) {
				try {
					conn.close();

				} catch (SQLException e1) {

					System.out.print(e1.getStackTrace());

				}
			}
		}

		return resultado;
	}
	
	//Realiza a busca de cliente no banco e retorna uma List para preencher uma JTable
	public List<Cliente> listarClientes(int tipo) {
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		Cliente cliente;

		try {
			
			//Se tipo for 1, é listado os clientes sem nenhum tipo de ordenação
			if (tipo == 1) {

				pstmts = connect().prepareStatement("SELECT * FROM "+banco+".cliente;");

				rs = pstmts.executeQuery();

				while (rs.next()) {
					cliente = new Cliente(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));

					listaCliente.add(cliente);

				}
			
			//Se tipo 2, é listado os clientes ordenados pela idade
			}else if(tipo == 2) {

				pstmts = connect().prepareStatement("SELECT * FROM "+banco+".cliente ORDER BY idade;");

				rs = pstmts.executeQuery();

				while (rs.next()) {
					cliente = new Cliente(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));

					listaCliente.add(cliente);

				}

			}

		}

		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Erro");

			e.printStackTrace();
		}

		finally {
			if (conn != null) {
				try {
					conn.close();

				} catch (SQLException e1) {

					System.out.print(e1.getStackTrace());

				}
			}
		}
		
		
		return listaCliente;

	}


}
