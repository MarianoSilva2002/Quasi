package pe.edu.upc.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.main.model.Alumno;
import pe.edu.upc.main.model.Curso;

import java.util.List;

@Repository
public interface ICursoRepository extends JpaRepository<Curso, Integer>{
    @Query("from Curso c where c.iddocente.idDocente = :docente")
    List<Curso> cursosporDocente(@Param("docente") int docente);
}
