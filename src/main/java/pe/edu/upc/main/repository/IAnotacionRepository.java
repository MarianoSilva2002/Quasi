package pe.edu.upc.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.main.model.Anotacion;
import pe.edu.upc.main.model.Consulta;

import java.util.List;

@Repository
public interface IAnotacionRepository extends JpaRepository<Anotacion, Integer>{
    @Query("from Anotacion a where a.idAlumno_curso.alumno.idAlumno = :idAlumno")
    List<Anotacion> anotacionporAlumno(@Param("idAlumno") int idAlumno);
}
