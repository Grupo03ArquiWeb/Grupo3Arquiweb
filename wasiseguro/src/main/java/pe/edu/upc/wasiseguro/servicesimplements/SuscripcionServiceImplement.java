package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.dtos.SuscripcionPorEstadoDTO;
import pe.edu.upc.wasiseguro.dtos.SuscripcionPorPlanDTO;
import pe.edu.upc.wasiseguro.entities.Suscripcion;
import pe.edu.upc.wasiseguro.repositories.ISuscripcionRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.ISuscripcionService;
import java.time.LocalDate;
import java.util.UUID;
import java.util.ArrayList;
import pe.edu.upc.wasiseguro.dtos.SuscripcionVigenciaDTO;
import java.time.LocalDate;
import pe.edu.upc.wasiseguro.dtos.VincularPlanDTO;
import pe.edu.upc.wasiseguro.entities.PlanSuscripcion;
import pe.edu.upc.wasiseguro.entities.Usuario;
import pe.edu.upc.wasiseguro.repositories.IPlanSuscripcionRepository;
import pe.edu.upc.wasiseguro.repositories.IUsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SuscripcionServiceImplement implements ISuscripcionService {

    @Autowired
    private ISuscripcionRepository sR;

    @Override
    public List<Suscripcion> list() {
        return sR.findAll();
    }

    @Override
    public Suscripcion insert(Suscripcion s) {
        return sR.save(s);
    }

    @Override
    public Optional<Suscripcion> listId(int id) {
        return sR.findById(id);
    }

    @Override
    public void update(Suscripcion s) {
        sR.save(s);
    }

    @Override
    public void delete(int id) {
        sR.deleteById(id);
    }

    @Autowired
    private IUsuarioRepository uR;

    @Autowired
    private IPlanSuscripcionRepository pR;

    @Override
    public List<SuscripcionPorEstadoDTO> cantidadSuscripcionesPorEstado() {
        return sR.cantidadSuscripcionesPorEstado();
    }

    @Override
    public List<SuscripcionPorPlanDTO> cantidadSuscripcionesPorPlan() {
        return sR.cantidadSuscripcionesPorPlan();
    }

    @Override
    public List<Suscripcion> filtrar(UUID idUsuario, Integer idPlan, String estado, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Suscripcion> lista = sR.findAll();
        List<Suscripcion> listaFiltrada = new ArrayList<>();

        for (Suscripcion s : lista) {
            boolean agregar = true;

            if (idUsuario != null && !s.getUsuario().getId().equals(idUsuario)) {
                agregar = false;
            }

            if (idPlan != null && s.getPlanSuscripcion().getId() != idPlan) {
                agregar = false;
            }

            if (estado != null && !s.getEstado().equalsIgnoreCase(estado)) {
                agregar = false;
            }

            if (fechaInicio != null && s.getFechaInicio().isBefore(fechaInicio)) {
                agregar = false;
            }

            if (fechaFin != null && s.getFechaFin().isAfter(fechaFin)) {
                agregar = false;
            }

            if (agregar) {
                listaFiltrada.add(s);
            }
        }

        return listaFiltrada;
    }

    @Override
    public SuscripcionVigenciaDTO validarVigencia(int id) {
        Optional<Suscripcion> suscripcionOpt = sR.findById(id);

        if (suscripcionOpt.isEmpty()) {
            return null;
        }

        Suscripcion s = suscripcionOpt.get();
        SuscripcionVigenciaDTO dto = new SuscripcionVigenciaDTO();

        dto.setId(s.getId());
        dto.setNombreUsuario(s.getUsuario().getNombre());
        dto.setPlanNombre(s.getPlanSuscripcion().getNombre());
        dto.setFechaInicio(s.getFechaInicio());
        dto.setFechaFin(s.getFechaFin());
        dto.setEstadoRegistrado(s.getEstado());

        LocalDate hoy = LocalDate.now();

        if (hoy.isAfter(s.getFechaFin())) {
            dto.setVigencia("vencida");
        } else {
            dto.setVigencia("activa");
        }

        return dto;
    }

    @Override
    public Suscripcion vincularPlan(VincularPlanDTO dto) {
        Optional<Suscripcion> suscripcionOpt = sR.findById(dto.getIdSuscripcion());
        Optional<Usuario> usuarioOpt = uR.findById(dto.getIdUsuario());
        Optional<PlanSuscripcion> planOpt = pR.findById(dto.getIdPlan());

        if (suscripcionOpt.isEmpty()) {
            return null;
        }

        if (usuarioOpt.isEmpty()) {
            return null;
        }

        if (planOpt.isEmpty()) {
            return null;
        }

        Suscripcion suscripcion = suscripcionOpt.get();

        if (!suscripcion.getUsuario().getId().equals(dto.getIdUsuario())) {
            return null;
        }

        suscripcion.setPlanSuscripcion(planOpt.get());
        return sR.save(suscripcion);
    }
}