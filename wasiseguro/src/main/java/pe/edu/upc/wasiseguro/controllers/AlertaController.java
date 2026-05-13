package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.AlertaDTO;
import pe.edu.upc.wasiseguro.entities.Alerta;
import pe.edu.upc.wasiseguro.entities.Usuario;
import pe.edu.upc.wasiseguro.repositories.IUsuarioRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IAlertaService;
import pe.edu.upc.wasiseguro.servicesinterfaces.IIncidenteService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    @Autowired
    private IAlertaService aS;

    @Autowired
    private IUsuarioRepository userR;

    @Autowired
    private IIncidenteService incidenteService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping
    public void registrar(@RequestBody AlertaDTO dto) {
        ModelMapper m = new ModelMapper();
        Alerta a = m.map(dto, Alerta.class);
        aS.insert(a);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public List<AlertaDTO> listar() {
        return aS.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, AlertaDTO.class);
        }).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") UUID id) {
        aS.delete(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/noleidas")
    public List<AlertaDTO> buscarNoLeidas(@RequestParam UUID u) {
        return aS.buscarNoLeidas(u).stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, AlertaDTO.class);
        }).collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/vigentes")
    public List<AlertaDTO> buscarVigentes() {
        return aS.buscarVigentes().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, AlertaDTO.class);
        }).collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/enviar-notificacion/{idUsuario}")
    public ResponseEntity<String> enviarAlertaTraducida(@PathVariable UUID idUsuario, @RequestParam String zona) {
        Usuario user = userR.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        String mensajeTraducido = incidenteService.obtenerMensajeTraducido(user, zona);
        return ResponseEntity.ok(mensajeTraducido);
    }
}