package pe.edu.upc.main.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.main.model.Anuncio;

public interface IAnuncioService {
	public boolean grabar(Anuncio anuncio);
	public void eliminar(int idAnuncio);
	public Optional<Anuncio> listarId(int idAnuncio);
	public List<Anuncio> listar();
}
