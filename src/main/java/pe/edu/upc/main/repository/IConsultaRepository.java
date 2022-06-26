package pe.edu.upc.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.main.model.Anuncio;
import pe.edu.upc.main.model.Consulta;

import java.util.List;

@Repository
public interface IConsultaRepository extends JpaRepository<Consulta, Integer>{
    @Query("from Consulta c where c.idAlumno_curso.alumno.idAlumno = :idAlumno")
    List<Consulta> consultaporAlumno(@Param("idAlumno") int idAlumno);

    @Query("from Consulta c where c.idAlumno_curso.seccion.idSeccion = :idSeccion")
    List<Consulta> consultaporSeccion(@Param("idSeccion") int idSeccion);
}
