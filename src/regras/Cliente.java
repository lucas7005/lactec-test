package regras;

public class Cliente {
	
	private int Id;
	private String nome;
	private int idade;
	private String telefone;
	
	public Cliente(int id, String nome, int idade, String telefone) {
		Id = id;
		this.nome = nome;
		this.idade = idade;
		this.telefone = telefone;
	}
	
	public Cliente() {
		
	}

	public int getId() {
		return Id;
	}

	public String getNome() {
		return nome;
	}

	public int getIdade() {
		return idade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setId(int id) {
		Id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	

}
