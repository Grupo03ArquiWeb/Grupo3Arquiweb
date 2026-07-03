package pe.edu.upc.wasiseguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.dtos.TipoIncidentePorEstadoDTO;
import pe.edu.upc.wasiseguro.dtos.TipoIncidenteEstadisticasDTO;
import pe.edu.upc.wasiseguro.entities.TipoIncidente;

import java.util.List;

@Repository
public interface ITipoIncidenteRepository extends JpaRepository<TipoIncidente, Integer> {
    List<TipoIncidente> findByNombreContainingIgnoreCase(String nombre);
    List<TipoIncidente> findByActivoTrue();

    @Query("SELECT new pe.edu.upc.wasiseguro.dtos.TipoIncidentePorEstadoDTO(t.activo, COUNT(t)) " +
            "FROM TipoIncidente t GROUP BY t.activo")
    List<TipoIncidentePorEstadoDTO> cantidadPorEstado();

    @Query("SELECT t.nombre, t.descripcion, COUNT(i), " +
            "SUM(CASE WHEN i.estado = 'ACTIVO' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN i.estado = 'pendiente' THEN 1 ELSE 0 END) " +
            "FROM TipoIncidente t LEFT JOIN Incidente i ON t.id = i.tipoIncidente.id " +
            "GROUP BY t.id, t.nombre, t.descripcion ORDER BY COUNT(i) DESC")
    List<Object[]> estadisticasIncidentes();
}