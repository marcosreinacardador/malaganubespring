package edu.arealance.nube.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Esta clase está escuchando todas las excepciones de este paquete
@RestControllerAdvice(basePackages = {"edu.arealance.nube"})
public class GestionExcepciones {

	Logger logger = LoggerFactory.getLogger(GestionExcepciones.class);
	
	// para cada tipo de excepción defino  / un método
	@ExceptionHandler(StringIndexOutOfBoundsException.class)   //especificamos el tipo de exception, siempre .class
	public ResponseEntity<?> gestionStringOutIndexException (StringIndexOutOfBoundsException e) {
		ResponseEntity<?> responseEntity = null;
		
			responseEntity = ResponseEntity.internalServerError().body(e.getMessage());
			logger.error(e.getMessage(), e);
		return responseEntity;
	}
	
	@ExceptionHandler(Throwable.class)   //especificamos el tipo de exception, siempre .class
	public ResponseEntity<?> gestionExceptionGenerica (Throwable e) {
		ResponseEntity<?> responseEntity = null;
		
			responseEntity = ResponseEntity.internalServerError().body(e.getMessage());
			logger.error(e.getMessage(), e);
		
		return responseEntity;
	}
	
	
}
