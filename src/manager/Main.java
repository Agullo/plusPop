package manager;

import java.util.ArrayList;
import java.util.List;
import easyaccept.EasyAcceptFacade;


public class Main {
	public static void main(String[] args) {
		List<String> testesDeAceitacao = new ArrayList<String>();
		Facade facade = new Facade();
		testesDeAceitacao.add("resources/teste_aceitacao/usecase_1.txt");
		testesDeAceitacao.add("resources/teste_aceitacao/usecase_2.txt");
		testesDeAceitacao.add("resources/teste_aceitacao/usecase_3.txt");
//		testesDeAceitacao.add("resources/teste_aceitacao/usecase_4.txt");
		 
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(facade, testesDeAceitacao);
		eaFacade.executeTests();
		System.out.println(eaFacade.getCompleteResults());
	}
}
