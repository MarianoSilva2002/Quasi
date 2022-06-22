package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Clase;
import pe.edu.upc.spring.repository.IClaseRepository;
import pe.edu.upc.spring.service.IClaseService;

@Service
public class ClaseServiceImpl implements IClaseService{

	@Autowired
	private IClaseRepository dClase;
	
	@Override
	@Transactional
	public boolean grabar(Clase clase) {
		Clase objClase = dClase.save(clase);
		if(objClase == null)
			return false;
		else
			return true;
	}
	

	@Override
	@Transactional
	public void eliminar(int idClase) {
		dClase.deleteById(idClase);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Clase> listarId(int idClase) {
		return dClase.findById(idClase);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Clase> listar() {
		return dClase.findAll();
	}
	
}
