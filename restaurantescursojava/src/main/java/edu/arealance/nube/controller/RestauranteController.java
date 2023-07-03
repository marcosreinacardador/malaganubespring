package edu.arealance.nube.controller;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import edu.arealance.nube.repository.entity.Restaurante;
import edu.arealance.nube.service.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;

/**
 * 
 * API WEB HTTP -> Deriva en la ejecución de un método
 * 
 * GET -> consultar todos  
 * GET -> consultar uno (por ID)
 * 
 * POST -> insertar un restaurante nuevo PUT -> modificar un restaurante que ya
 * existe 
 * DELETE -> borra un restaurante (por ID) 
 * GET -> Búsqueda -> por barrio, por especialidad, por nombre, etc..
 *
 */

//@Controller    // Devuelve una vista(html/jsp)
@RestController // Devolvemos un JSON http://localhost:8081/restaurante
@RequestMapping("/restaurante")
public class RestauranteController {
	
	@Autowired  // hace falta inyectar
	RestauranteService restauranteService;  // viene llamado desde RestauranteServiceImple 
	
	@Autowired
	Environment environment;  // de aqui voy asacar la instancia del pueerto
	
	
	Logger logger = LoggerFactory.getLogger(RestauranteController.class);

	@GetMapping("/test") // http://localhost:8081/restaurante/test
	public Restaurante obtenerRestauranteTest() { // objeto dinamico, spring ha hecho un new

		Restaurante restaurante = null;

		System.out.println("Llamando a obtenerRestauranteTest");
		
		logger.debug("estoy en ObtenerRestauranteTest");
				
		restaurante = new Restaurante(1l, "Martinete", "Carlos Haya 33", "Carranque", "www.martinete.org",  // Estado TRANSIENT
				"http://dkdkskksdk.coe", 33.65f, -2.3f, 10, "gazpachuelo", "paella", "sopa de marisco",
				LocalDateTime.now());

		return restaurante;
	}
	
	private ResponseEntity<?> generarRespuestaErroresValidacion(BindingResult bindingResult){
		
		ResponseEntity<?> responseEntity = null;
		List<ObjectError> listaErrores = null;
			
			listaErrores = bindingResult.getAllErrors();
			// vamos a imprimir los errores por el log
			listaErrores.forEach(e -> logger.error(e.toString()));
			responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaErrores);
		
		return responseEntity;
	}
	
	
	
//	GET -> consultar TODOS GET http://localhost:8081/restaurante
	@GetMapping
	public ResponseEntity<?> listarTodos() {
		
		ResponseEntity<?> responseEntity = null;   // representa el mensaje http y devuelve cualquier cosa
		Iterable<Restaurante> lista_Restaurantes = null;  // con iterable nos da la lista que llama que es servicio
		
			
//			String saludo = "Hola";
//			saludo.charAt(10);
			logger.debug("Atendido por el puerto " + environment.getProperty("local.server.port"));
			lista_Restaurantes = this.restauranteService.consultarTodos();  // dame la lista de restaurantes y me da el servicio
			responseEntity = ResponseEntity.ok(lista_Restaurantes);  // con esto estamos construyendo el objeto de vuelta que es responseEntity
			//logger.info("Si acabo de listar todos los registros.");
		return responseEntity;
	}
	
//	GET -> consultar todos los barrios (distintos) de la base de datos 
//	GET http://localhost:8081/restaurante/lBarrios
	@GetMapping("/lBarrios")
	public ResponseEntity<?> listarPorBarriosUnicos() {
		
		ResponseEntity<?> responseEntity = null;   // representa el mensaje http y devuelve cualquier cosa
//		List<String> listBarrios = null;
		Iterable<String> listBarrios = null;
			
			listBarrios = this.restauranteService.listaBarrios();  // dame la lista de restaurantes y me da el servicio
			responseEntity = ResponseEntity.ok(listBarrios);  // con esto estamos construyendo el objeto de vuelta que es responseEntity
			//logger.info("Si acabo de listar todos los registros.");
		return responseEntity;
	}
	
	
	
	
	
//	  GET -> consultar uno (por ID)  http://localhost:8081/restaurante/id
	@Operation(description =  "Este servicio permite la consulta de un restaurante por ID")   // para documentar cada método en la OPEN API
	@GetMapping("/{id}")
	public ResponseEntity<?> listarPorId(@PathVariable Long id) {
		
		ResponseEntity<?> responseEntity = null;   // representa el mensaje http y devuelve cualquier cosa
		Optional<Restaurante> or = null;
		
			logger.debug("En ListarPorID");
		
			or = this.restauranteService.consultarRestaurante(id);
		
				if(or.isPresent()) {  // si esta presente nos dvuelve un booleano
					// la consulta ha recuperado un registro
					Restaurante restauranteLeido =  or.get();
					logger.debug("Recuperado el registro" + restauranteLeido);
					responseEntity = ResponseEntity.ok().body(restauranteLeido);
				} else {
					// la consulta NO ha recuperado un registro
				responseEntity = ResponseEntity.noContent().build();  // el el 204   .build() construir un body con el cuerpo vacio
				logger.debug("El restaurante con " + id + " no existe");
			}
		logger.debug("Hemos salido ya de listarPorID" );
		return responseEntity;	
	}
	
//	  POST -> insertar un restaurante nuevo  http://localhost:8081/restaurante (Body Restaurante)
//            trae el cuerpo que es el json y lo insertamos y devuelvo el nuevo objeto ya insertado
	
	@PostMapping
	public ResponseEntity<?> insertarRestaurante(@Valid  @RequestBody Restaurante restaurante, BindingResult bindingResult){  //deserializa por recibir un texto
												
		ResponseEntity<?> responseEntity = null;   // representa el mensaje http y devuelve cualquier cosa
		Restaurante restauranteNuevo = null;

		//TODO validar
		if(bindingResult.hasErrors()) {
			logger.debug("Errores en la entrada POST");
			responseEntity = generarRespuestaErroresValidacion(bindingResult);
		} else {
			logger.debug("Sin errores en la entrada POST");
			restauranteNuevo = this.restauranteService.altaRestaurante(restaurante);
			responseEntity =  ResponseEntity.status(HttpStatus.CREATED).body(restaurante);  // 201 es porque se ha creado
		}
		return responseEntity;	
		}
	
	
//	  PUT -> modificar un restaurante que ya existe  http://localhost:8081/restaurante/id (Body Restaurante)
	@PutMapping("/{id}")
	public ResponseEntity<?> modificaRestaurante(@Valid  @RequestBody Restaurante restaurante, @PathVariable Long id, BindingResult bindingResult){  //deserializa por recibir un texto
		
		ResponseEntity<?> responseEntity = null;   // representa el mensaje http y devuelve cualquier cosa
		Optional<Restaurante> opRestaurante = null;
		
		
		//TODO validar
				if(bindingResult.hasErrors()) {
					logger.debug("Errores en la entrada PUT");
					responseEntity = generarRespuestaErroresValidacion(bindingResult);
				} else {
					logger.debug("Sin errores en la entrada PUT");
				
	
		opRestaurante = this.restauranteService.modificarRestaurante(id, restaurante);
		if(opRestaurante.isPresent()) {
			Restaurante rm = opRestaurante.get();
			responseEntity = ResponseEntity.ok(rm);  // 200 esta correcto
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 not found error del cliente
		}
				}		
		return responseEntity;	
	 
	}
	
//	  DELETE -> borra un restaurante (por ID) http://localhost:8081/restaurante/id
	@DeleteMapping("/{id}")
	public ResponseEntity<?> BorrarPorId(@PathVariable Long id) {
		
		ResponseEntity<?> responseEntity = null;   // representa el mensaje http y devuelve cualquier cosa
		
		this.restauranteService.borrarRestaurante(id);
		responseEntity = ResponseEntity.ok().build();  //devuelvo 200 si borrar es correcto
		
		
		return responseEntity;
	}
	
	
	// Obtener restaurantes en un rango de precio   http://localhost:8081/restaurante/8/25
	@GetMapping("/buscarPorPrecio/{min}/{max}")
	public ResponseEntity<?> listaPorRangoPrecios(@PathVariable int min, @PathVariable int max){
		ResponseEntity<?> responseEntity = null;   // representa el mensaje http y devuelve cualquier cosa
			Iterable<Restaurante> itRest = null;
			
			itRest = this.restauranteService.obtenerPorRangoPrecio(min, max);
			responseEntity = ResponseEntity.ok(itRest); 
		
		return responseEntity;
		
	}
	
// otra forma de hacerlo con parametros
//	@GetMapping("/buscarPorPrecio")   
//	public ResponseEntity<?> listarPorRangoPrecio (
//			@RequestParam(name = "preciomin") int preciomin,
//			@RequestParam(name = "preciomax") int preciomax)
//	{
//		ResponseEntity<?> responseEntity = null;
//		Iterable<Restaurante> lista_Restaurantes = null;
//			
//			lista_Restaurantes = this.restauranteService.buscarPorRangoPrecio(preciomin, preciomax);
//			responseEntity = ResponseEntity.ok(lista_Restaurantes);
//		
//		return responseEntity;
//	}
//	
	
	// Buscar por especialidad
	@GetMapping("/BuscarCualquierCosa/{dato}")
//	@GetMapping("/BuscarCualquierCosa")  tambien vale hacerlo con parametros
//	public ResponseEntity<?> listarNombreBarrioOEspecialidad(@RequestParam( name = dato) String dato){
	public ResponseEntity<?> listarNombreBarrioOEspecialidad(@PathVariable String dato){
		ResponseEntity<?> responseEntity = null;   // representa el mensaje http y devuelve cualquier cosa
		Iterable<Restaurante> itRest = null;
		
			itRest = this.restauranteService.listarNombreBarrioOEspecialidad(dato);
			responseEntity = ResponseEntity.ok(itRest); 
		
	return responseEntity;
		
	}
	


	
	
}
