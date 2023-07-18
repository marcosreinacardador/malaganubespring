package edu.arealance.nube.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import edu.arealance.nube.dto.FraseChuckNorris;
import edu.arealance.nube.repository.RestauranteRepository;
import edu.arealance.nube.repository.entity.Restaurante;

//Se ha generado con add unimplemented methods lo implementa desde RestauranteService con todos sus metodos que implementa desde RestauranteService

@Service
public class RestauranteServiceImpl implements RestauranteService{
	
	@Autowired   //hace falta inyectar
	RestauranteRepository restauranteRepository;   //instancia de la base de datos

	@Override
	@Transactional(readOnly = true)   //utilizamos el de spring y no el de Tomcat, permitimos acceso concurrente a a la tabla Restaurantes
	public Iterable<Restaurante> consultarTodos() {  //Se ha generado con add unimplemented methods lo implementa desde RestauranteService
		return this.restauranteRepository.findAll();    //le pido todos los restaurantes a la base de datos y le devuelvo todos los restaurante con iterable findAll()
		//return null;
	}
	
	//todos los barrios (distintos) de la base de datos
	@Override
	@Transactional(readOnly = true)   //utilizamos el de spring y no el de Tomcat, permitimos acceso concurrente a a la tabla Restaurantes
	public Iterable<String> listaBarrios() {  //Se ha generado con add unimplemented methods lo implementa desde RestauranteService
//		List<String> lista_barrios = null;
		Iterable<String> lista_barrios = null;
		lista_barrios = this.restauranteRepository.listaBarrios();    //le pido todos los restaurantes a la base de datos y le devuelvo todos los restaurante con iterable findAll()
		return lista_barrios;
	}
	

	@Override
	@Transactional(readOnly = true)   //utilizamos el de spring y no el de Tomcat, permitimos acceso concurrente a a la tabla Restaurantes
	public Optional<Restaurante> consultarRestaurante(Long id) {
		return restauranteRepository.findById(id);
		//turn Optional.empty();
	}

	@Override
	@Transactional   //utilizamos el de spring y no el de Tomcat
	public Restaurante altaRestaurante(Restaurante restaurante) {
		return this.restauranteRepository.save(restaurante);
		//return null;
	}

	@Override
	@Transactional   //utilizamos el de spring y no el de Tomcat
	public void borrarRestaurante(Long id) {
		this.restauranteRepository.deleteById(id);
		
	}

	@Override
	@Transactional   //utilizamos el de spring y no el de Tomcat
	public Optional<Restaurante> modificarRestaurante(Long id, Restaurante restaurante) {
		Optional<Restaurante> opRest = 	Optional.empty();
		// 1 LEER
		opRest =  this.restauranteRepository.findById(id);
			if(opRest.isPresent()) {  // lo has encontrado si?
				// Al estar dentro de una transaccion, restauranteLeido está asociado a un resgistro de la tabla.
				// Si modifico un campo, estoy modificando la columna asociada (Estado "Persistent" - JPA)
 				Restaurante restauranteLeido  = opRest.get();
				//restauranteLeido.setNombre(restaurante.getNombre()); en vez de hacerlo uno a uno por campo lo hacemos con BeanUtils.copyProperties
 				BeanUtils.copyProperties(restaurante, restauranteLeido, "id", "creadoEn");   // copiame todos los atributos de restauranteLeido a restaurate, menos id y creadoEn
 				opRest = Optional.of(restauranteLeido); //Rellenamos el Optional
			}
		// 2 ACTUALIZAR
		return opRest;
	}

	@Override
	@Transactional(readOnly = true) 
	public Iterable<Restaurante> obtenerPorRangoPrecio(int preciomin, int preciomax){
		Iterable<Restaurante> listaR = null;
			listaR = this.restauranteRepository.findByPrecioMedioBetween(preciomin, preciomax);
		return listaR; 
	}
	
	// paginado
	@Override
	@Transactional(readOnly = true)
	public Iterable<Restaurante> obtenerPorRangoPrecio(int preciomin, int preciomax, Pageable pageable) {
		Iterable<Restaurante> listaR = null;
		listaR = this.restauranteRepository.findByPrecioMedioBetween(preciomin, preciomax, pageable);
		return listaR;
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Restaurante> listarNombreBarrioOEspecialidad(String clave) {
		Iterable<Restaurante> listaR = null;
		listaR = this.restauranteRepository.buscarPorBarrioNombreOEspecialidad(clave);
		return listaR;
	}

	
//	@Override
//	@Transactional(readOnly = true) 
//	public Iterable<Restaurante> listarPorEspecialidad(String esp1, String esp2, String esp3) {
//		Iterable<Restaurante> listaR = null;
//			listaR = this.restauranteRepository.findByEspecialidad1OrEspecialidad2OrEspecialidad3AllIgnoreCase(esp1, esp2, esp3);
//		return null;
//	}
	
	@Override
	public Optional<FraseChuckNorris> obtenerFraseAleatorioChuckNorris() {
		Optional<FraseChuckNorris> opChuck = Optional.empty();
		FraseChuckNorris fraseChuckNorris = null;
		RestTemplate restTemplate = null;
		
			restTemplate = new RestTemplate();
		    fraseChuckNorris = restTemplate.getForObject("https://api.chucknorris.io/jokes/random", FraseChuckNorris.class);
		    opChuck = Optional.of(fraseChuckNorris);
		
		return opChuck;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Restaurante> consultarPorPaginas(Pageable pageable){
		
		return this.restauranteRepository.findAll(pageable);
		
	}
	
	
}
