package pe.edu.upc.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import pe.edu.upc.main.model.Alumno;

public interface IAlumnoService {
	public boolean grabar(Alumno alumno);
	public void eliminar(int idAlumno);
	public Optional<Alumno> listarId(int idAlumno);
	public List<Alumno> listar();
	public List<Alumno> buscarContrasena(String correo, String contrasena);
	public List<Alumno> buscarAlumnoporCorreo(String correo);
}
