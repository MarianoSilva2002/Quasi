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

    @Query("from Alumno_cursos ac where ac.alumno.idAlumno =:alumno")
    List<Alumno_cursos> seccionesporAlumno(@Param("alumno") int alumno);
    @Query("from Alumno_cursos ac where ac.alumno.idAlumno =:alumno and ac.seccion.idSeccion =:seccion")
    List<Alumno_cursos> seccionesporAlumnoySeccion(@Param("alumno") int alumno, @Param("seccion") int seccion);

}
