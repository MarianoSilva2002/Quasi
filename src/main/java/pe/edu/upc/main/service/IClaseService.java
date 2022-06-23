package pe.edu.upc.main.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.main.model.Clase;

public interface IClaseService {
	public boolean grabar(Clase clase);
	public void eliminar(int idClase);
	public Optional<Clase> listarId(int idClase);
	public List<Clase> listar();
}
