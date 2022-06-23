package pe.edu.upc.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.main.model.Alumno;

@Repository
public interface IAlumnoRepository extends JpaRepository<Alumno, Integer>{
	
}