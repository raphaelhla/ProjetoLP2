package psquiza;

/**
 * Classe que representa uma especialidade de um pesquisador. Possui um toString
 * vazio, pois e a parte que difere dos demais pesquisadores.
 * 
 * @author matheus
 */
public interface Especialidade {

	/*
	 * Metodo toString da especialidade a ser implementada pelas demais classes que
	 * implementam especialidade.
	 * 
	 */
	public String toString();

	/**
	 * Metodo que vai alterar uma tributo de um objeto pesquisador.
	 * 
	 * @param atributo  atributo a ser alterado.
	 * @param novoValor novo valor desse atributo.
	 */
	public void alteraPesquisador(String atributo, String novoValor);
	
}
