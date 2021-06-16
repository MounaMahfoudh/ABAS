package com.abas.spiel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mmahfoud
 *
 */
@SpringBootApplication(scanBasePackages = "com.abas.spiel")
public class Spiel {

	private static final Logger LOG = LoggerFactory.getLogger(Spiel.class);

	public static void main(String[] args) {
		LOG.info("STARTING Schere Stein Papier for ABAS");
		SpringApplication.run(Spiel.class, args);
		LOG.info("Schere Stein Papier EXITED");
	}



}