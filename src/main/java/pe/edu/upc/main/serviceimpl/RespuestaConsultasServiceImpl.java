package pe.edu.upc.main.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.main.model.RespuestaConsultas;
import pe.edu.upc.main.repository.IRespuestaConsultasRepository;
import pe.edu.upc.main.service.IRespuestaConsultasService;

@Service
public class RespuestaConsultasServiceImpl implements IRespuestaConsultasService{

	@Autowired
	private IRespuestaConsultasRepository dRespuestaConsultas;
	
	@Override
	@Transactional
	public boolean grabar(RespuestaConsultas respuestaconsultas) {
		RespuestaConsultas objRespuestaConsultas = dRespuestaConsultas.save(respuestaconsultas);
		if(objRespuestaConsultas == null)
			return false;
		else
			return true;
	}
	

	@Override
	@Transactional
	public void eliminar(int idRespuestaConsultas) {
		dRespuestaConsultas.deleteById(idRespuestaConsultas);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<RespuestaConsultas> listarId(int idRespuestaConsultas) {
		return dRespuestaConsultas.findById(idRespuestaConsultas);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RespuestaConsultas> listar() {
		return dRespuestaConsultas.findAll();
	}
	
}
