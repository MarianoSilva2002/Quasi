package pe.edu.upc.main.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.main.model.Preguntas_Seguridad;
import pe.edu.upc.main.repository.IPreguntas_SeguridadRepository;
import pe.edu.upc.main.service.IPreguntas_SeguridadService;

@Service
public class Preguntas_SeguridadServiceImpl implements IPreguntas_SeguridadService{

	@Autowired
	private IPreguntas_SeguridadRepository dPreguntas_Seguridad;
	
	@Override
	@Transactional
	public boolean grabar(Preguntas_Seguridad preguntas_seguridad) {
		Preguntas_Seguridad objPreguntas_Seguridad = dPreguntas_Seguridad.save(preguntas_seguridad);
		if(objPreguntas_Seguridad == null)
			return false;
		else
			return true;
	}
	

	@Override
	@Transactional
	public void eliminar(int idPreguntas_Seguridad) {
		dPreguntas_Seguridad.deleteById(idPreguntas_Seguridad);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Preguntas_Seguridad> listarId(int idPreguntas_Seguridad) {
		return dPreguntas_Seguridad.findById(idPreguntas_Seguridad);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Preguntas_Seguridad> listar() {
		return dPreguntas_Seguridad.findAll();
	}
	
}
