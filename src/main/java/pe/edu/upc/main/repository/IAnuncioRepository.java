package pe.edu.upc.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.main.model.Anuncio;

@Repository
public interface IAnuncioRepository extends JpaRepository<Anuncio, Integer>{
	
}
