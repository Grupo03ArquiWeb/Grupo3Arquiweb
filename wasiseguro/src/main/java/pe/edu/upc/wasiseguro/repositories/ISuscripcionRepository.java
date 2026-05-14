package pe.edu.upc.wasiseguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.dtos.SuscripcionPorEstadoDTO;
import pe.edu.upc.wasiseguro.dtos.SuscripcionPorPlanDTO;
import pe.edu.upc.wasiseguro.dtos.SuscripcionEstadisticaPlanDTO;
import pe.edu.upc.wasiseguro.dtos.SuscripcionEstadisticaEstadoDTO;
import pe.edu.upc.wasiseguro.entities.Suscripcion;

import java.util.List;

@Repository
public interface ISuscripcionRepository extends JpaRepository<Suscripcion, Integer> {


    @Query("SELECT s.estado, COUNT(s), MAX(p.nombre), MAX(s.fechaInicio), MAX(s.fechaFin) " +
            "FROM Suscripcion s JOIN s.planSuscripcion p " +
            "GROUP BY s.estado ORDER BY COUNT(s) DESC")
    List<Object[]> estadisticasPorEstado();


    @Query("SELECT p.nombre, COUNT(s), MAX(p.precioMensual), MAX(p.precioAnual), " +
            "SUM(p.precioMensual), CASE WHEN MAX(CASE WHEN p.activo = true THEN 1 ELSE 0 END) = 1 THEN true ELSE false END " +
            "FROM Suscripcion s JOIN s.planSuscripcion p " +
            "GROUP BY p.nombre ")
    List<Object[]> estadisticasPorPlan();

}