package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Preguntas_Seguridad;

public interface IPreguntas_SeguridadService {
	public boolean grabar(Preguntas_Seguridad preguntas_seguridad);
	public void eliminar(int idPreguntas_Seguridad);
	public Optional<Preguntas_Seguridad> listarId(int idPreguntas_Seguridad);
	public List<Preguntas_Seguridad> listar();
}
