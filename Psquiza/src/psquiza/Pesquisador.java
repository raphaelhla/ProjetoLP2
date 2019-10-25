package psquiza;

public class Pesquisador {

	private String nome;
	private String funcao;
	private String biografia;
	private String email;
	private String fotoURL;
	private String status;
	private Validador validador;

	public Pesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		validador = new Validador();
		validador.verificaEntradaNulaVazia(nome, "Campo nome nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(email, "Campo email nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(fotoURL, "Campo fotoURL nao pode ser nulo ou vazio.");
		validador.verificaEmail(email);
		validador.verificafotoURL(fotoURL);
		
		this.nome = nome;
		this.funcao = funcao;
		this.biografia = biografia;
		this.email = email;
		this.fotoURL = fotoURL;
		this.status = "ativo";
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFotoURL(String fotoURL) {
		this.fotoURL = fotoURL;
	}
	
	public String getStatus() {
		return this.status;
	}
	public void ativaPesquisador() {
		this.status = "ativo";
	}
	
	public void desativaPesquisador() {
		this.status = "inativo";
	}

	@Override
	public String toString() {
		return this.nome + " (" + this.funcao + ") - " + this.biografia + " - " + this.email + " - " + this.fotoURL;
	}
	
}
