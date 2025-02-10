package stusyo222b.webappsspringproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import stusyo222b.webappsspringproject.entities.OfficeWorker;
import stusyo222b.webappsspringproject.service.OfficeWorkerService;

import java.util.List;

@SpringBootTest
class WebAppsSpringProjectApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	OfficeWorkerService officeWorkerService;

	@Test
	void getOfficeWorker(){
		List<OfficeWorker> myList = officeWorkerService.getAllOfficeWorkers();
		myList.stream().forEach(System.out::println);
	}

}
