package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.NivelRiesgoListDTO;
import pe.edu.upc.wasiseguro.entities.NivelRiesgo;
import pe.edu.upc.wasiseguro.serviceinterface.INivelRiesgoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("nivelRiesgo")
public class NivelRiesgoController {
    @Autowired
    private INivelRiesgoService nS;

    @GetMapping
    public ResponseEntity<List<NivelRiesgoListDTO>> listar(){
        ModelMapper m= new ModelMapper();
        List<NivelRiesgoListDTO>listaNiveles=nS.list().stream()
                .map(y->m.map(y,NivelRiesgoListDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaNiveles);
    }
    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody NivelRiesgoListDTO dto) {
        ModelMapper m = new ModelMapper();
        NivelRiesgo nivelRiesgo = m.map(dto, NivelRiesgo.class);
        nS.insert(nivelRiesgo);
        return ResponseEntity.ok("Nivel de riesgo registrado correctamente");
    }

    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody NivelRiesgoListDTO dto) {
        ModelMapper m = new ModelMapper();
        NivelRiesgo nivelRiesgo = m.map(dto, NivelRiesgo.class);
        nS.insert(nivelRiesgo);
        return ResponseEntity.ok("Nivel de riesgo actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        NivelRiesgo nivelRiesgo = nS.listId(id);

        if (nivelRiesgo == null) {
            return ResponseEntity.badRequest().body("No existe el nivel de riesgo con id: " + id);
        }

        nS.delete(id);
        return ResponseEntity.ok("Nivel de riesgo eliminado correctamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable int id) {
        NivelRiesgo nivelRiesgo = nS.listId(id);

        if (nivelRiesgo == null) {
            return ResponseEntity.badRequest().body("No existe el nivel de riesgo con id: " + id);
        }

        ModelMapper m = new ModelMapper();
        NivelRiesgoListDTO dto = m.map(nivelRiesgo, NivelRiesgoListDTO.class);

        return ResponseEntity.ok(dto);
    }
}
