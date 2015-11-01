package manager;

import java.util.ArrayList;
import java.util.List;
import easyaccept.EasyAcceptFacade;

public class Main {
	public static void main(String[] args) {
		List<String> testes = new ArrayList<String>();
		Facade facade = new Facade();
		 
		testes.add("resources/teste_aceitacao/usecase_1.txt");
		testes.add("resources/teste_aceitacao/usecase_2.txt");
//		testes.add("resources/teste_aceitacao/usecase_3.txt");
//		testes.add("resources/teste_aceitacao/usecase_4.txt");
		 
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(facade, testes);
		eaFacade.executeTests();
		System.out.println(eaFacade.getCompleteResults());
	}
}
