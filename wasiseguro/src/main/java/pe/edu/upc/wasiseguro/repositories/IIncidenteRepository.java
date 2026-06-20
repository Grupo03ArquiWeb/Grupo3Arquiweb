package pe.edu.upc.wasiseguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.dtos.IncidenteCantidadDTO;
import pe.edu.upc.wasiseguro.dtos.IncidenteRankingDTO;
import pe.edu.upc.wasiseguro.entities.Incidente;
import java.util.List;
import java.util.UUID;

@Repository
public interface IIncidenteRepository extends JpaRepository<Incidente, UUID> {

    List<Incidente> findAllByOrderByFechaOcurridoDesc();

    List<Incidente> findByEstado(String estado);

    List<Incidente> findByTipoIncidenteNombre(String nombreTipo);

    List<Incidente> findByVotosValidoGreaterThan(int minVotos);

    // Toma de decisiones: Reporte de cantidad de incidentes por tipo y estado (US49)
    @Query("SELECT new pe.edu.upc.wasiseguro.dtos.IncidenteCantidadDTO(i.tipoIncidente.nombre, i.estado, COUNT(i), MAX(i.fechaOcurrido), MIN(i.fechaOcurrido)) " +
            "FROM Incidente i " +
            "GROUP BY i.tipoIncidente.nombre, i.estado")
    List<IncidenteCantidadDTO> countIncidentesByType();

    // Toma de decisiones: Ranking de actividad y fidelidad ciudadana por usuario (US43)
    @Query("SELECT new pe.edu.upc.wasiseguro.dtos.IncidenteRankingDTO(i.usuario.nombre, i.usuario.email, COUNT(i.id), MIN(i.fechaOcurrido), MAX(i.fechaOcurrido)) " +
            "FROM Incidente i " +
            "GROUP BY i.usuario.nombre, i.usuario.email " +
            "ORDER BY COUNT(i.id) DESC")
    List<IncidenteRankingDTO> obtenerRankingUsuarios();
}