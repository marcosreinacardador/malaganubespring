package edu.arealance.nube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewaymarcosApplication {

	/**
	 * para hacer el gateway
	 * 
	 * 1 Nuevo proyecto con Spring Starter Proyect con las depencias
	 * Gateway y y eureka client
	 * 2. Anotación con @EnableEurekaClient en el main
	 * 3 Properties  / yml configuracion / enrutamiento
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		SpringApplication.run(GatewaymarcosApplication.class, args);
	}

}
