package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Anuncio;

public interface IAnuncioService {
	public boolean grabar(Anuncio anuncio);
	public void eliminar(int idAnuncio);
	public Optional<Anuncio> listarId(int idAnuncio);
	public List<Anuncio> listar();
}
