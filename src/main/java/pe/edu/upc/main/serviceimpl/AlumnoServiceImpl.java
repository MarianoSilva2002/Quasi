package pe.edu.upc.main.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.main.service.IAlumnoService;
import pe.edu.upc.main.model.Alumno;
import pe.edu.upc.main.repository.IAlumnoRepository;

@Service
public class AlumnoServiceImpl implements IAlumnoService {

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

	@Override
	@Transactional(readOnly = true)
	public List<Alumno> buscarContrasena(String correo, String contrasena) {
		return dAlumno.buscarContrasena(correo,contrasena);
	}

}
