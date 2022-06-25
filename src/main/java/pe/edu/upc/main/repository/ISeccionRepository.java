package pe.edu.upc.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.main.model.Curso;
import pe.edu.upc.main.model.Seccion;

import java.util.List;

@Repository
public interface ISeccionRepository extends JpaRepository<Seccion, Integer>{
    @Query("from Seccion s where s.idcurso.idCurso = :curso")
    List<Seccion> seccionporCurso(@Param("curso") int curso);
}
