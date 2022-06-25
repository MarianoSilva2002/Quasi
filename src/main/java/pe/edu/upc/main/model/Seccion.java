package pe.edu.upc.main.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Seccion")
public class Seccion implements Serializable{
	
	private static final long serialVersionUID = 11;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSeccion;
	
	@NotEmpty(message="El nombre de la seccion es necesario")
	@Column(name="Nombre", nullable = false, length = 60)
	private String Nombre;
	
	@Column(name="Cantsemanas", nullable = false)
	private int Cantsemanas;
	
	@Column(name="CantClasessemanales", nullable = false)
	private int CantClasessemanales;
	
	@NotEmpty(message="La hora de inicio de la seccion es necesaria")
	@Column(name="HoraInicio", nullable = false)
	private String HoraInicio;
	
	@NotEmpty(message="La hora final de la seccion es necesaria")
	@Column(name="HoraFin", nullable = false)
	private String HoraFin;
	
	@ManyToOne
	@JoinColumn(name="idcurso",nullable = false)
	private Curso idcurso; 

	public Seccion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Seccion(int idSeccion, @NotEmpty(message = "El nombre de la seccion es necesario") String nombre,
			int cantsemanas,
			int cantClasessemanales,
			@NotEmpty(message = "La hora de inicio de la seccion es necesaria") String horaInicio,
			@NotEmpty(message = "La hora final de la seccion es necesaria") String horaFin, Curso idcurso) {
		super();
		this.idSeccion = idSeccion;
		Nombre = nombre;
		Cantsemanas = cantsemanas;
		CantClasessemanales = cantClasessemanales;
		HoraInicio = horaInicio;
		HoraFin = horaFin;
		this.idcurso = idcurso;
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

	public int getCantsemanas() {
		return Cantsemanas;
	}

	public void setCantsemanas(int cantsemanas) {
		Cantsemanas = cantsemanas;
	}

	public int getCantClasessemanales() {
		return CantClasessemanales;
	}

	public void setCantClasessemanales(int cantClasessemanales) {
		CantClasessemanales = cantClasessemanales;
	}

	public String getHoraInicio() {
		return HoraInicio;
	}

	public void setHoraInicio(String horaInicio) {
		HoraInicio = horaInicio;
	}

	public String getHoraFin() {
		return HoraFin;
	}

	public void setHoraFin(String horaFin) {
		HoraFin = horaFin;
	}

	public Curso getIdcurso() {
		return idcurso;
	}

	public void setIdcurso(Curso idcurso) {
		this.idcurso = idcurso;
	}
	
}
