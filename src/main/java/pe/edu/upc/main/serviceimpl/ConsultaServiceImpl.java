package pe.edu.upc.main.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.main.model.Consulta;
import pe.edu.upc.main.repository.IConsultaRepository;
import pe.edu.upc.main.service.IConsultaService;

@Service
public class ConsultaServiceImpl implements IConsultaService{

	@Autowired
	private IConsultaRepository dConsulta;
	
	@Override
	@Transactional
	public boolean grabar(Consulta consulta) {
		Consulta objConsulta = dConsulta.save(consulta);
		if(objConsulta == null)
			return false;
		else
			return true;
	}
	

	@Override
	@Transactional
	public void eliminar(int idConsulta) {
		dConsulta.deleteById(idConsulta);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Consulta> listarId(int idConsulta) {
		return dConsulta.findById(idConsulta);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Consulta> listar() {
		return dConsulta.findAll();
	}

	@Override
	public List<Consulta> consultaporAlumno(int idAlumno) {
		return dConsulta.consultaporAlumno(idAlumno);
	}

	@Override
	public List<Consulta> consultaporSeccion(int idSeccion) {
		return dConsulta.consultaporSeccion(idSeccion);
	}

}
