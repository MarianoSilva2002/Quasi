package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Alumno_cursos;

public interface IAlumno_cursosService {
	public boolean grabar(Alumno_cursos alumno_cursos);
	public void eliminar(int idAlumno_cursos);
	public Optional<Alumno_cursos> listarId(int idAlumno_cursos);
	public List<Alumno_cursos> listar();
}
