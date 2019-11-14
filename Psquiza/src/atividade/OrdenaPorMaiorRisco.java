package atividade;

import java.util.Comparator;

/**
 * Classe de ordenacao de atividades.
 * 
 * @author Raphael Agra - 119110413
 *
 */
public class OrdenaPorMaiorRisco implements Comparator<Atividade> {
	
	/**
	 * Compara duas atividades utilizando como criterio o nivel de risco de cada uma.
	 */
	public int compare(Atividade a1, Atividade a2) {
		if(a1.mapRisco(a1.getNivelRisco()) > a2.mapRisco(a2.getNivelRisco())) {
			return -1;
		}
		return 1;
	}

}
