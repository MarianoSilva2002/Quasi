package pe.edu.upc.main.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.main.model.Anuncio;
import pe.edu.upc.main.repository.IAnuncioRepository;
import pe.edu.upc.main.service.IAnuncioService;

@Service
public class AnuncioServiceImpl implements IAnuncioService{

	@Autowired
	private IAnuncioRepository dAnuncio;
	
	@Override
	@Transactional
	public boolean grabar(Anuncio anuncio) {
		Anuncio objAnuncio = dAnuncio.save(anuncio);
		if(objAnuncio == null)
			return false;
		else
			return true;
	}
	

	@Override
	@Transactional
	public void eliminar(int idAnuncio) {
		dAnuncio.deleteById(idAnuncio);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Anuncio> listarId(int idAnuncio) {
		return dAnuncio.findById(idAnuncio);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Anuncio> listar() {
		return dAnuncio.findAll();
	}

	@Override
	public List<Anuncio> anunciosporSeccion(int idSeccion) {
		return dAnuncio.anunciosporSeccion(idSeccion);
	}

}
