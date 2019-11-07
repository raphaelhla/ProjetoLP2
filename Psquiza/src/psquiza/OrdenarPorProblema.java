package psquiza;

import java.util.Comparator;

/**
 * Classe de ordenacao de pesquisas do criterio problema.
 * 
 * @author Weslley Azevedo - 119111241
 *
 */
public class OrdenarPorProblema implements Comparator<Pesquisa> {

	/**
	 * Compara duas pesquisas utilizando como criteiro de ordem a quantidade de
	 * problemas, a chave dos problemas e o codigo da pesquisa.
	 */
	public int compare(Pesquisa p1, Pesquisa p2) {
		if (p1.getTemProblemaAssociado() && p2.getTemProblemaAssociado()) {
			if (p1.getCodigoProblema().equals(p2.getCodigoProblema())) {
				return p1.getCodigo().compareTo(p2.getCodigo());
			}
			return p1.getCodigoProblema().compareTo(p2.getCodigoProblema());
		} else if (p1.getTemProblemaAssociado() && !p2.getTemProblemaAssociado()) {
			return 1;
		} else if (!p1.getTemProblemaAssociado() && p2.getTemProblemaAssociado()) {
			return -1;
		}
		return p1.getCodigo().compareTo(p2.getCodigo());
	}
}
