package psquiza;

import java.util.Comparator;

public class OrdenarPorProblema implements Comparator<Pesquisa> {
	public int compare(Pesquisa p1, Pesquisa p2) {
		if (p1.getCodigoProblema().equals(p2.getCodigoProblema())) {
			return p1.getCodigo().compareTo(p2.getCodigo());
		}
		return p1.getCodigoProblema().compareTo(p2.getCodigoProblema());
	}

}
