package psquiza;

/**
 * Classe que representa um pesquisador de especialidade professor.
 * 
 * 
 * @author matheus
 *
 */
public class PesquisadorProfessor implements Especialidade {

	/**
	 * String que representa a formacao do pesquisador professor.
	 */
	private String formacao;

	/**
	 * String que representa a unidade alocada para o pesquisador professor.
	 */
	private String unidadeAlocada;

	/**
	 * String que representa a data da contratacao do pesquisador professor.
	 * 
	 */
	private String dataContratacao;

	/**
	 * Validador utilizado para validar entradas do sistema.
	 */
	private Validador validador;

	/**
	 * Constroi um pesquisador professor a partir da formacao, unidade alocada e
	 * data da contratacao.
	 * 
	 * @param formacao        String que representa a formacao do pesquisador
	 *                        professor.
	 * @param unidadeAlocada  String que representa a unidade alocada para o
	 *                        pesquisador professor.
	 * @param dataContratacao String que representa a data da contratacao do
	 *                        pesquisador professor.
	 */
	public PesquisadorProfessor(String formacao, String unidadeAlocada, String dataContratacao) {
		this.validador = new Validador();
		validador.verificaEntradaNulaVazia(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(unidadeAlocada, "Campo unidade nao pode ser nulo ou vazio.");
		validador.verificaData(dataContratacao);
		this.formacao = formacao;
		this.unidadeAlocada = unidadeAlocada;
		this.dataContratacao = dataContratacao;

	}

	/**
	 * Metodo que retorna a formacao do pesquisador professor.
	 * 
	 * @return formacao do pesquisador professor.
	 */
	public String getFormacao() {
		return formacao;
	}

	/**
	 * Metodo que retorna a unidade alocada para o pesquisador professor.
	 * 
	 * @return unidade alocada para o pesquisador professor.
	 */
	public String getUnidadeAlocada() {
		return unidadeAlocada;
	}

	/**
	 * Metodo que retorna a data da contratacao do pesquisador professor.
	 * 
	 * @return data da contratacao do pesquisador professor.
	 */
	public String getDataContratacao() {
		return dataContratacao;
	}

	/**
	 * Metodo que retorna o toString do pesquisador professor.
	 * 
	 * @return String que representa o toString do pesquisador professor.
	 */
	@Override
	public String toString() {
		return " - " + this.formacao + " - " + this.unidadeAlocada + " - " + this.dataContratacao;
	}

	/**
	 * Metodo que edita um pesquisador professor com o atributo a ser editado e um
	 * novo valor para o atributo.
	 * 
	 * @param atributo  Atributo que vai ser editado.
	 * @param novoValor Novo valor para o atributo.
	 */
	@Override
	public void alteraPesquisador(String atributo, String novoValor) {
		validador.verificaEntradaNulaVazia(atributo, "Atributo nao pode ser vazio ou nulo.");
		validador.verificaEntradaNulaVazia(novoValor, "novoValor nao pode ser vazio ou nulo.");
		switch (atributo) {
		case "FORMACAO":
			this.formacao = novoValor;
			break;
		case "UNIDADE":
			this.unidadeAlocada = novoValor;
			break;
		case "DATA":
			this.dataContratacao = novoValor;
			break;
		}
	}
}
