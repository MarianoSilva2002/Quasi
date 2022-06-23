package pe.edu.upc.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.main.model.Docente;

@Repository
public interface IDocenteRepository extends JpaRepository<Docente, Integer>{
	
}
