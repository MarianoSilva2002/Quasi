package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.RespuestaConsultas;

public interface IRespuestaConsultasService {
	public boolean grabar(RespuestaConsultas respuestaconsultas);
	public void eliminar(int idRespuestaConsultas);
	public Optional<RespuestaConsultas> listarId(int idRespuestaConsultas);
	public List<RespuestaConsultas> listar();
}
