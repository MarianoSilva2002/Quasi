package pe.edu.upc.main.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.main.model.Docente;
import pe.edu.upc.main.repository.IDocenteRepository;
import pe.edu.upc.main.service.IDocenteService;

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

	@Override
	@Transactional(readOnly = true)
	public List<Docente> buscarContrasena(String correo, String contrasena) {
		return dDocente.buscarContrasena(correo,contrasena);
	}
	@Override
	@Transactional(readOnly = true)
	public List<Docente> buscarDocenteporCorreo(String correo) {
		return dDocente.buscarDocenteporCorreo(correo);
	}

}
