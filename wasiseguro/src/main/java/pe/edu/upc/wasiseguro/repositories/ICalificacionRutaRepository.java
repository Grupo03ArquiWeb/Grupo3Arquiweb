package pe.edu.upc.wasiseguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.dtos.RutaSeguridadDTO;
import pe.edu.upc.wasiseguro.entities.CalificacionRuta;
import java.util.List;

@Repository
public interface ICalificacionRutaRepository extends JpaRepository<CalificacionRuta, Integer> {
    @Query("SELECT r.nombreDestino as nombreDestino, AVG(c.puntaje) as promedioSeguridad " +
            "FROM CalificacionRuta c JOIN c.ruta r " +
            "GROUP BY r.nombreDestino")
    List<RutaSeguridadDTO> getPromedioSeguridadPorRuta();
}