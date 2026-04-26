package pe.edu.upc.wasiseguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.dtos.SuscripcionPorEstadoDTO;
import pe.edu.upc.wasiseguro.dtos.SuscripcionPorPlanDTO;
import pe.edu.upc.wasiseguro.entities.Suscripcion;

import java.util.List;

@Repository
public interface ISuscripcionRepository extends JpaRepository<Suscripcion, Integer> {

    @Query("SELECT new pe.edu.upc.wasiseguro.dtos.SuscripcionPorEstadoDTO(s.estado, COUNT(s)) " +
            "FROM Suscripcion s GROUP BY s.estado")
    List<SuscripcionPorEstadoDTO> cantidadSuscripcionesPorEstado();

    @Query("SELECT new pe.edu.upc.wasiseguro.dtos.SuscripcionPorPlanDTO(s.planSuscripcion.nombre, COUNT(s)) " +
            "FROM Suscripcion s GROUP BY s.planSuscripcion.nombre")
    List<SuscripcionPorPlanDTO> cantidadSuscripcionesPorPlan();
}