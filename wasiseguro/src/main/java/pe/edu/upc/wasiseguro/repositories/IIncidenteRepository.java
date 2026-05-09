package pe.edu.upc.wasiseguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.dtos.IncidenteCantidadDTO;
import pe.edu.upc.wasiseguro.entities.Incidente;
import java.util.List;
import java.util.UUID;

@Repository
public interface IIncidenteRepository extends JpaRepository<Incidente, UUID> {
    List<Incidente> findAllByOrderByFechaOcurridoDesc();
    List<Incidente> findByEstado(String estado);
    List<Incidente> findByTipoIncidenteNombre(String nombreTipo);
    List<Incidente> findByVotosValidoGreaterThan(int minVotos);

    @Query("SELECT new pe.edu.upc.wasiseguro.dtos.IncidenteCantidadDTO(i.tipoIncidente.nombre, COUNT(i)) FROM Incidente i GROUP BY i.tipoIncidente.nombre")
    List<IncidenteCantidadDTO> countIncidentesByType();
}