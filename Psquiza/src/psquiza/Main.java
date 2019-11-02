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
//		String x = "amo";
//		System.out.println(x.contains("Amo"));
		Map<Integer, String> teste = new HashMap<>();
		teste.put(1, "oi");
		System.out.println(teste.get(1));
		teste.remove(1);
		System.out.println(teste.get(10));
	}
}
