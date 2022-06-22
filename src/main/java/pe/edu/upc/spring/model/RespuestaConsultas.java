package pe.edu.upc.spring.model;

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
@Table(name="Consulta")
public class RespuestaConsultas implements Serializable{
	
	private static final long serialVersionUID = 11;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRespuestaConsulta;
	
	@Column(name="RespuestaConsulta", nullable = true, length = 500)
	private String RespuestaConsulta;
	
	@Column(name="FechaRespuesta", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date FechaRespuesta;
	
	@Column(name="HoraRespuesta", nullable = true)
	@Temporal(TemporalType.TIME)
	private Date HoraRespuesta;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Consultas_id;
	
	//@ManyToOne
	//@JoinColumn(name="idAlumno_curso", nullable = true)
	//private idAlumno_curso idAlumno_curso;
	
	public RespuestaConsultas() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RespuestaConsultas(int idRespuestaConsulta, String respuestaConsulta, Date fechaRespuesta,
			Date horaRespuesta, int consultas_id) {
		super();
		this.idRespuestaConsulta = idRespuestaConsulta;
		RespuestaConsulta = respuestaConsulta;
		FechaRespuesta = fechaRespuesta;
		HoraRespuesta = horaRespuesta;
		Consultas_id = consultas_id;
	}

	public int getIdRespuestaConsulta() {
		return idRespuestaConsulta;
	}

	public void setIdRespuestaConsulta(int idRespuestaConsulta) {
		this.idRespuestaConsulta = idRespuestaConsulta;
	}

	public String getRespuestaConsulta() {
		return RespuestaConsulta;
	}

	public void setRespuestaConsulta(String respuestaConsulta) {
		RespuestaConsulta = respuestaConsulta;
	}

	public Date getFechaRespuesta() {
		return FechaRespuesta;
	}

	public void setFechaRespuesta(Date fechaRespuesta) {
		FechaRespuesta = fechaRespuesta;
	}

	public Date getHoraRespuesta() {
		return HoraRespuesta;
	}

	public void setHoraRespuesta(Date horaRespuesta) {
		HoraRespuesta = horaRespuesta;
	}

	public int getConsultas_id() {
		return Consultas_id;
	}

	public void setConsultas_id(int consultas_id) {
		Consultas_id = consultas_id;
	}
	
}
