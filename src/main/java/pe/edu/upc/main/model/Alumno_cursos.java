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
	private Alumno Alumno;

	@ManyToOne
	@JoinColumn(name="Seccion", nullable = true)
	private Seccion Seccion;

	public Alumno_cursos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Alumno_cursos(int idAlumno_curso, Alumno alumno, Seccion seccion) {
		this.idAlumno_curso = idAlumno_curso;
		Alumno = alumno;
		Seccion = seccion;
	}

	public int getIdAlumno_curso() {
		return idAlumno_curso;
	}

	public void setIdAlumno_curso(int idAlumno_curso) {
		this.idAlumno_curso = idAlumno_curso;
	}

	public pe.edu.upc.main.model.Alumno getAlumno() {
		return Alumno;
	}

	public void setAlumno(pe.edu.upc.main.model.Alumno alumno) {
		Alumno = alumno;
	}

	public pe.edu.upc.main.model.Seccion getSeccion() {
		return Seccion;
	}

	public void setSeccion(pe.edu.upc.main.model.Seccion seccion) {
		Seccion = seccion;
	}
}
