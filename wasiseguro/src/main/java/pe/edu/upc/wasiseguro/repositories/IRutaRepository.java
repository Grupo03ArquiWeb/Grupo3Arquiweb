package pe.edu.upc.wasiseguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.dtos.RutaSugeridaDTO;
import pe.edu.upc.wasiseguro.dtos.RutasFavoritasDTO;
import pe.edu.upc.wasiseguro.entities.Ruta;
import java.util.List;
import java.util.UUID;

@Repository
public interface IRutaRepository extends JpaRepository<Ruta, UUID> {
    List<RutasFavoritasDTO> findByEsFavoritaTrue();

    List<Ruta> findByUsuarioId(UUID idUsuario);
    List<RutaSugeridaDTO> findTop2ByDestinoLatAndDestinoLng(double lat, double lng);

    List<Ruta> findByDestinoLatAndDestinoLng(double lat, double lng);
    @Query("SELECT r.id as id, r.nombreOrigen as nombreOrigen, r.nombreDestino as nombreDestino, " +
            "r.geojsonTrayecto as geojsonTrayecto, true as esSegura, 95.0 as seguridadScore " +
            "FROM Ruta r WHERE r.nivelRiesgo.idNivelRiesgo != 3")
    List<RutaSugeridaDTO> sugerirRutasSeguras();
}