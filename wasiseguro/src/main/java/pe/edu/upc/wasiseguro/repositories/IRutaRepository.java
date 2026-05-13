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

    @Query("SELECT new pe.edu.upc.wasiseguro.dtos.RutaSugeridaDTO(r.id, r.nombreOrigen, r.nombreDestino, r.geojsonTrayecto, true, 90.0) " +
            "FROM Ruta r " +
            "WHERE r.destinoLat = :lat AND r.destinoLng = :lng " +
            "ORDER BY r.distanciaKm ASC LIMIT 2")
    List<RutaSugeridaDTO> findTop2ByDestinoLatAndDestinoLng(@Param("lat") double lat, @Param("lng") double lng);

    List<Ruta> findByDestinoLatAndDestinoLng(double lat, double lng);

    // Toma de decisiones: Sugerencia de rutas que no tienen nivel de riesgo crítico (US Sugerencias)
    @Query("SELECT new pe.edu.upc.wasiseguro.dtos.RutaSugeridaDTO(r.id, r.nombreOrigen, r.nombreDestino, r.geojsonTrayecto, true, 95.0) " +
            "FROM Ruta r WHERE r.nivelRiesgo.idNivelRiesgo != 3")
    List<RutaSugeridaDTO> sugerirRutasSeguras();
}