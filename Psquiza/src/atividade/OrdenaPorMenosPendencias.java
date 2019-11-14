package atividade;

import java.util.Comparator;

/**
 * Classe de ordenacao de atividades.
 * 
 * @author Raphael Agra - 119110413
 *
 */
public class OrdenaPorMenosPendencias implements Comparator<Atividade> {

	/**
	 * Compara duas atividades utilizando como criterio a quantidade de itens
	 * pendentes de cada uma.
	 */
	public int compare(Atividade a1, Atividade a2) {
		if (a1.getQtdItensPendentes() < a2.getQtdItensPendentes()) {
			return -1;
		}
		return 1;
	}

}
