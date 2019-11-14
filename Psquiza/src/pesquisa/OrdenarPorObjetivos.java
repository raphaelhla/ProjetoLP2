package pesquisa;

import java.util.Comparator;

/**
 * Classe de ordenacao de pesquisas do criterio objetivos.
 * 
 * @author Weslley Azevedo 119111241
 *
 */
public class OrdenarPorObjetivos implements Comparator<Pesquisa> {

	/**
	 * Compara duas pesquisas utilizando como criterio a quantidade de objetivos e o
	 * codigo da pesquisa.
	 */
	public int compare(Pesquisa p1, Pesquisa p2) {
		if (p1.getQtdObjetivo() == p2.getQtdObjetivo()) {
			return p1.getCodigo().compareTo(p2.getCodigo());
		}
		return p1.getQtdObjetivo() - p2.getQtdObjetivo();
	}
}
