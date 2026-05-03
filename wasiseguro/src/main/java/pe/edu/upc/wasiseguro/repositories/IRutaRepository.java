package pe.edu.upc.wasiseguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.entities.Ruta;

import java.util.UUID;

@Repository
public interface IRutaRepository extends JpaRepository<Ruta, UUID> {
}
