package pe.edu.upc.wasiseguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.dtos.IncidenteCantidadDTO;
import pe.edu.upc.wasiseguro.entities.Incidente;

import java.util.List;
import java.util.UUID;

@Repository
public interface IIncidenteRepository extends JpaRepository<Incidente, UUID> {

    @Query("SELECT i FROM Incidente i WHERE i.estado = :estado")
    List<Incidente> buscarPorEstado(@Param("estado") String estado);

    @Query("SELECT i FROM Incidente i WHERE i.tipoIncidente.nombre = :nombreTipo")
    List<Incidente> buscarPorTipo(@Param("nombreTipo") String nombreTipo);

    @Query("SELECT i FROM Incidente i WHERE i.votosValido > :minVotos")
    List<Incidente> buscarIncidentesMuyReportados(@Param("minVotos") int minVotos);

    @Query("SELECT new pe.edu.upc.wasiseguro.dtos.IncidenteCantidadDTO(i.tipoIncidente.nombre, COUNT(i)) " +
            "FROM Incidente i GROUP BY i.tipoIncidente.nombre")
    List<IncidenteCantidadDTO> countIncidentesByType();
}