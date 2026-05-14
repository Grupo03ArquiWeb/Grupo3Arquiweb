package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.*;
import pe.edu.upc.wasiseguro.entities.*;
import pe.edu.upc.wasiseguro.repositories.IUsuarioRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IEventoPanicoService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/EventoPanico")
public class EventoPanicoController {

    @Autowired
    private IEventoPanicoService epS;

    @Autowired
    private IUsuarioRepository userR;

    // US47 - Listar todos (solo ADMIN)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERADOR')")
    @GetMapping("listar")
    public ResponseEntity<List<EventoPanicoListDTO>> listar(){
        ModelMapper m= new ModelMapper();
        List<EventoPanicoListDTO>listaUsers=epS.list().stream()
                .map(y->m.map(y, EventoPanicoListDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaUsers);
    }
    // US47 - Crear evento de pánico
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERADOR', 'ROLE_USER')")
    @PostMapping("/crear")
    public ResponseEntity<?> registrar(@RequestBody EventoPanicoCreateDTO dto){

        ModelMapper m=new ModelMapper();
        EventoPanico c=m.map(dto, EventoPanico.class);
        Optional<Usuario> userOpt = userR.findById(dto.getIdUsuario());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Usuario no válido");
        }
        c.setUsuario(userOpt.get());
        c.setAtendido(false);
        EventoPanico cur= epS.insert(c);
        EventoPanicoCreateDTO responseDTO=m.map(cur, EventoPanicoCreateDTO.class);
        responseDTO.setIdUsuario(cur.getUsuario().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERADOR', 'ROLE_USER')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable UUID id, @RequestBody EventoPanicoCreateDTO dto)  {
        Optional<EventoPanico> existente = epS.listId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Evento no encontrado");
        }
        EventoPanico eventP = existente.get();
        Optional<Usuario> userOpt = userR.findById(dto.getIdUsuario());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Usuario no válido");
        }
        eventP.setUsuario(userOpt.get());
        eventP.setLatitud(dto.getLatitud());
        eventP.setLongitud(dto.getLongitud());
        eventP.setMensajeExtra(dto.getMensajeExtra());
        epS.update(eventP);
        return ResponseEntity.ok("Evento de Pánico actualizado correctamente");
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERADOR', 'ROLE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable UUID id) {
        Optional<EventoPanico> user = epS.listId(id);
        if (user.isPresent()) {
            epS.delete(id);
            return ResponseEntity.ok("Usuario eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }
    }
    // Filtro 1
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERADOR')")
    @GetMapping("/buscarporatendido")
    public ResponseEntity<List<EventoPanicoCreateDTO>> buscarPorAtendido(@RequestParam boolean atendido) {
        ModelMapper m = new ModelMapper();
        List<EventoPanicoCreateDTO> resultado = epS.buscarPorAtendido(atendido).stream()
                .map(y -> {
                    EventoPanicoCreateDTO dto = m.map(y, EventoPanicoCreateDTO.class);
                    dto.setIdUsuario(y.getUsuario().getId());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultado);
    }

    // Filtro 2
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERADOR')")
    @GetMapping("/buscarporusuario")
    public ResponseEntity<List<EventoPanicoCreateDTO>> buscarPorUsuario(@RequestParam UUID idUsuario) {
        ModelMapper m = new ModelMapper();
        List<EventoPanicoCreateDTO> resultado = epS.buscarPorUsuario(idUsuario).stream()
                .map(y -> {
                    EventoPanicoCreateDTO dto = m.map(y, EventoPanicoCreateDTO.class);
                    dto.setIdUsuario(y.getUsuario().getId());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultado);
    }
    // US47 - Marcar evento como atendido
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PatchMapping("/{id}/atender")
    public ResponseEntity<String> atender(@PathVariable UUID id) {
        Optional<EventoPanico> existente = epS.listId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento no encontrado");
        }
        EventoPanico evento = existente.get();
        evento.setAtendido(true);
        epS.update(evento);
        return ResponseEntity.ok("Evento marcado como atendido");
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/resumen-por-usuario")
    public ResponseEntity<List<ResumenEventoPanicoDTO>> resumenPorUsuario() {
        return ResponseEntity.ok(epS.resumenEventosPorUsuario());
    }

    // Toma de decisiones 2 — Eventos recientes con datos del usuario (solo ADMIN)
    // GET /api/EventoPanico/recientes?limite=10
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/recientes")
    public ResponseEntity<List<EventoRecienteDTO>> eventosRecientes(
            @RequestParam(defaultValue = "10") int limite) {
        return ResponseEntity.ok(epS.eventosRecientesConDatosUsuario(limite));
    }

}
