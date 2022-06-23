package pe.edu.upc.main.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Consulta")
public class Consulta implements Serializable{
	
	private static final long serialVersionUID = 11;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idConsulta;
	
	@Column(name="NombreConsulta", nullable = true, length = 50)
	private String NombreConsulta;
	
	@Column(name="Descripcion", nullable = true, length = 500)
	private String Descripcion;

	@Column(name="FechaConsulta", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date FechaConsulta;
	
	@Column(name="HoraConsulta", nullable = true)
	@Temporal(TemporalType.TIME)
	private Date HoraConsulta;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Alumno_Curso_id;
	
	//@ManyToOne
	//@JoinColumn(name="idAlumno_curso", nullable = true)
	//private idAlumno_curso idAlumno_curso;
	
	public Consulta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Consulta(int idConsulta, String nombreConsulta, String descripcion, Date fechaConsulta, Date horaConsulta,
			int alumno_Curso_id) {
		super();
		this.idConsulta = idConsulta;
		NombreConsulta = nombreConsulta;
		Descripcion = descripcion;
		FechaConsulta = fechaConsulta;
		HoraConsulta = horaConsulta;
		Alumno_Curso_id = alumno_Curso_id;
	}

	public int getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(int idConsulta) {
		this.idConsulta = idConsulta;
	}

	public String getNombreConsulta() {
		return NombreConsulta;
	}

	public void setNombreConsulta(String nombreConsulta) {
		NombreConsulta = nombreConsulta;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public Date getFechaConsulta() {
		return FechaConsulta;
	}

	public void setFechaConsulta(Date fechaConsulta) {
		FechaConsulta = fechaConsulta;
	}

	public Date getHoraConsulta() {
		return HoraConsulta;
	}

	public void setHoraConsulta(Date horaConsulta) {
		HoraConsulta = horaConsulta;
	}

	public int getAlumno_Curso_id() {
		return Alumno_Curso_id;
	}

	public void setAlumno_Curso_id(int alumno_Curso_id) {
		Alumno_Curso_id = alumno_Curso_id;
	}
	
}
