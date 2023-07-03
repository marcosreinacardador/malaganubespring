package edu.arealance.nube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekamarcosApplication {
	
/*   1 creo poryecto con Spring Starter Project y add dependencia de EurekaServer
 *   2 Add dependencia de glasfish JAXB
 *   3 Anotación en el main @EnableEurekaServer
 *   4 Configuro las properties
 * 
 */

	public static void main(String[] args) {
		SpringApplication.run(EurekamarcosApplication.class, args);
	}

}
