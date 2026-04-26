package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.TipoIncidenteDTO;
import pe.edu.upc.wasiseguro.entities.TipoIncidente;
import pe.edu.upc.wasiseguro.servicesinterfaces.ITipoIncidenteService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/TipoIncidente")
public class TipoIncidenteController {

    @Autowired
    private ITipoIncidenteService tS;

    @GetMapping("/listar")
    public ResponseEntity<List<TipoIncidenteDTO>> listar() {
        ModelMapper m = new ModelMapper();
        List<TipoIncidenteDTO> lista = tS.list().stream()
                .map(y -> m.map(y, TipoIncidenteDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> registrar(@RequestBody TipoIncidenteDTO dto) {
        ModelMapper m = new ModelMapper();
        TipoIncidente t = m.map(dto, TipoIncidente.class);
        TipoIncidente cur = tS.insert(t);
        TipoIncidenteDTO responseDTO = m.map(cur, TipoIncidenteDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable int id, @RequestBody TipoIncidenteDTO dto) {
        Optional<TipoIncidente> existente = tS.listId(id);

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("TipoIncidente no encontrado");
        }

        TipoIncidente tipoIncidente = existente.get();
        tipoIncidente.setNombre(dto.getNombre());
        tipoIncidente.setIconoUrl(dto.getIconoUrl());
        tipoIncidente.setActivo(dto.isActivo());

        tS.update(tipoIncidente);
        return ResponseEntity.ok("TipoIncidente actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<TipoIncidente> tipo = tS.listId(id);

        if (tipo.isPresent()) {
            tS.delete(id);
            return ResponseEntity.ok("TipoIncidente eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("TipoIncidente no encontrado");
        }
    }

    @GetMapping("/buscarpornombre/{nombre}")
    public ResponseEntity<List<TipoIncidenteDTO>> buscarPorNombre(@PathVariable String nombre) {
        ModelMapper m = new ModelMapper();
        List<TipoIncidenteDTO> lista = tS.buscarPorNombre(nombre).stream()
                .map(y -> m.map(y, TipoIncidenteDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/listaractivos")
    public ResponseEntity<List<TipoIncidenteDTO>> listarActivos() {
        ModelMapper m = new ModelMapper();
        List<TipoIncidenteDTO> lista = tS.listarActivos().stream()
                .map(y -> m.map(y, TipoIncidenteDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }
}