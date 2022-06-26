package pe.edu.upc.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import pe.edu.upc.main.model.Anotacion;

public interface IAnotacionService {
	public boolean grabar(Anotacion anotacion);
	public void eliminar(int idAnotacion);
	public Optional<Anotacion> listarId(int idAnotacion);
	public List<Anotacion> listar();
	public List<Anotacion> anotacionporAlumno(int idAlumno);

}
