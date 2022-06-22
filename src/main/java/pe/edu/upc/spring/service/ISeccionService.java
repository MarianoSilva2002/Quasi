package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Seccion;

public interface ISeccionService {
	public boolean grabar(Seccion seccion);
	public void eliminar(int idSeccion);
	public Optional<Seccion> listarId(int idSeccion);
	public List<Seccion> listar();
}
