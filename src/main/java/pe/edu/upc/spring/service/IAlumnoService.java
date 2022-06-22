package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Alumno;

public interface IAlumnoService {
	public boolean grabar(Alumno alumno);
	public void eliminar(int idAlumno);
	public Optional<Alumno> listarId(int idAlumno);
	public List<Alumno> listar();
}
