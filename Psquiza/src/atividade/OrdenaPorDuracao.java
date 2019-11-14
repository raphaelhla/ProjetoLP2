package atividade;

import java.util.Comparator;

/**
 * Classe de ordenacao de atividades.
 * 
 * @author Raphael Agra - 119110413
 *
 */
public class OrdenaPorDuracao implements Comparator<Atividade> {

	/**
	 * Compara duas atividades utilizando como criterio a duracao de cada uma.
	 */
	public int compare(Atividade a1, Atividade a2) {
		if (a1.getDuracao() > a2.getDuracao()) {
			return -1;
		}
		return 1;
	}

}
