package pe.edu.upc.wasiseguro.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.IncidenteCantidadDTO;
import pe.edu.upc.wasiseguro.dtos.IncidenteDTO;
import pe.edu.upc.wasiseguro.dtos.IncidenteRankingDTO;
import pe.edu.upc.wasiseguro.entities.Incidente;
import pe.edu.upc.wasiseguro.entities.Usuario;
import pe.edu.upc.wasiseguro.repositories.IUsuarioRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IIncidenteService;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/incidentes")
public class IncidenteController {

    @Autowired
    private IIncidenteService iS;

    @Autowired
    private IUsuarioRepository uR;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/crear")
    public void registrar(@Valid @RequestBody IncidenteDTO dto) {
        ModelMapper m = new ModelMapper();
        Incidente i = m.map(dto, Incidente.class);
        iS.insert(i);
    }

    @GetMapping("/listar")
    public List<IncidenteDTO> listar() {
        return iS.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, IncidenteDTO.class);
        }).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") UUID id) {
        iS.delete(id);
    }

    @GetMapping("/buscarestado")
    public List<IncidenteDTO> buscarPorEstado(@RequestParam String e) {
        return iS.buscarPorEstado(e).stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, IncidenteDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/buscartipo")
    public List<IncidenteDTO> buscarPorTipo(@RequestParam String t) {
        return iS.buscarPorTipo(t).stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, IncidenteDTO.class);
        }).collect(Collectors.toList());
    }

    // Reporte US49: Cantidades por Tipo y Estado
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/reporte-cantidades")
    public List<IncidenteCantidadDTO> obtenerReporte() {
        return iS.reporteCantidades();
    }

    // Reporte US43: Ranking de Usuarios Pro (5 datos)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/reporte-usuarios")
    public List<IncidenteRankingDTO> reportePorUsuarios() {
        return iS.reportePorUsuario();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping("/modificar")
    public void modificar(@Valid @RequestBody IncidenteDTO dto, Authentication authentication) {
        String emailLogueado = authentication.getName();
        ModelMapper m = new ModelMapper();
        Incidente i = m.map(dto, Incidente.class);
        iS.updateOwned(i, emailLogueado);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping("/usuario/eliminar/{id}")
    public void eliminarPropio(@PathVariable("id") UUID id, Authentication authentication) {
        String emailLogueado = authentication.getName();
        iS.deleteOwned(id, emailLogueado);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @PatchMapping("/votar/{id}/{esPositivo}")
    public void votar(@PathVariable("id") UUID id, @PathVariable("esPositivo") boolean esPositivo, Authentication auth) {
        String emailVotante = auth.getName();
        iS.votar(id, emailVotante, esPositivo);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/{id}/comentar")
    public ResponseEntity<String> publicarComentario(@PathVariable UUID id, @RequestParam String texto, @RequestParam String email) {
        iS.agregarComentario(id, texto, email);
        return ResponseEntity.ok("✅ ¡Comentario publicado!");
    }

    @GetMapping("/{id}/comentarios")
    public List<Incidente.ComentarioEmbeddable> verComentarios(@PathVariable UUID id) {
        return iS.listarComentarios(id);
    }

    @GetMapping("/obtener-mensaje/{idUsuario}")
    public ResponseEntity<String> obtenerMensaje(@PathVariable UUID idUsuario, @RequestParam String zona) {
        Usuario user = uR.findById(idUsuario).orElseThrow();
        if (!iS.verificarSiEnviarAlerta(user)) {
            return ResponseEntity.ok("INFO: La alerta no se envió por tu configuración de frecuencia o silencio.");
        }
        return ResponseEntity.ok(iS.obtenerMensajeTraducido(user, zona));
    }
}