package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Anotacion;
import pe.edu.upc.spring.repository.IAnotacionRepository;
import pe.edu.upc.spring.service.IAnotacionService;

@Service
public class AnotacionServiceImpl implements IAnotacionService{

	@Autowired
	private IAnotacionRepository dAnotacion;
	
	@Override
	@Transactional
	public boolean grabar(Anotacion anotacion) {
		Anotacion objAnotacion = dAnotacion.save(anotacion);
		if(objAnotacion == null)
			return false;
		else
			return true;
	}
	

	@Override
	@Transactional
	public void eliminar(int idAnotacion) {
		dAnotacion.deleteById(idAnotacion);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Anotacion> listarId(int idAnotacion) {
		return dAnotacion.findById(idAnotacion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Anotacion> listar() {
		return dAnotacion.findAll();
	}
	
}
