package pe.edu.upc.main.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.main.model.Seccion;
import pe.edu.upc.main.repository.ISeccionRepository;
import pe.edu.upc.main.service.ISeccionService;

@Service
public class SeccionServiceImpl implements ISeccionService{

	@Autowired
	private ISeccionRepository dSeccion;
	
	@Override
	@Transactional
	public boolean grabar(Seccion seccion) {
		Seccion objSeccion = dSeccion.save(seccion);
		if(objSeccion == null)
			return false;
		else
			return true;
	}
	

	@Override
	@Transactional
	public void eliminar(int idSeccion) {
		dSeccion.deleteById(idSeccion);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Seccion> listarId(int idSeccion) {
		return dSeccion.findById(idSeccion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Seccion> listar() {
		return dSeccion.findAll();
	}

	@Override
	public List<Seccion> seccionporCurso(int curso) {
		return dSeccion.seccionporCurso(curso);
	}

}
