package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Anotacion;

public interface IAnotacionService {
	public boolean grabar(Anotacion anotacion);
	public void eliminar(int idAnotacion);
	public Optional<Anotacion> listarId(int idAnotacion);
	public List<Anotacion> listar();
}
