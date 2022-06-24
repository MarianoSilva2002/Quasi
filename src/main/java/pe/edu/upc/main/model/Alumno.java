package pe.edu.upc.main.model;

import java.io.Serializable;

import javax.persistence.*;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Alumno")
public class Alumno implements Serializable{
	
	private static final long serialVersionUID = 11;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAlumno;
	
	@NotEmpty(message="Su nombre es necesario")
	@Column(name="nombre", nullable = false, length = 50)
	private String nombre;
	
	@NotEmpty(message="Su apellido P. es necesario")
	@Column(name="apellidoPaterno", nullable = false, length = 50)
	private String aPaterno;
	
	@NotEmpty(message="Su apellido M. es necesario")
	@Column(name="apellidoMaterno", nullable = false, length = 50)
	private String aMaterno;
	
	@NotEmpty(message="La contraseña es necesaria")
	@Column(name="contrasena", nullable = false, length = 50)
	private String contrasena;
	
	@NotEmpty (message="Su correo es necesario")
	@Email(message="La direcccion de correo no es valida")
	@Column(name="correo", nullable = false, length = 50)
	private String correo;
	
	@NotEmpty(message="Su genero es necesario")
	@Column(name="genero", nullable = false, length = 50)
	private String genero;
	
	@NotEmpty(message="Porfavor ingrese la carrera a la que pertenece")
	@Column(name="carrera", nullable = false, length = 50)
	private String carrera;

	
	@NotEmpty(message="Porfavor ingrese la respuesta correspondiente")
	@Column(name="RespuestaSeguridad", nullable = false, length = 100)
	private String Respuesta_Seguridad;
	
	@ManyToOne
	@JoinColumn(name="Preguntas_Seguridad", nullable = false)
	private Preguntas_Seguridad idPregunta;
	
	
	public Alumno() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Alumno(int idAlumno, String nombre, String aPaterno, String aMaterno, String contrasena, String correo, String genero, String carrera, String respuesta_Seguridad, Preguntas_Seguridad idPregunta) {
		this.idAlumno = idAlumno;
		this.nombre = nombre;
		this.aPaterno = aPaterno;
		this.aMaterno = aMaterno;
		this.contrasena = contrasena;
		this.correo = correo;
		this.genero = genero;
		this.carrera = carrera;
		Respuesta_Seguridad = respuesta_Seguridad;
		this.idPregunta = idPregunta;
	}

	public int getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
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

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}


	public String getRespuesta_Seguridad() {
		return Respuesta_Seguridad;
	}

	public void setRespuesta_Seguridad(String respuesta_Seguridad) {
		Respuesta_Seguridad = respuesta_Seguridad;
	}

	public Preguntas_Seguridad getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Preguntas_Seguridad idPregunta) {
		this.idPregunta = idPregunta;
	}
}
