package pe.edu.upc.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.main.model.Clase;

@Repository
public interface IClaseRepository extends JpaRepository<Clase, Integer>{
	
}
