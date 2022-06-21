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
@Table(name="Seccion")
public class Seccion implements Serializable{
	
	private static final long serialVersionUID = 11;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSeccion;
	
	@NotEmpty(message="El nombre de la seccion es necesaria")
	@Column(name="Nombre", nullable = false, length = 60)
	private String Nombre;
	
	@NotEmpty(message="La descripcion de la seccion es necesaria")
	@Column(name="Descripcion", nullable = false, length = 200)
	private String Descripcion;

	public Seccion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Seccion(int idSeccion, String nombre, Docente docente) {
		super();
		this.idSeccion = idSeccion;
		Nombre = nombre;
	}

	public int getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(int idSeccion) {
		this.idSeccion = idSeccion;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	
}
