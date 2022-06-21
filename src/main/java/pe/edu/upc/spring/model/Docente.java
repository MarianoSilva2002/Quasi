package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Docente")
public class Docente implements Serializable{
	
	private static final long serialVersionUID = 11;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDocente;
	
	@NotEmpty(message="Su nombre es necesario")
	@Column(name="nombre", nullable = false, length = 60)
	private String nombre;
	
	@NotEmpty(message="Su apellido P. es necesario")
	@Column(name="apellidoPaterno", nullable = false, length = 60)
	private String aPaterno;
	
	@NotEmpty(message="Su apellido M. es necesario")
	@Column(name="apellidoMaterno", nullable = false, length = 60)
	private String aMaterno;
	
	@NotEmpty(message="Su genero es necesario")
	@Column(name="genero", nullable = false, length = 50)
	private String genero;
	
	@NotEmpty (message="Su correo es necesario")
	@Email(message="La direcccion de correo no es valida")
	@Column(name="correo", nullable = false, length = 60)
	private String correo;
	
	@NotEmpty(message="La contraseña es necesaria")
	@Column(name="contrasena", nullable = false, length = 60)
	private String contrasena;
	
	@NotEmpty(message="Su especialidad es necesaria")
	@Column(name="especialidad", nullable = false, length = 60)
	private String especialidad;
	
	public Docente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Docente(int idDocente, String nombre, String aPaterno, String aMaterno, String genero, String correo,
			String contrasena,String especialidad) {
		super();
		this.idDocente = idDocente;
		this.nombre = nombre;
		this.aPaterno = aPaterno;
		this.aMaterno = aMaterno;
		this.genero = genero;
		this.correo = correo;
		this.contrasena = contrasena;
		this.especialidad = especialidad;
	}

	public int getIdDocente() {
		return idDocente;
	}

	public void setIdDocente(int idDocente) {
		this.idDocente = idDocente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getaPaterno() {
		return aPaterno;
	}

	public void setaPaterno(String aPaterno) {
		this.aPaterno = aPaterno;
	}

	public String getaMaterno() {
		return aMaterno;
	}

	public void setaMaterno(String aMaterno) {
		this.aMaterno = aMaterno;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
}
