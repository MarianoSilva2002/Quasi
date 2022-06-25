package pe.edu.upc.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.main.model.Alumno;
import pe.edu.upc.main.model.Docente;

import java.util.List;

@Repository
public interface IDocenteRepository extends JpaRepository<Docente, Integer>{
    @Query("from Docente d where d.correo = :correo and d.contrasena = :contrasena")
    List<Docente> buscarContrasena(@Param("correo") String correo, @Param("contrasena") String contrasena);
    @Query("from Docente d where d.correo = :correo")
    List<Docente> buscarDocenteporCorreo(@Param("correo") String correo);
}
