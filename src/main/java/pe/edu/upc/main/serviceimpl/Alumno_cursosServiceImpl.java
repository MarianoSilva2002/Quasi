package pe.edu.upc.main.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.main.service.IAlumno_cursosService;
import pe.edu.upc.main.model.Alumno_cursos;
import pe.edu.upc.main.repository.IAlumno_cursosRepository;

@Service
public class Alumno_cursosServiceImpl implements IAlumno_cursosService {

	@Autowired
	private IAlumno_cursosRepository dAlumno_cursos;
	
	@Override
	@Transactional
	public boolean grabar(Alumno_cursos alumno_cursos) {
		Alumno_cursos objAlumno_cursos = dAlumno_cursos.save(alumno_cursos);
		if(objAlumno_cursos == null)
			return false;
		else
			return true;
	}
	

	@Override
	@Transactional
	public void eliminar(int idAlumno_cursos) {
		dAlumno_cursos.deleteById(idAlumno_cursos);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Alumno_cursos> listarId(int idAlumno_cursos) {
		return dAlumno_cursos.findById(idAlumno_cursos);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Alumno_cursos> listar() {
		return dAlumno_cursos.findAll();
	}

	@Override
	public List<Alumno_cursos> seccionesporAlumno(int alumno) {
		return dAlumno_cursos.seccionesporAlumno(alumno);
	}

	@Override
	public List<Alumno_cursos> seccionesporAlumnoySeccion(int alumno, int seccion) {
		return dAlumno_cursos.seccionesporAlumnoySeccion(alumno,seccion);
	}

}
