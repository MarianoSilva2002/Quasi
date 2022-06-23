package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Preguntas_Seguridad")
public class Preguntas_Seguridad implements Serializable{
	
	private static final long serialVersionUID = 11;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPreguntas_Seguridad;
	
	@Column(name="Pregunta", nullable = true, length = 100)
	private String Pregunta;

	public Preguntas_Seguridad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Preguntas_Seguridad(int idPreguntas_Seguridad, String pregunta) {
		super();
		this.idPreguntas_Seguridad = idPreguntas_Seguridad;
		Pregunta = pregunta;
	}

	public int getIdPreguntas_Seguridad() {
		return idPreguntas_Seguridad;
	}

	public void setIdPreguntas_Seguridad(int idPreguntas_Seguridad) {
		this.idPreguntas_Seguridad = idPreguntas_Seguridad;
	}

	public String getPregunta() {
		return Pregunta;
	}

	public void setPregunta(String pregunta) {
		Pregunta = pregunta;
	}

	
}
