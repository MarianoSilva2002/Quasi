package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Curso;
import pe.edu.upc.spring.repository.ICursoRepository;
import pe.edu.upc.spring.service.ICursoService;

@Service
public class CursoServiceImpl implements ICursoService{

	@Autowired
	private ICursoRepository dCurso;
	
	@Override
	@Transactional
	public boolean grabar(Curso curso) {
		Curso objCurso = dCurso.save(curso);
		if(objCurso == null)
			return false;
		else
			return true;
	}
	

	@Override
	@Transactional
	public void eliminar(int idCurso) {
		dCurso.deleteById(idCurso);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Curso> listarId(int idCurso) {
		return dCurso.findById(idCurso);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Curso> listar() {
		return dCurso.findAll();
	}
	
}
