package pe.edu.upc.main.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Anotacion")
public class Anotacion implements Serializable{
	
	private static final long serialVersionUID = 11;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAnotacion;
	
	@Column(name="NombreAnotacion", nullable = true, length = 50)
	private String NombreAnotacion;
	
	@Column(name="Descripcion", nullable = true, length = 500)
	private String Descripcion;
	
	@Column(name="FechaAnotacion", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date FechaAnotacion;
	
	@Column(name="HoraAnotacion", nullable = true)
	@Temporal(TemporalType.TIME)
	private Date HoraAnotacion;
	
	public Anotacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Anotacion(int idAnotacion, String nombreAnotacion, String descripcion, Date fechaAnotacion,
			Date horaAnotacion) {
		super();
		this.idAnotacion = idAnotacion;
		NombreAnotacion = nombreAnotacion;
		Descripcion = descripcion;
		FechaAnotacion = fechaAnotacion;
		HoraAnotacion = horaAnotacion;
	}

	public int getIdAnotacion() {
		return idAnotacion;
	}

	public void setIdAnotacion(int idAnotacion) {
		this.idAnotacion = idAnotacion;
	}

	public String getNombreAnotacion() {
		return NombreAnotacion;
	}

	public void setNombreAnotacion(String nombreAnotacion) {
		NombreAnotacion = nombreAnotacion;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public Date getFechaAnotacion() {
		return FechaAnotacion;
	}

	public void setFechaAnotacion(Date fechaAnotacion) {
		FechaAnotacion = fechaAnotacion;
	}

	public Date getHoraAnotacion() {
		return HoraAnotacion;
	}

	public void setHoraAnotacion(Date horaAnotacion) {
		HoraAnotacion = horaAnotacion;
	}	
}
