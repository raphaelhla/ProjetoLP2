package psquiza;

import easyaccept.EasyAccept;

public class Facade {

	public static void main(String[] args) {
		args = new String[] { "psquiza.Facade", /**"testes_easyaccept/use_case_1.txt",*/
				"testes_easyaccept/use_case_2.txt",
				"testes_easyaccept/use_case_3.txt",
				 /** "testes_easyaccept/use_case_4.txt" */};
		EasyAccept.main(args);
	}

	private PesquisadorController pesquisadorController;
	private ProblemaController problemaController;
	private ObjetivoController objetivoController;
	private AtividadeController atividadeController;

	public Facade() {
		this.pesquisadorController = new PesquisadorController();
		this.problemaController = new ProblemaController();
		this.objetivoController = new ObjetivoController();
		this.atividadeController = new AtividadeController();
		
	}

	// METODOS DA PARTE 1 (MATHEUS) ABAIXO DESSE COMENTARIO

	// METODOS DA PARTE 2 (RAPHAEL) ABAIXO DESSE COMENTARIO

	/**
	 * Metodo que cadastra um pesquisador a partir do seu nome, funcao, biografia,
	 * email e fotoURL.
	 * 
	 * @param nome      Nome do pesquisador.
	 * @param funcao    Funcao do pesquisador.
	 * @param biografia Biografia do pesquisador.
	 * @param email     Email do pesquisador.
	 * @param fotoURL   Url da foto do pesquisador.
	 */
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		this.pesquisadorController.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
	}

	/**
	 * Metodo que edita um pesquisador a partir do seu email, do atributo a ser
	 * editado e um novo valor para o atributo.
	 * 
	 * @param email     Email do pesquisador.
	 * @param atributo  Atributo que vai ser editado.
	 * @param novoValor Novo valor para o atributo.
	 */
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		this.pesquisadorController.alteraPesquisador(email, atributo, novoValor);
	}

	/**
	 * Metodo responsavel por ativar o pesquisador a partir do seu email.
	 * 
	 * @param email Email do pesquisador
	 */
	public void ativaPesquisador(String email) {
		this.pesquisadorController.ativaPesquisador(email);
	}

	/**
	 * Metodo responsavel por desativar o pesquisador a partir do seu email.
	 * 
	 * @param email Email do pesquisador
	 */
	public void desativaPesquisador(String email) {
		this.pesquisadorController.desativaPesquisador(email);
	}

	/**
	 * Metodo que retorna a representacao em string do pesquisador a partir do seu
	 * cpf.
	 * 
	 * @param email Email do pesquisador
	 * @return retorna a representacao em string do pesquisador
	 */
	public String exibePesquisador(String email) {
		return this.pesquisadorController.exibePesquisador(email);
	}
	
	/**
	 * Metodo que verifica se um pesquisador é ativo ou nao, a partir do seu email.
	 * 
	 * @param email Email do pesquisador.
	 * @return um valor booleano verdade caso o pesquisador esteja ativo, caso
	 *         contrario retorna falso.
	 */
	public boolean pesquisadorEhAtivo(String email) {
		return this.pesquisadorController.pesquisadorEhAtivo(email);
	}

	// METODOS DA PARTE 3 (ALISSON) ABAIXO DESSE COMENTARIO

	public String cadastraProblema(String descricao, int viabilidade) {
		return this.problemaController.cadastraProblema(descricao, viabilidade);
	}
	
	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		return this.objetivoController.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}
	
	public void apagarProblema(String codigo) {
		this.problemaController.apagarProblema(codigo);
	}
	
	public void apagarObjetivo(String codigo) {
		this.objetivoController.apagarObjetivo(codigo);
	}
	
	public String exibeProblema(String codigo) {
		return this.problemaController.exibeProblema(codigo);
	}
	
	public String exibeObjetivo(String codigo) {
		return this.objetivoController.exibeObjetivo(codigo);
	}
	
	// METODOS DA PARTE 4 (WESLLEY) ABAIXO DESSE COMENTARIO
	
	public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		return this.atividadeController.cadastraAtividade(descricao, nivelRisco, descricaoRisco);
	}

	public void apagaAtividade(String codigo) {
		this.atividadeController.apagaAtividade(codigo);
	}

	public void cadastraItem(String codigo, String item) {
		this.atividadeController.cadastraItem(codigo, item);
	}

	public String exibeAtividade(String codigo) {
		return this.atividadeController.exibeAtividade(codigo);
	}

	public int contaItensPendentes(String codigo) {
		return this.atividadeController.contaItensPedentes(codigo);
	}

	public int contaItensRealizados(String codigo) {
		return this.atividadeController.contaItensRealizados(codigo);
	}
}
