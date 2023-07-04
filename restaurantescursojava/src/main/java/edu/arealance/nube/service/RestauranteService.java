package edu.arealance.nube.service;

import java.util.List;
import java.util.Optional;

import edu.arealance.nube.dto.FraseChuckNorris;
import edu.arealance.nube.repository.entity.Restaurante;

public interface RestauranteService {

	// no recibo nada, que devuelvo un iterable de restaurante, dará error en
	// RestauranteServiceImpl, habrá que add este iterable
	// Iterable lo que hace es recorrer
	Iterable<Restaurante> consultarTodos();

	// el tipo optional hace que nunca devuelva nulo
	Optional<Restaurante> consultarRestaurante(Long id);

	// metemos restaurante y nos devuelve restaurante ademas con
	// el id y la fecha
	Restaurante altaRestaurante(Restaurante restaurante);

	// devuelve void si existe lo borra y sino no existe
	void borrarRestaurante(Long id);

	// cogemos el id y los datos del
	// restaurante y nos devuelve si
	// existe o no con optional al igual
	// que la consulta.
	Optional<Restaurante> modificarRestaurante(Long id, Restaurante restaurante);
	
	// Cogemos le precimedio minimo y preciomedio maximo  del restaurante y nos
	// devuelve los restaurantes con el precio min y precio max
	Iterable<Restaurante> obtenerPorRangoPrecio(int min, int max);   //aqui cambiamos el nombre del servicio que se ha copiado del repositorio.
	
	Iterable<Restaurante> listarNombreBarrioOEspecialidad(String clave);
	
	//	todos los barrios (distintos) de la base de datos
//	List<String> listaBarrios();
	Iterable<String> listaBarrios();
	
	//Obtener frase aleatoria
	Optional<FraseChuckNorris> obtenerFraseAleatorioChuckNorris();
}
