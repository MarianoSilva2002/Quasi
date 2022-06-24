package pe.edu.upc.main.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.main.model.Alumno;
import pe.edu.upc.main.model.Docente;

public interface IDocenteService {
	public boolean grabar(Docente docente);
	public void eliminar(int idDocente);
	public Optional<Docente> listarId(int idDocente);
	public List<Docente> listar();
	public List<Docente> buscarContrasena(String correo, String contrasena);
}
