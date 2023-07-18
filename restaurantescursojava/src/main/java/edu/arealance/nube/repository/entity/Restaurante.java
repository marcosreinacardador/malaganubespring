package edu.arealance.nube.repository.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "restaurantes", schema = "bdrestaurantes")
public class Restaurante {

	@Id  // Indica a spring que éste será la clave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //autoincremento en Mysql
	private Long id;
	
	@NotEmpty
	private String nombre; //x
	
	@NotEmpty
	private String direccion;//x
	
	@NotEmpty
	private String barrio;//x
	
	private String web;
	
	private String fichaGoogle;
	
	private Float latitud;
	
	private Float longitud;
	
	@Min(2)
	@Max(500)
	private Integer precioMedio;//x
	
	private String especialidad1;
	
	private String especialidad2;
	
	private String especialidad3;
	
	@Column(name = "creado_en")
	private LocalDateTime creadoEN;
	
	@PrePersist // este metodo, marcado así, se ejecuta antes de insertar el restaurante
	private void generarFechaCreacion () 
	{
		this.creadoEN = LocalDateTime.now();  // obtengo la fecha de hoy metodo estatico 
	}

	@Lob   // este atributo es un fichero tipo blob
	@JsonIgnore  // no queremos que este atributo vaya con el JSON de respuesta, para que no lo serialize
	private byte[] foto;
	
	public Integer getFotoHashCode() {  // genera una bandera o flag
		
		Integer idev = null;
		
			if(this.foto!= null) {
				idev = this.foto.hashCode();  //hascode es la direccion de memoria del atributo idev
			}
		
		return idev;
	}
	
	
//	Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getFichaGoogle() {
		return fichaGoogle;
	}

	public void setFichaGoogle(String fichaGoogle) {
		this.fichaGoogle = fichaGoogle;
	}

	public Float getLatitud() {
		return latitud;
	}

	public void setLatitud(Float latitud) {
		this.latitud = latitud;
	}

	public Float getLongitud() {
		return longitud;
	}

	public void setLongitud(Float longitud) {
		this.longitud = longitud;
	}

	public Integer getPrecioMedio() {
		return precioMedio;
	}

	public void setPrecioMedio(Integer precioMedio) {
		this.precioMedio = precioMedio;
	}

	public String getEspecialidad1() {
		return especialidad1;
	}

	public void setEspecialidad1(String especialidad1) {
		this.especialidad1 = especialidad1;
	}

	public String getEspecialidad2() {
		return especialidad2;
	}

	public void setEspecialidad2(String especialidad2) {
		this.especialidad2 = especialidad2;
	}

	public String getEspecialidad3() {
		return especialidad3;
	}

	public void setEspecialidad3(String especialidad3) {
		this.especialidad3 = especialidad3;
	}

	public LocalDateTime getCreadoEN() {
		return creadoEN;
	}

	public void setCreadoEN(LocalDateTime creadoEN) {
		this.creadoEN = creadoEN;
	}
	
// Se crean los constructores de parametros y vacio.	
	public Restaurante(Long id, String nombre, String direccion, String barrio, String web, String fichaGoogle,
			Float latitud, Float longitud, Integer precioMedio, String especialidad1, String especialidad2,
			String especialidad3, LocalDateTime creadoEN) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.barrio = barrio;
		this.web = web;
		this.fichaGoogle = fichaGoogle;
		this.latitud = latitud;
		this.longitud = longitud;
		this.precioMedio = precioMedio;
		this.especialidad1 = especialidad1;
		this.especialidad2 = especialidad2;
		this.especialidad3 = especialidad3;
		this.creadoEN = creadoEN;
	}
	
	
	public Restaurante() {
		// TODO Auto-generated constructor stub
	}	
		
	@Override
	public String toString() {
		return "Restaurante [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", barrio=" + barrio
				+ ", web=" + web + ", fichaGoogle=" + fichaGoogle + ", latitud=" + latitud + ", longitud=" + longitud
				+ ", precioMedio=" + precioMedio + ", especialidad1=" + especialidad1 + ", especialidad2="
				+ especialidad2 + ", especialidad3=" + especialidad3 + ", creadoEN=" + creadoEN + "]";
	}

	
	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	
	
}
