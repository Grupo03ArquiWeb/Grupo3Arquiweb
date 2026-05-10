package pe.edu.upc.wasiseguro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.SuscripcionCreateDTO;
import pe.edu.upc.wasiseguro.dtos.SuscripcionDTO;
import pe.edu.upc.wasiseguro.dtos.SuscripcionPorEstadoDTO;
import pe.edu.upc.wasiseguro.dtos.SuscripcionPorPlanDTO;
import pe.edu.upc.wasiseguro.entities.PlanSuscripcion;
import pe.edu.upc.wasiseguro.entities.Suscripcion;
import pe.edu.upc.wasiseguro.entities.Usuario;
import pe.edu.upc.wasiseguro.dtos.VincularPlanDTO;
import pe.edu.upc.wasiseguro.repositories.IPlanSuscripcionRepository;
import pe.edu.upc.wasiseguro.repositories.IUsuarioRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.ISuscripcionService;
import org.modelmapper.ModelMapper;
import java.time.LocalDate;
import java.util.UUID;
import pe.edu.upc.wasiseguro.dtos.SuscripcionVigenciaDTO;

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

    @GetMapping("/cantidad-por-estado")
    public ResponseEntity<List<SuscripcionPorEstadoDTO>> cantidadPorEstado() {
        return ResponseEntity.ok(sS.cantidadSuscripcionesPorEstado());
    }

    @GetMapping("/cantidad-por-plan")
    public ResponseEntity<List<SuscripcionPorPlanDTO>> cantidadPorPlan() {
        return ResponseEntity.ok(sS.cantidadSuscripcionesPorPlan());
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<SuscripcionDTO>> filtrar(
            @RequestParam(required = false) UUID idUsuario,
            @RequestParam(required = false) Integer idPlan,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) LocalDate fechaInicio,
            @RequestParam(required = false) LocalDate fechaFin
    ) {
        ModelMapper m = new ModelMapper();

        List<SuscripcionDTO> lista = sS.filtrar(idUsuario, idPlan, estado, fechaInicio, fechaFin)
                .stream()
                .map(s -> {
                    SuscripcionDTO dto = m.map(s, SuscripcionDTO.class);
                    dto.setIdUsuario(s.getUsuario().getId());
                    dto.setNombreUsuario(s.getUsuario().getNombre());
                    dto.setIdPlan(s.getPlanSuscripcion().getId());
                    dto.setPlanNombre(s.getPlanSuscripcion().getNombre());
                    dto.setPrecioMensual(s.getPlanSuscripcion().getPrecioMensual());
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/validar-vigencia/{id}")
    public ResponseEntity<?> validarVigencia(@PathVariable int id) {
        SuscripcionVigenciaDTO dto = sS.validarVigencia(id);

        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Suscripcion no encontrada");
        }

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/vincular-plan")
    public ResponseEntity<?> vincularPlan(@RequestBody VincularPlanDTO dto) {
        Optional<Suscripcion> suscripcionOpt = sS.listId(dto.getIdSuscripcion());

        if (suscripcionOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Suscripcion no encontrada");
        }

        Optional<Usuario> usuarioOpt = uR.findById(dto.getIdUsuario());
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        Optional<PlanSuscripcion> planOpt = pR.findById(dto.getIdPlan());
        if (planOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PlanSuscripcion no encontrado");
        }

        Suscripcion suscripcion = suscripcionOpt.get();

        if (!suscripcion.getUsuario().getId().equals(dto.getIdUsuario())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La suscripcion no pertenece al usuario enviado");
        }

        suscripcion.setPlanSuscripcion(planOpt.get());
        sS.update(suscripcion);

        SuscripcionDTO responseDTO = new SuscripcionDTO();
        responseDTO.setId(suscripcion.getId());
        responseDTO.setIdUsuario(suscripcion.getUsuario().getId());
        responseDTO.setNombreUsuario(suscripcion.getUsuario().getNombre());
        responseDTO.setIdPlan(suscripcion.getPlanSuscripcion().getId());
        responseDTO.setPlanNombre(suscripcion.getPlanSuscripcion().getNombre());
        responseDTO.setPrecioMensual(suscripcion.getPlanSuscripcion().getPrecioMensual());
        responseDTO.setFechaInicio(suscripcion.getFechaInicio());
        responseDTO.setFechaFin(suscripcion.getFechaFin());
        responseDTO.setEstado(suscripcion.getEstado());

        return ResponseEntity.ok(responseDTO);
    }
}