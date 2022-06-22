package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Docente;

public interface IDocenteService {
	public boolean grabar(Docente docente);
	public void eliminar(int idDocente);
	public Optional<Docente> listarId(int idDocente);
	public List<Docente> listar();
}
