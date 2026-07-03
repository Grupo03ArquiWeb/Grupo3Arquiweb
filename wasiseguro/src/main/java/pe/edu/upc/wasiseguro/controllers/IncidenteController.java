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
    public void registrar(@Valid @RequestBody IncidenteDTO dto, Authentication authentication) {
        Usuario u = uR.findByEmail(authentication.getName());
        ModelMapper m = new ModelMapper();
        Incidente i = m.map(dto, Incidente.class);
        i.setUsuario(u);
        iS.insert(i);
    }

    @GetMapping("/listar")
    public List<IncidenteDTO> listar() {
        ModelMapper m = new ModelMapper();
        return iS.list().stream().map(y -> {
            IncidenteDTO dto = m.map(y, IncidenteDTO.class);
            if (y.getUsuario() != null) {
                dto.setNombreUsuario(y.getUsuario().getNombre() + " " + y.getUsuario().getApellido());
                dto.setEmailUsuario(y.getUsuario().getEmail());
            }
            if (y.getTipoIncidente() != null) {
                dto.setTipoIncidenteNombre(y.getTipoIncidente().getNombre());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public IncidenteDTO listarPorId(@PathVariable("id") UUID id) {
        ModelMapper m = new ModelMapper();
        Incidente i = iS.findById(id);
        IncidenteDTO dto = m.map(i, IncidenteDTO.class);
        if (i.getUsuario() != null) {
            dto.setNombreUsuario(i.getUsuario().getNombre() + " " + i.getUsuario().getApellido());
            dto.setEmailUsuario(i.getUsuario().getEmail());
        }
        if (i.getTipoIncidente() != null) {
            dto.setTipoIncidenteNombre(i.getTipoIncidente().getNombre());
        }
        return dto;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") UUID id) {
        iS.delete(id);
    }

    @GetMapping("/buscarestado")
    public List<IncidenteDTO> buscarPorEstado(@RequestParam String e) {
        ModelMapper m = new ModelMapper();
        return iS.buscarPorEstado(e).stream().map(y -> {
            IncidenteDTO dto = m.map(y, IncidenteDTO.class);
            if (y.getUsuario() != null) {
                dto.setNombreUsuario(y.getUsuario().getNombre() + " " + y.getUsuario().getApellido());
                dto.setEmailUsuario(y.getUsuario().getEmail());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/buscartipo")
    public List<IncidenteDTO> buscarPorTipo(@RequestParam String t) {
        ModelMapper m = new ModelMapper();
        return iS.buscarPorTipo(t).stream().map(y -> {
            IncidenteDTO dto = m.map(y, IncidenteDTO.class);
            if (y.getUsuario() != null) {
                dto.setNombreUsuario(y.getUsuario().getNombre() + " " + y.getUsuario().getApellido());
                dto.setEmailUsuario(y.getUsuario().getEmail());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/reporte-cantidades")
    public List<IncidenteCantidadDTO> obtenerReporte() {
        return iS.reporteCantidades();
    }

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