package pe.edu.upc.main.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Anotacion")
public class Anotacion implements Serializable{
	
	private static final long serialVersionUID = 11;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAnotacion;
	
	@Column(name="Descripcion", nullable = true, length = 500)
	private String Descripcion;
	
	@Column(name="FechaAnotacion", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date FechaAnotacion;
	
	@Column(name="HoraAnotacion", nullable = true)
	@Temporal(TemporalType.TIME)
	private Date HoraAnotacion;

	@ManyToOne
	@JoinColumn(name="idAlumno_curso", nullable = true)
	private Alumno_cursos idAlumno_curso;
	
	public Anotacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Anotacion(int idAnotacion, String descripcion, Date fechaAnotacion,
			Date horaAnotacion, Alumno_cursos idAlumno_curso) {
		super();
		this.idAnotacion = idAnotacion;
		Descripcion = descripcion;
		FechaAnotacion = fechaAnotacion;
		HoraAnotacion = horaAnotacion;
		this.idAlumno_curso = idAlumno_curso;
	}

	public int getIdAnotacion() {
		return idAnotacion;
	}

	public void setIdAnotacion(int idAnotacion) {
		this.idAnotacion = idAnotacion;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public Date getFechaAnotacion() {
		return FechaAnotacion;
	}

	public void setFechaAnotacion(Date fechaAnotacion) {
		FechaAnotacion = fechaAnotacion;
	}

	public Date getHoraAnotacion() {
		return HoraAnotacion;
	}

	public void setHoraAnotacion(Date horaAnotacion) {
		HoraAnotacion = horaAnotacion;
	}

	public Alumno_cursos getIdAlumno_curso() {
		return idAlumno_curso;
	}

	public void setIdAlumno_curso(Alumno_cursos idAlumno_curso) {
		this.idAlumno_curso = idAlumno_curso;
	}
}
