package psquiza;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Facade facade = new Facade();
		facade.cadastraProblema("Pedro amorin", 3);
		facade.cadastraProblema("Eu amo programar", 5);
		facade.cadastraAtividade("Iriamo da silva", "BAIXO", "olhamos para cima");
//		System.out.println(facade.busca("amo", 4));
		Map<String, Integer> teste = new HashMap<>();
		teste.put("um", 1);
		teste.put("dois", 2);
//		List<String> lista = new ArrayList<>(teste.keySet());
//		System.out.println(lista);
	}
}
