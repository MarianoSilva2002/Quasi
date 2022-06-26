package pe.edu.upc.main.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="RespuestaConsulta")
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

	
	@ManyToOne
	@JoinColumn(name="idConsulta", nullable = true)
	private Consulta consulta;
	
	public RespuestaConsultas() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RespuestaConsultas(int idRespuestaConsulta, String respuestaConsulta, Date fechaRespuesta, Date horaRespuesta, Consulta consulta) {
		this.idRespuestaConsulta = idRespuestaConsulta;
		RespuestaConsulta = respuestaConsulta;
		FechaRespuesta = fechaRespuesta;
		HoraRespuesta = horaRespuesta;
		this.consulta = consulta;
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

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
}
