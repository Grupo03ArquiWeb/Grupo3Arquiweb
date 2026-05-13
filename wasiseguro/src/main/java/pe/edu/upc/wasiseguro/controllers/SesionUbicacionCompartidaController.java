package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.entities.Usuario;
import pe.edu.upc.wasiseguro.repositories.IUsuarioRepository;
import pe.edu.upc.wasiseguro.dtos.SesionUbicacionCompartidaDTO;
import pe.edu.upc.wasiseguro.entities.SesionUbicacionCompartida;
import pe.edu.upc.wasiseguro.servicesinterfaces.ISesionUbicacionCompartidaService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/SesionUbicacionCompartida")
public class SesionUbicacionCompartidaController {

    @Autowired
    private IUsuarioRepository usuarioR;

    @Autowired
    private ISesionUbicacionCompartidaService sS;

    @GetMapping
    public ResponseEntity<List<SesionUbicacionCompartidaDTO>> listar() {
        ModelMapper m = new ModelMapper();

        List<SesionUbicacionCompartidaDTO> lista = sS.list().stream()
                .map(x -> m.map(x, SesionUbicacionCompartidaDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> registrar(@RequestBody SesionUbicacionCompartidaDTO dto) {

        Optional<Usuario> usuarioOpt = usuarioR.findById(dto.getIdUsuario());

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Usuario no válido");
        }

        SesionUbicacionCompartida sesion = new SesionUbicacionCompartida();
        sesion.setUsuario(usuarioOpt.get());
        sesion.setActiva(dto.isActiva());
        sesion.setIniciadaEn(dto.getIniciadaEn());
        sesion.setFinalizadaEn(dto.getFinalizadaEn());

        SesionUbicacionCompartida guardado = sS.insert(sesion);

        SesionUbicacionCompartidaDTO responseDTO = new SesionUbicacionCompartidaDTO();
        responseDTO.setId(guardado.getId());
        responseDTO.setIdUsuario(dto.getIdUsuario());
        responseDTO.setActiva(guardado.isActiva());
        responseDTO.setIniciadaEn(guardado.getIniciadaEn());
        responseDTO.setFinalizadaEn(guardado.getFinalizadaEn());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable UUID id) {
        Optional<SesionUbicacionCompartida> sesion = sS.listId(id);

        if (sesion.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe la sesión de ubicación compartida con id: " + id);
        }

        ModelMapper m = new ModelMapper();
        SesionUbicacionCompartidaDTO dto = m.map(sesion.get(), SesionUbicacionCompartidaDTO.class);

        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable UUID id) {
        Optional<SesionUbicacionCompartida> sesion = sS.listId(id);

        if (sesion.isPresent()) {
            sS.delete(id);
            return ResponseEntity.ok("Sesión de ubicación compartida eliminada exitosamente");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Sesión de ubicación compartida no encontrada");
    }
}