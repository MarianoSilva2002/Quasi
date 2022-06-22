package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Alumno;
import pe.edu.upc.spring.repository.IAlumnoRepository;
import pe.edu.upc.spring.service.IAlumnoService;

@Service
public class AlumnoServiceImpl implements IAlumnoService{

	@Autowired
	private IAlumnoRepository dAlumno;
	
	@Override
	@Transactional
	public boolean grabar(Alumno alumno) {
		Alumno objAlumno = dAlumno.save(alumno);
		if(objAlumno == null)
			return false;
		else
			return true;
	}
	

	@Override
	@Transactional
	public void eliminar(int idAlumno) {
		dAlumno.deleteById(idAlumno);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Alumno> listarId(int idAlumno) {
		return dAlumno.findById(idAlumno);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Alumno> listar() {
		return dAlumno.findAll();
	}
	
}
