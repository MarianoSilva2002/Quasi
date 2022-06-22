package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Consulta;

public interface IConsultaService {
	public boolean grabar(Consulta consulta);
	public void eliminar(int idConsulta);
	public Optional<Consulta> listarId(int idConsulta);
	public List<Consulta> listar();
}
