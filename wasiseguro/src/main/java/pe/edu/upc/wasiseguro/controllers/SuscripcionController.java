package pe.edu.upc.wasiseguro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.SuscripcionCreateDTO;
import pe.edu.upc.wasiseguro.dtos.SuscripcionDTO;
import pe.edu.upc.wasiseguro.dtos.SuscripcionEstadisticaPlanDTO;
import pe.edu.upc.wasiseguro.dtos.SuscripcionEstadisticaEstadoDTO;
import pe.edu.upc.wasiseguro.entities.PlanSuscripcion;
import pe.edu.upc.wasiseguro.entities.Suscripcion;
import pe.edu.upc.wasiseguro.entities.Usuario;
import pe.edu.upc.wasiseguro.repositories.IPlanSuscripcionRepository;
import pe.edu.upc.wasiseguro.repositories.IUsuarioRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.ISuscripcionService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/suscripciones")
public class SuscripcionController {

    @Autowired
    private ISuscripcionService sS;

    @Autowired
    private IUsuarioRepository uR;

    @Autowired
    private IPlanSuscripcionRepository pR;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody SuscripcionCreateDTO dto) {
        Optional<Usuario> usuarioOpt = uR.findById(dto.getIdUsuario());
        Optional<PlanSuscripcion> planOpt = pR.findById(dto.getIdPlan());

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        if (planOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PlanSuscripcion no encontrado");
        }

        Suscripcion s = new Suscripcion();
        s.setUsuario(usuarioOpt.get());
        s.setPlanSuscripcion(planOpt.get());
        s.setFechaInicio(dto.getFechaInicio());
        s.setFechaFin(dto.getFechaFin());
        s.setEstado(dto.getEstado());

        Suscripcion guardada = sS.insert(s);

        SuscripcionDTO responseDTO = new SuscripcionDTO();
        responseDTO.setId(guardada.getId());
        responseDTO.setIdUsuario(guardada.getUsuario().getId());
        responseDTO.setNombreUsuario(guardada.getUsuario().getNombre());
        responseDTO.setIdPlan(guardada.getPlanSuscripcion().getId());
        responseDTO.setPlanNombre(guardada.getPlanSuscripcion().getNombre());
        responseDTO.setPrecioMensual(guardada.getPlanSuscripcion().getPrecioMensual());
        responseDTO.setFechaInicio(guardada.getFechaInicio());
        responseDTO.setFechaFin(guardada.getFechaFin());
        responseDTO.setEstado(guardada.getEstado());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable int id, @RequestBody SuscripcionCreateDTO dto) {
        Optional<Suscripcion> suscripcionOpt = sS.listId(id);
        Optional<Usuario> usuarioOpt = uR.findById(dto.getIdUsuario());
        Optional<PlanSuscripcion> planOpt = pR.findById(dto.getIdPlan());

        if (suscripcionOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Suscripcion no encontrada");
        }

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        if (planOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PlanSuscripcion no encontrado");
        }

        Suscripcion s = suscripcionOpt.get();
        s.setUsuario(usuarioOpt.get());
        s.setPlanSuscripcion(planOpt.get());
        s.setFechaInicio(dto.getFechaInicio());
        s.setFechaFin(dto.getFechaFin());
        s.setEstado(dto.getEstado());

        sS.update(s);
        return ResponseEntity.ok("Suscripcion actualizada correctamente");
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Suscripcion> suscripcionOpt = sS.listId(id);

        if (suscripcionOpt.isPresent()) {
            sS.delete(id);
            return ResponseEntity.ok("Suscripcion eliminada exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Suscripcion no encontrada");
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<SuscripcionDTO>> listar() {
        List<SuscripcionDTO> lista = sS.list().stream().map(s -> {
            SuscripcionDTO dto = new SuscripcionDTO();
            dto.setId(s.getId());
            dto.setIdUsuario(s.getUsuario().getId());
            dto.setNombreUsuario(s.getUsuario().getNombre());
            dto.setIdPlan(s.getPlanSuscripcion().getId());
            dto.setPlanNombre(s.getPlanSuscripcion().getNombre());
            dto.setPrecioMensual(s.getPlanSuscripcion().getPrecioMensual());
            dto.setFechaInicio(s.getFechaInicio());
            dto.setFechaFin(s.getFechaFin());
            dto.setEstado(s.getEstado());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/filtrar/estado/{estado}")
    public ResponseEntity<List<SuscripcionDTO>> filtrarPorEstado(@PathVariable String estado) {
        List<SuscripcionDTO> lista = sS.filtrarPorEstado(estado).stream().map(s -> {
            SuscripcionDTO dto = new SuscripcionDTO();
            dto.setId(s.getId());
            dto.setIdUsuario(s.getUsuario().getId());
            dto.setNombreUsuario(s.getUsuario().getNombre());
            dto.setIdPlan(s.getPlanSuscripcion().getId());
            dto.setPlanNombre(s.getPlanSuscripcion().getNombre());
            dto.setPrecioMensual(s.getPlanSuscripcion().getPrecioMensual());
            dto.setFechaInicio(s.getFechaInicio());
            dto.setFechaFin(s.getFechaFin());
            dto.setEstado(s.getEstado());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/filtrar/fechas")
    public ResponseEntity<?> filtrarPorRangoFechas(
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin) {

        if (fechaInicio.isAfter(fechaFin)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La fecha de inicio no puede ser mayor que la fecha fin");
        }

        List<SuscripcionDTO> lista = sS.filtrarPorRangoFechas(fechaInicio, fechaFin).stream().map(s -> {
            SuscripcionDTO dto = new SuscripcionDTO();
            dto.setId(s.getId());
            dto.setIdUsuario(s.getUsuario().getId());
            dto.setNombreUsuario(s.getUsuario().getNombre());
            dto.setIdPlan(s.getPlanSuscripcion().getId());
            dto.setPlanNombre(s.getPlanSuscripcion().getNombre());
            dto.setPrecioMensual(s.getPlanSuscripcion().getPrecioMensual());
            dto.setFechaInicio(s.getFechaInicio());
            dto.setFechaFin(s.getFechaFin());
            dto.setEstado(s.getEstado());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/estadisticas/estado")
    public ResponseEntity<List<SuscripcionEstadisticaEstadoDTO>> estadisticasPorEstado() {
        return ResponseEntity.ok(sS.estadisticasPorEstado());
    }

    @GetMapping("/estadisticas/plan")
    public ResponseEntity<List<SuscripcionEstadisticaPlanDTO>> estadisticasPorPlan() {
        return ResponseEntity.ok(sS.estadisticasPorPlan());
    }
}