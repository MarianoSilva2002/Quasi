package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Curso;

public interface ICursoService {
	public boolean grabar(Curso curso);
	public void eliminar(int idCurso);
	public Optional<Curso> listarId(int idCurso);
	public List<Curso> listar();
}
