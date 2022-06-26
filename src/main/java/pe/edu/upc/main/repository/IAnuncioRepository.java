package pe.edu.upc.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.main.model.Alumno;
import pe.edu.upc.main.model.Anuncio;

import java.util.List;

@Repository
public interface IAnuncioRepository extends JpaRepository<Anuncio, Integer>{
    @Query("from Anuncio a where a.idseccion.idSeccion = :idSeccion")
    List<Anuncio> anunciosporSeccion(@Param("idSeccion") int idSeccion);
}
