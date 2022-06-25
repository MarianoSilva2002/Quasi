package pe.edu.upc.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.main.model.Alumno;
import pe.edu.upc.main.model.Alumno_cursos;
import pe.edu.upc.main.model.Curso;

import java.util.List;

@Repository
public interface IAlumno_cursosRepository extends JpaRepository<Alumno_cursos, Integer>{

    @Query("from Alumno_cursos ac where ac.Alumno.idAlumno = :alumno")
    List<Alumno_cursos> cursosporAlumno(@Param("alumno") int alumno);

}
