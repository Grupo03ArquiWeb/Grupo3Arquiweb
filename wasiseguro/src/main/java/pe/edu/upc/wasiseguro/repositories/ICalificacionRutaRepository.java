package pe.edu.upc.wasiseguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.dtos.RutaSeguridadDTO;
import pe.edu.upc.wasiseguro.entities.CalificacionRuta;

import java.util.List;

@Repository
public interface ICalificacionRutaRepository extends JpaRepository<CalificacionRuta, Integer> {

    @Query(value = "SELECT r.nombre_destino as nombreDestino, AVG(c.puntaje) as promedioSeguridad " +
            "FROM calificacion_ruta c JOIN ruta r ON c.id_ruta = r.id " +
            "GROUP BY r.nombre_destino", nativeQuery = true)
    List<RutaSeguridadDTO> getPromedioSeguridadPorRuta();
}