package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Docente;
import pe.edu.upc.spring.repository.IDocenteRepository;
import pe.edu.upc.spring.service.IDocenteService;

@Service
public class DocenteServiceImpl implements IDocenteService{

	@Autowired
	private IDocenteRepository dDocente;
	
	@Override
	@Transactional
	public boolean grabar(Docente docente) {
		Docente objDocente = dDocente.save(docente);
		if(objDocente == null)
			return false;
		else
			return true;
	}
	

	@Override
	@Transactional
	public void eliminar(int idDocente) {
		dDocente.deleteById(idDocente);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Docente> listarId(int idDocente) {
		return dDocente.findById(idDocente);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Docente> listar() {
		return dDocente.findAll();
	}
	
}
