package com.abas.spiel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

@SpringBootTest
class SpielTests {
	
    @BeforeTestClass
    public static void setupHeadlessMode() {
        System.setProperty("java.awt.headless", "false");
    }

    @Test
    public void someTest() { }

	@Test
	void contextLoads() {
	}

}
