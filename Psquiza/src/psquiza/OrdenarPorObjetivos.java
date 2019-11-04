package psquiza;

import java.util.Comparator;

public class OrdenarPorObjetivos implements Comparator<Pesquisa> {
	public int compare(Pesquisa p1, Pesquisa p2) {
		if (p1.getQtdObjetivo() == p2.getQtdObjetivo()) {
			return p1.getCodigo().compareTo(p2.getCodigo());
		}
		return p1.getQtdObjetivo() - p2.getQtdObjetivo();
	}
}
