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
	private String nCurso;
	
	@NotEmpty(message="La descripcion del curso es necesaria")
	@Column(name="Descripcion", nullable = false, length = 500)
	private String descripcion;

	@Column(name="NroEvaluaciones", nullable= false)
	private int nroEvaluaciones;

	@ManyToOne
	@JoinColumn(name="iddocente",nullable = false)
	private Docente iddocente; 
	
	public Curso() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Curso(int idCurso, @NotEmpty(message = "Ingresar el nombre del curso") String ncurso,
			@NotEmpty(message = "La descripcion del curso es necesaria") String descripcion,
			int nroEvaluaciones, Docente iddocente) {
		super();
		this.idCurso = idCurso;
		nCurso = ncurso;
		this.descripcion = descripcion;
		this.nroEvaluaciones = nroEvaluaciones;
		this.iddocente = iddocente;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public String getnCurso() {
		return nCurso;
	}

	public void setnCurso(String nCurso) {
		this.nCurso = nCurso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getNroEvaluaciones() {
		return nroEvaluaciones;
	}

	public void setNroEvaluaciones(int nroEvaluaciones) {
		this.nroEvaluaciones = nroEvaluaciones;
	}

	public Docente getIddocente() {
		return iddocente;
	}

	public void setIddocente(Docente iddocente) {
		this.iddocente = iddocente;
	}

	
}
