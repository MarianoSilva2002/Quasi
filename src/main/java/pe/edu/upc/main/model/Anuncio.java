package pe.edu.upc.main.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Anuncio")
public class Anuncio implements Serializable{
	
	private static final long serialVersionUID = 11;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAnuncio;
	
	@NotEmpty(message="La descripcion del anuncio es necesario")
	@Column(name="Descripcion", nullable = false, length = 200)
	private String Descripcion;
	
	@Column(name="Fechaanuncio", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date Fechaanuncio;
	
	@Column(name="Horaanuncio", nullable = false)
	@Temporal(TemporalType.TIME)
	private Date Horaanuncio;
	
	@ManyToOne
	@JoinColumn(name="idcurso")
	private Seccion idseccion;

	public Anuncio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Anuncio(int idAnuncio,
			@NotEmpty(message = "La descripcion del anuncio es necesario") String descripcion, Date fechaanuncio,
			 Date horaanuncio, Seccion idseccion) {
		super();
		this.idAnuncio = idAnuncio;
		Descripcion = descripcion;
		Fechaanuncio = fechaanuncio;
		Horaanuncio = horaanuncio;
		this.idseccion = idseccion;
	}

	public int getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(int idAnuncio) {
		this.idAnuncio = idAnuncio;
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

	public Seccion getIdseccion() {
		return idseccion;
	}

	public void setIdseccion(Seccion idseccion) {
		this.idseccion = idseccion;
	}
}
