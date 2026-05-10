package pe.edu.upc.wasiseguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.dtos.TipoIncidentePorEstadoDTO;
import pe.edu.upc.wasiseguro.entities.TipoIncidente;

import java.util.List;

@Repository
public interface ITipoIncidenteRepository extends JpaRepository<TipoIncidente, Integer> {
    List<TipoIncidente> findByNombreContainingIgnoreCase(String nombre);
    List<TipoIncidente> findByActivoTrue();

    @Query("SELECT new pe.edu.upc.wasiseguro.dtos.TipoIncidentePorEstadoDTO(t.activo, COUNT(t)) " +
            "FROM TipoIncidente t GROUP BY t.activo")
    List<TipoIncidentePorEstadoDTO> cantidadPorEstado();
}