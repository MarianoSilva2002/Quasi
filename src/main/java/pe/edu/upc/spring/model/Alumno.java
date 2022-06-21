package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
	@Column(name="nombre", nullable = false, length = 60)
	private String nombre;
	
	@NotEmpty(message="Su apellido P. es necesario")
	@Column(name="apellidoPaterno", nullable = false, length = 60)
	private String aPaterno;
	
	@NotEmpty(message="Su apellido M. es necesario")
	@Column(name="apellidoMaterno", nullable = false, length = 60)
	private String aMaterno;
	
	@NotEmpty(message="La contraseña es necesaria")
	@Column(name="contrasena", nullable = false, length = 60)
	private String contrasena;
	
	@NotEmpty (message="Su correo es necesario")
	@Email(message="La direcccion de correo no es valida")
	@Column(name="correo", nullable = false, length = 60)
	private String correo;
	
	@NotEmpty(message="Su genero es necesario")
	@Column(name="genero", nullable = false, length = 50)
	private String genero;
	
	@NotEmpty(message="Porfavor ingrese la carrera a la que pertenece")
	@Column(name="carrera", nullable = false, length = 50)
	private String carrera;
	
	@ManyToOne
	@JoinColumn(name="idDocente", nullable = false)
	private Docente docente;
	
	@ManyToOne
	@JoinColumn(name="idSeccion", nullable = false)
	private Seccion seccion;
	
	// falta agregar pregunta y respuesta

	public Alumno() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Alumno(int idAlumno, String nombre, String aPaterno, String aMaterno, String genero, String correo,
            String contrasena, String carrera, Docente docente, Seccion seccion) {
        super();
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.genero = genero;
        this.correo = correo;
        this.contrasena = contrasena;
        this.carrera = carrera;
        this.docente = docente;
        this.seccion = seccion;
    }

	public int getIdAlumno() {
		return idAlumno;
	}

	public void setIdEmpleado(int idAlumno) {
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

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}
	
	
}
