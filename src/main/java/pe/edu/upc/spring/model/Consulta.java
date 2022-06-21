package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Consulta")
public class Consulta implements Serializable{
	
	private static final long serialVersionUID = 11;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idConsulta;
	
	@NotEmpty(message="Ingresar el nombre de la consulta")
	@Column(name="Nconsulta", nullable = false, length = 60)
	private String Nconsulta;
	
	@NotEmpty(message="La descripcion de la consulta es necesaria")
	@Column(name="Descripcion", nullable = false, length = 200)
	private String Descripcion;

	@NotEmpty(message="La respuesta de la consulta es necesaria")
	@Column(name="Respuesta", nullable = false, length = 200)
	private String Respuesta;
	
	public Consulta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Consulta(int idConsulta, String nconsulta, String respuesta, Alumno alumno, Docente docente) {
		super();
		this.idConsulta = idConsulta;
		this.Respuesta = respuesta;
		Nconsulta = nconsulta;
	}

	public int getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(int idConsulta) {
		this.idConsulta = idConsulta;
	}

	public String getNconsulta() {
		return Nconsulta;
	}

	public void setNconsulta(String nconsulta) {
		Nconsulta = nconsulta;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public String getRespuesta() {
		return Respuesta;
	}

	public void setRespuesta(String respuesta) {
		Respuesta = respuesta;
	}
	
}
