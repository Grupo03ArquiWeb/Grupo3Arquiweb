package pe.edu.upc.wasiseguro.servicesinterfaces;

import pe.edu.upc.wasiseguro.dtos.SuscripcionPorEstadoDTO;
import pe.edu.upc.wasiseguro.dtos.SuscripcionPorPlanDTO;
import pe.edu.upc.wasiseguro.entities.Suscripcion;
import pe.edu.upc.wasiseguro.dtos.VincularPlanDTO;
import java.time.LocalDate;
import java.util.UUID;
import pe.edu.upc.wasiseguro.dtos.SuscripcionVigenciaDTO;

import java.util.List;
import java.util.Optional;

public interface ISuscripcionService {
    public List<Suscripcion> list();
    public Suscripcion insert(Suscripcion s);
    public Optional<Suscripcion> listId(int id);
    public void update(Suscripcion s);
    public void delete(int id);
    public SuscripcionVigenciaDTO validarVigencia(int id);
    public Suscripcion vincularPlan(VincularPlanDTO dto);

    public List<SuscripcionPorEstadoDTO> cantidadSuscripcionesPorEstado();
    public List<SuscripcionPorPlanDTO> cantidadSuscripcionesPorPlan();

    public List<Suscripcion> filtrar(UUID idUsuario, Integer idPlan, String estado, LocalDate fechaInicio, LocalDate fechaFin);
}