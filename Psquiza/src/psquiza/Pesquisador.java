package psquiza;

/**
 * Representacao de um pesquisador. Todo pesquisador precisa ter nome, funcao,
 * biografia, email e url da foto.
 * 
 * @author Raphael Agra - 119110413
 *
 */
public class Pesquisador {
	private Especialidade especialidade;

	/**
	 * Nome do pesquisador.
	 */
	private String nome;

	/**
	 * Funcao do pesquisador.
	 */
	private String funcao;

	/**
	 * Biografia do pesquisador.
	 */
	private String biografia;

	/**
	 * Email do pesquisador.
	 */
	private String email;

	/**
	 * URL da foto do pesquisador.
	 */
	private String fotoURL;

	/**
	 * Status do pesquisador.
	 */
	private String status;

	/**
	 * Validador utilizado para lançar excecoes.
	 */
	private Validador validador;

	/**
	 * Constroi um pesquisador a partir do seu nome, funcao, biografia, email e url
	 * da foto.
	 * 
	 * @param nome      Nome do pesquisador.
	 * @param funcao    Funcao do pesquisador.
	 * @param biografia Biografia do pesquisador.
	 * @param email     Email do pesquisador.
	 * @param fotoURL   Url da foto do pesquisador.
	 */
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

	// US6 Matheus
	
	/**
	 * Metodo que vai setar uma especialidade para o pesquisador.
	 * 
	 * @param especialidade - nova especialidade do pesquisador.
	 */
	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	/**
	 * Metodo que vai retornar a especialidade do pesquisador.
	 * 
	 * @return - especialidade do pesquisador.
	 */
	public Especialidade getEspecialidade() {
		return this.especialidade;
	}

	/**
	 * Metodo que vai retornar a funçao do pesquisador.
	 * 
	 * @return - funçao do pesquisador.
	 */
	public String getFuncao() {
		return this.funcao;
	}

	/**
	 * Metodo que altera o nome do pesquisador a partir de um novo nome passado como
	 * parametro.
	 * 
	 * @param nome Nome do pesquisador.
	 */
	
	// US2 Raphael Agra
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Metodo que altera a funcao do pesquisador a partir de uma nova funcao passada
	 * como parametro.
	 * 
	 * @param funcao Funcao do pesquisador.
	 */
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	/**
	 * Metodo que altera a biografia do pesquisador a partir de uma nova biografia
	 * passada como parametro.
	 * 
	 * @param biografia Biografia do pesquisador.
	 */
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	/**
	 * Metodo que altera o email do pesquisador a partir de um novo email passado
	 * como parametro.
	 * 
	 * @param email Email do pesquisador.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Metodo que altera a url da foto do pesquisador a partir de uma nova url
	 * passada como parametro.
	 * 
	 * @param fotoURL Url da foto do pesquisador.
	 */
	public void setFotoURL(String fotoURL) {
		this.fotoURL = fotoURL;
	}

	/**
	 * Metodo que retorna o status do pesquisador.
	 * 
	 * @return retorna o status do pesquisador.
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Metodo que ativa o pesquisador
	 */
	public void ativaPesquisador() {
		this.status = "ativo";
	}

	/**
	 * Metodo que desativa o pesquisador.
	 */
	public void desativaPesquisador() {
		this.status = "inativo";
	}

	/**
	 * Metodo que retorna a representacao em string do pesquisador.
	 * 
	 * @return a representacao em string do pesquisador
	 */
	@Override
	public String toString() {
		return this.nome + " (" + this.funcao + ") - " + this.biografia + " - " + this.email + " - " + this.fotoURL
				+ ((especialidade != null) ? especialidade.toString() : "");
	}

	/**
	 * Metodo que verifica se a biografia do pesquisador contem uma string passada
	 * como parametro e retorna um valor booleano.
	 * 
	 * @param termo Termo ser procurado.
	 * @return verdade se a biografia contem o termo, caso contrario retorna falso.
	 */
	public boolean busca(String termo) {
		return this.biografia.contains(termo);
	}

	/**
	 * Metodo que retorna a biografia do pesquisador.
	 * 
	 * @return a biografia do pesquisador.
	 */
	public String getBiografia() {
		return this.biografia;
	}

	/**
	 * Metodo que retorna o email do pesquisador.
	 * 
	 * @return o email do pesquisador.
	 */
	public String getEmail() {
		return this.email;
	}

	//US6 Matheus
	
	/**
	 * Metodo que vai alterar uma tributo de um objeto pesquisador.
	 * 
	 * @param atributo - atributo a ser alterado.
	 * @param novoValor - novo valor desse atributo.
	 */
	public void alteraAtributoEspecialidade(String atributo, String novoValor) {
		this.especialidade.alteraPesquisador(atributo, novoValor);
	}
}
