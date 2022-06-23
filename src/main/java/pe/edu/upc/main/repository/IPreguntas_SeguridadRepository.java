package pe.edu.upc.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.main.model.Preguntas_Seguridad;

@Repository
public interface IPreguntas_SeguridadRepository extends JpaRepository<Preguntas_Seguridad, Integer>{
	
}
