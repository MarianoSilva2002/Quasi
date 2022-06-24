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
	
	@NotEmpty(message="La cantidad de semanas de la seccion es necesaria")
	@Column(name="Cantsemanas", nullable = false)
	private int Cantsemanas;
	
	@NotEmpty(message="La cantidad de clases semanales de la seccion es necesaria")
	@Column(name="CantClasessemanales", nullable = false)
	private int CantClasessemanales;
	
	@NotEmpty(message="La hora de inicio de la seccion es necesaria")
	@Column(name="HoraInicio", nullable = false)
	@Temporal(TemporalType.TIME)
	private Date HoraInicio;
	
	@NotEmpty(message="La hora final de la seccion es necesaria")
	@Column(name="HoraFin", nullable = false)
	@Temporal(TemporalType.TIME)
	private Date HoraFin;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idcurso")
	private Curso idcurso; 

	public Seccion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Seccion(int idSeccion, @NotEmpty(message = "El nombre de la seccion es necesario") String nombre,
			@NotEmpty(message = "La cantidad de semanas de la seccion es necesaria") int cantsemanas,
			@NotEmpty(message = "La cantidad de clases semanales de la seccion es necesaria") int cantClasessemanales,
			@NotEmpty(message = "La hora de inicio de la seccion es necesaria") Date horaInicio,
			@NotEmpty(message = "La hora final de la seccion es necesaria") Date horaFin, Curso idcurso) {
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

	public Date getHoraInicio() {
		return HoraInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		HoraInicio = horaInicio;
	}

	public Date getHoraFin() {
		return HoraFin;
	}

	public void setHoraFin(Date horaFin) {
		HoraFin = horaFin;
	}

	public Curso getIdcurso() {
		return idcurso;
	}

	public void setIdcurso(Curso idcurso) {
		this.idcurso = idcurso;
	}
	
}
