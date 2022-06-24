package pe.edu.upc.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.main.model.Alumno_cursos;

@Repository
public interface IAlumno_cursosRepository extends JpaRepository<Alumno_cursos, Integer>{
	
}
