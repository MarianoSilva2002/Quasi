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
	private int idPregunta;
	
	@Column(name="Pregunta", nullable = true, length = 100)
	private String Pregunta;

	public Preguntas_Seguridad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Preguntas_Seguridad(int idPregunta, String pregunta) {
		super();
		this.idPregunta = idPregunta;
		Pregunta = pregunta;
	}

	public int getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(int idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getPregunta() {
		return Pregunta;
	}

	public void setPregunta(String pregunta) {
		Pregunta = pregunta;
	}
}
