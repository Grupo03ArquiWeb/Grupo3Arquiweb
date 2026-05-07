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

    @Query(value = "SELECT r.id as id, r.nombre_origen as nombreOrigen, r.nombre_destino as nombreDestino, r.es_favorita as esFavorita " +
            "FROM ruta r WHERE r.es_favorita = true", nativeQuery = true)
    List<RutasFavoritasDTO> findFavoritas();

    @Query(value = "SELECT r.id as id, r.nombre_origen as nombreOrigen, r.nombre_destino as nombreDestino, " +
            "r.geojson_trayecto as geojsonTrayecto, " +
            "TRUE as esSegura, " +
            "95.5 as seguridadScore " +
            "FROM ruta r " +
            "JOIN nivel_riesgo nr ON r.nivel_riesgo = nr.id_nivel_riesgo " +
            "WHERE nr.name_nivel_riesgo != 'Alto'", nativeQuery = true)
    List<RutaSugeridaDTO> sugerirRutasSeguras();

    @Query(value = "SELECT * FROM ruta WHERE id_usuario = ?1", nativeQuery = true)
    List<Ruta> findRutasByUsuarioId(UUID idUsuario);

    @Query(value = "SELECT r.id as id, r.nombre_origen as nombreOrigen, r.nombre_destino as nombreDestino, " +
            "r.geojson_trayecto as geojsonTrayecto, " +
            "(r.nivel_riesgo = 1) as esSegura, " +
            "80.0 as seguridadScore " +
            "FROM ruta r WHERE r.destino_lat = :destLat AND r.destino_lng = :destLng LIMIT 2", nativeQuery = true)
    List<RutaSugeridaDTO> findAlternativasByDestino(@Param("destLat") double destLat, @Param("destLng") double destLng);

    @Query(value = "SELECT * FROM ruta WHERE destino_lat = ?1 AND destino_lng = ?2", nativeQuery = true)
    List<Ruta> findRutasEntidadByDestino(double lat, double lng);
}