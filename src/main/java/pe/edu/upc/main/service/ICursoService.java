package pe.edu.upc.main.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.main.model.Curso;
import pe.edu.upc.main.model.Docente;

public interface ICursoService {
	public boolean grabar(Curso curso);
	public void eliminar(int idCurso);
	public Optional<Curso> listarId(int idCurso);
	public List<Curso> listar();
	public List<Curso> cursosporDocente(int docente);

}
