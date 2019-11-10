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
	 * String que representa a formaçao do pesquisador professor.
	 */
	private String formacao;
	
	/**
	 * String que representa a unidade alocada para o pesquisador professor.
	 */
	private String unidadeAlocada;
	
	/**
	 * String que representa a data da contratação do pesquisador professor.
	 * 
	 */
	private String dataContratacao;

	/**
	 * Constroi um pesquisador professor a partir da formaçao, unidade alocada e data da contrataçao.
	 * 
	 * @param formacao - String que representa a formaçao do pesquisador professor.
	 * @param unidadeAlocada - String que representa a unidade alocada para o pesquisador professor.
	 * @param dataContratacao - String que representa a data da contratação do pesquisador professor.
	 */
	public PesquisadorProfessor(String formacao, String unidadeAlocada, String dataContratacao) {
		this.formacao = formacao;
		this.unidadeAlocada = unidadeAlocada;
		this.dataContratacao = dataContratacao;

	}

	/**
	 * Metodo que retorna a formaçao do pesquisador professor.
	 * 
	 * @return - formaçao do pesquisador professor.
	 */
	public String getFormacao() {
		return formacao;
	}
	
	/**
	 * Metodo que retorna a unidade alocada para o pesquisador professor.
	 * 
	 * @return - unidade alocada para o pesquisador professor.
	 */
	public String getUnidadeAlocada() {
		return unidadeAlocada;
	}

	/**
	 * Metodo que retorna a data da contrataçao do pesquisador professor.
	 * 
	 * @return - data da contrataçao do pesquisador professor.
	 */
	public String getDataContratacao() {
		return dataContratacao;
	}

	/**
	 * Metodo que retorna o toString do pesquisador professor.
	 * 
	 * @return - String que representa o toString do pesquisador professor.
	 */
	@Override
	public String toString() {
		return " - " + this.formacao + " - " + this.unidadeAlocada + " - " + this.dataContratacao;
	}

	/**
	 *  Metodo que edita um pesquisador professor com o atributo a ser
	 * editado e um novo valor para o atributo.
	 * 
	 * @param atributo  Atributo que vai ser editado.
	 * @param novoValor Novo valor para o atributo.
	 */
	@Override
	public void alteraPesquisador(String atributo, String novoValor) {
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
