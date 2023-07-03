package edu.arealance.nube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient    // Activamos el cliente Eureka
public class RestaurantescursojavaApplication {
	
	/**
	 * PARA CONFIGURAR EL CLIENTE EUREKA
	 * 
	 * 1 Add Starters, eureka client
	 * 2 Add anotación @EnableEurekaClient
	 * 3 Configurar las properties relativas al eureka
	 * @param args
	 */

	public static void main(String[] args) {
		SpringApplication.run(RestaurantescursojavaApplication.class, args);
	}

}
