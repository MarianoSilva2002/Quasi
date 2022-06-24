package pe.edu.upc.main.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

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

	@ManyToOne
	@JoinColumn(name="idAlumno_curso", nullable = true)
	private Alumno_cursos idAlumno_curso;
	
	public Consulta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Consulta(int idConsulta, String nombreConsulta, String descripcion, Date fechaConsulta, Date horaConsulta, Alumno_cursos idAlumno_curso) {
		this.idConsulta = idConsulta;
		NombreConsulta = nombreConsulta;
		Descripcion = descripcion;
		FechaConsulta = fechaConsulta;
		HoraConsulta = horaConsulta;
		this.idAlumno_curso = idAlumno_curso;
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

	public Alumno_cursos getIdAlumno_curso() {
		return idAlumno_curso;
	}

	public void setIdAlumno_curso(Alumno_cursos idAlumno_curso) {
		this.idAlumno_curso = idAlumno_curso;
	}
}
