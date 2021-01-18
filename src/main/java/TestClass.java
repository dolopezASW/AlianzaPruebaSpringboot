import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.alianza.prueba.dto.ClientDTO;
import com.alianza.prueba.service.IClientService;

public class TestClass {
	
	@Autowired
	private IClientService clientService;

	public static void main(String[] args) {
		TestClass testClass = new TestClass();
		ClientDTO client = new ClientDTO("","","",LocalDate.now(),LocalDate.now().plusYears(2));
		if(testClass.clientService.createClient(client).getBody()!=null) {
			System.out.println("Create PASSED");
		}
		if(testClass.clientService.searchClients("").getBody()!=null) {
			System.out.println("Search by key PASSED");
		}

	}

}
