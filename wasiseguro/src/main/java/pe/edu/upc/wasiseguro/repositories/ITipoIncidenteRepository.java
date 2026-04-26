package pe.edu.upc.wasiseguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.entities.TipoIncidente;

import java.util.List;

@Repository
public interface ITipoIncidenteRepository extends JpaRepository<TipoIncidente, Integer> {
    List<TipoIncidente> findByNombreContainingIgnoreCase(String nombre);
    List<TipoIncidente> findByActivoTrue();
}