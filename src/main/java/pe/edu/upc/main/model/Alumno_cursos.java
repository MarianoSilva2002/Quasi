package pe.edu.upc.main.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Table(name="Alumno_cursos")
public class Alumno_cursos implements Serializable{
	
	private static final long serialVersionUID = 11;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAlumno_curso;
	
	@ManyToOne
	@JoinColumn(name="Alumno", nullable = true)
	private Alumno alumno;

	@ManyToOne
	@JoinColumn(name="Seccion", nullable = true)
	private Seccion seccion;

	public Alumno_cursos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Alumno_cursos(int idAlumno_curso, Alumno alumno, Seccion seccion) {
		this.idAlumno_curso = idAlumno_curso;
		this.alumno = alumno;
		this.seccion = seccion;
	}

	public int getIdAlumno_curso() {
		return idAlumno_curso;
	}

	public void setIdAlumno_curso(int idAlumno_curso) {
		this.idAlumno_curso = idAlumno_curso;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}
}
