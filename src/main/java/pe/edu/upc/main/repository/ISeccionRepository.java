package pe.edu.upc.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.main.model.Seccion;

@Repository
public interface ISeccionRepository extends JpaRepository<Seccion, Integer>{
	
}
