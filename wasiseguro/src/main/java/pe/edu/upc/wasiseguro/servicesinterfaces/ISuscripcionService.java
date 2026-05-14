package pe.edu.upc.wasiseguro.servicesinterfaces;

import pe.edu.upc.wasiseguro.dtos.SuscripcionEstadisticaPlanDTO;
import pe.edu.upc.wasiseguro.dtos.SuscripcionEstadisticaEstadoDTO;
import pe.edu.upc.wasiseguro.entities.Suscripcion;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ISuscripcionService {
    // CRUD
    public List<Suscripcion> list();
    public Suscripcion insert(Suscripcion s);
    public Optional<Suscripcion> listId(int id);
    public void update(Suscripcion s);
    public void delete(int id);

    // Filtros simples
    public List<Suscripcion> filtrarPorEstado(String estado);
    public List<Suscripcion> filtrarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin);

    // (6 atributos)
    public List<SuscripcionEstadisticaEstadoDTO> estadisticasPorEstado();
    public List<SuscripcionEstadisticaPlanDTO> estadisticasPorPlan();
}