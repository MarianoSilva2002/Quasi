package pe.edu.upc.main.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Curso")
public class Curso implements Serializable{
	
	private static final long serialVersionUID = 11;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCurso;
	
	@NotEmpty(message="Ingresar el nombre del curso")
	@Column(name="Ncurso", nullable = false, length = 60)
	private String Ncurso;
	
	@NotEmpty(message="La descripcion del curso es necesaria")
	@Column(name="Descripcion", nullable = false, length = 500)
	private String Descripcion;
	
	@NotEmpty(message="Ingresar la cantidad de evaluaciones")
	@Column(name="NroEvaluaciones", nullable= false)
	private int NroEvaluaciones;

	@ManyToOne
	@JoinColumn(name="iddocente",nullable = false)
	private Docente iddocente; 
	
	public Curso() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Curso(int idCurso, @NotEmpty(message = "Ingresar el nombre del curso") String ncurso,
			@NotEmpty(message = "La descripcion del curso es necesaria") String descripcion,
			@NotEmpty(message = "Ingresar la cantidad de evaluaciones") int nroEvaluaciones, Docente iddocente) {
		super();
		this.idCurso = idCurso;
		Ncurso = ncurso;
		Descripcion = descripcion;
		NroEvaluaciones = nroEvaluaciones;
		this.iddocente = iddocente;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public String getNcurso() {
		return Ncurso;
	}

	public void setNcurso(String ncurso) {
		Ncurso = ncurso;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public int getNroEvaluaciones() {
		return NroEvaluaciones;
	}

	public void setNroEvaluaciones(int nroEvaluaciones) {
		NroEvaluaciones = nroEvaluaciones;
	}

	public Docente getIddocente() {
		return iddocente;
	}

	public void setIddocente(Docente iddocente) {
		this.iddocente = iddocente;
	}

	
}
