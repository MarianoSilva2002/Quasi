package pe.edu.upc.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.main.model.Alumno;

import java.util.List;

@Repository
public interface IAlumnoRepository extends JpaRepository<Alumno, Integer>{
    @Query("from Alumno a where a.correo = :correo and a.contrasena = :contrasena")
    List<Alumno> buscarContrasena(@Param("correo") String correo, @Param("contrasena") String contrasena);

    @Query("from Alumno a where a.correo = :correo")
    List<Alumno> buscarAlumnoporCorreo(@Param("correo") String correo);
}
