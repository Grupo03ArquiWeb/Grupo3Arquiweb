package pe.edu.upc.wasiseguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.dtos.RutaSeguridadDTO;
import pe.edu.upc.wasiseguro.entities.CalificacionRuta;
import java.util.List;

@Repository
public interface ICalificacionRutaRepository extends JpaRepository<CalificacionRuta, Integer> {

    // Toma de decisiones: Análisis estadístico de seguridad percibida por ruta (5 datos)
    @Query("SELECT new pe.edu.upc.wasiseguro.dtos.RutaSeguridadDTO(" +
            "r.nombreDestino, AVG(c.puntaje), COUNT(c.id), MAX(c.puntaje), MIN(c.puntaje)) " +
            "FROM CalificacionRuta c JOIN c.ruta r " +
            "GROUP BY r.nombreDestino " +
            "ORDER BY AVG(c.puntaje) DESC")
    List<RutaSeguridadDTO> getPromedioSeguridadPorRuta();
}