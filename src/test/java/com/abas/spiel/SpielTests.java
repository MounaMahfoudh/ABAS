package com.abas.spiel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpielTests {

	@Autowired
	MyCommandLineRunner commandLineRunner;

	@Test
	public void testMain() {
		commandLineRunner.run();		
	}	

}
