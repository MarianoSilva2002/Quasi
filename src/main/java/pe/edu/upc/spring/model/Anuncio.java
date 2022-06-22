package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Anuncio")
public class Anuncio implements Serializable{
	
	private static final long serialVersionUID = 11;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAnuncio;
	
	@NotEmpty(message="El nombre del anuncio es necesario")
	@Column(name="Nombre", nullable = false, length = 60)
	private String Nombre;
	
	@NotEmpty(message="La descripcion del anuncio es necesario")
	@Column(name="Descripcion", nullable = false, length = 200)
	private String Descripcion;
	
	@NotEmpty(message="La fecha del anuncio es necesaria")
	@Column(name="Fechaanuncio", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date Fechaanuncio;
	
	@NotEmpty(message="La hora del anuncio es necesaria")
	@Column(name="Horaanuncio", nullable = false)
	@Temporal(TemporalType.TIME)
	private Date Horaanuncio;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idcurso")
	private Curso idcurso;

	public Anuncio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Anuncio(int idAnuncio, @NotEmpty(message = "El nombre del anuncio es necesario") String nombre,
			@NotEmpty(message = "La descripcion del anuncio es necesario") String descripcion,
			@NotEmpty(message = "La fecha del anuncio es necesaria") Date fechaanuncio,
			@NotEmpty(message = "La hora del anuncio es necesaria") Date horaanuncio, Curso idcurso) {
		super();
		this.idAnuncio = idAnuncio;
		Nombre = nombre;
		Descripcion = descripcion;
		Fechaanuncio = fechaanuncio;
		Horaanuncio = horaanuncio;
		this.idcurso = idcurso;
	}

	public int getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(int idAnuncio) {
		this.idAnuncio = idAnuncio;
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

	public Date getFechaanuncio() {
		return Fechaanuncio;
	}

	public void setFechaanuncio(Date fechaanuncio) {
		Fechaanuncio = fechaanuncio;
	}

	public Date getHoraanuncio() {
		return Horaanuncio;
	}

	public void setHoraanuncio(Date horaanuncio) {
		Horaanuncio = horaanuncio;
	}

	public Curso getIdcurso() {
		return idcurso;
	}

	public void setIdcurso(Curso idcurso) {
		this.idcurso = idcurso;
	}

	
}
