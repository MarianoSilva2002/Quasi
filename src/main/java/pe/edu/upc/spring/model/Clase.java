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
@Table(name="Clase")
public class Clase implements Serializable{
	
	private static final long serialVersionUID = 11;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idClase;
	
	@NotEmpty(message="Ingresar el numero de clase")
	@Column(name="Numclase", nullable = false)
	private int Numclase;
	
	@NotEmpty(message="La descripcion de la clase es necesaria")
	@Column(name="Descripcion", nullable = false, length = 80)
	private String Descripcion;

	@NotEmpty(message="Ingresar si habra evaluaciones en la clase es necesario")
	@Column(name="Evaluacion", nullable = false)
	private boolean Evaluacion;

	public Clase() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Clase(int idClase, @NotEmpty(message = "Ingresar el numero de clase") int numclase,
			@NotEmpty(message = "La descripcion de la clase es necesaria") String descripcion,
			@NotEmpty(message = "Ingresar si habra evaluaciones en la clase es necesario") boolean evaluacion) {
		super();
		this.idClase = idClase;
		Numclase = numclase;
		Descripcion = descripcion;
		Evaluacion = evaluacion;
	}

	public int getIdClase() {
		return idClase;
	}

	public void setIdClase(int idClase) {
		this.idClase = idClase;
	}

	public int getNumclase() {
		return Numclase;
	}

	public void setNumclase(int numclase) {
		Numclase = numclase;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public boolean isEvaluacion() {
		return Evaluacion;
	}

	public void setEvaluacion(boolean evaluacion) {
		Evaluacion = evaluacion;
	}
	
	
	
}
