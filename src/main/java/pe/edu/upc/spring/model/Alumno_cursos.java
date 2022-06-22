package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Alumno_cursos")
public class Alumno_cursos implements Serializable{
	
	private static final long serialVersionUID = 11;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAlumno_curso;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Alumno_id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Curso_id;
	
	//@ManyToOne
	//@JoinColumn(name="Alumno", nullable = true)
	//private Alumno Alumno;
	
	public Alumno_cursos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Alumno_cursos(int idAlumno_curso, int Alumno_id, int Curso_id) {
        super();
        this.idAlumno_curso = idAlumno_curso;
        this.Alumno_id = Alumno_id;
        this.Curso_id = Curso_id;
    }

	public int getIdAlumno_curso() {
		return idAlumno_curso;
	}

	public void setIdAlumno_curso(int idAlumno_curso) {
		this.idAlumno_curso = idAlumno_curso;
	}

	public int getAlumno_id() {
		return Alumno_id;
	}

	public void setAlumno_id(int alumno_id) {
		Alumno_id = alumno_id;
	}

	public int getCurso_id() {
		return Curso_id;
	}

	public void setCurso_id(int curso_id) {
		Curso_id = curso_id;
	}
	
}
