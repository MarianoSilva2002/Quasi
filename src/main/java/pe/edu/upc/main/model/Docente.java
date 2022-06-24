package pe.edu.upc.main.model;

import java.io.Serializable;

import javax.persistence.*;
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
	
	@ManyToOne
	@JoinColumn(name="pregunta", nullable = false)
	private Preguntas_Seguridad pregunta; 
	
	@NotEmpty(message="Su respuesta de seguridad es necesaria")
	@Column(name="respuestaseguridad", nullable = false, length = 100)
	private String respuestaseguridad;


	public Docente() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Docente(int idDocente, @NotEmpty(message = "Su nombre es necesario") String nombre,
			@NotEmpty(message = "Su apellido P. es necesario") String aPaterno,
			@NotEmpty(message = "Su apellido M. es necesario") String aMaterno,
			@NotEmpty(message = "Su genero es necesario") String genero,
			@NotEmpty(message = "Su correo es necesario") @Email(message = "La direcccion de correo no es valida") String correo,
			@NotEmpty(message = "La contraseña es necesaria") String contrasena,
			@NotEmpty(message = "Su especialidad es necesaria") String especialidad, Preguntas_Seguridad pregunta,
			@NotEmpty(message = "Su respuesta de seguridad es necesaria") String respuestaseguridad) {
		super();
		this.idDocente = idDocente;
		this.nombre = nombre;
		this.aPaterno = aPaterno;
		this.aMaterno = aMaterno;
		this.genero = genero;
		this.correo = correo;
		this.contrasena = contrasena;
		this.especialidad = especialidad;
		this.pregunta = pregunta;
		this.respuestaseguridad = respuestaseguridad;
	}

	public Docente getIdDocente() {
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
	public Preguntas_Seguridad getPregunta() {
		return pregunta;
	}
	public void setPregunta(Preguntas_Seguridad pregunta) {
		this.pregunta = pregunta;
	}
	public String getRespuestaseguridad() {
		return respuestaseguridad;
	}
	public void setRespuestaseguridad(String respuestaseguridad) {
		this.respuestaseguridad = respuestaseguridad;
	}

}
