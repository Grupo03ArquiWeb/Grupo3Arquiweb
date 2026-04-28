package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.NivelRiesgoListDTO;
import pe.edu.upc.wasiseguro.dtos.NivelRiesgoUpdateDTO;
import pe.edu.upc.wasiseguro.entities.NivelRiesgo;
import pe.edu.upc.wasiseguro.servicesinterfaces.INivelRiesgoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/NivelRiesgo")
public class NivelRiesgoController {
    @Autowired
    private INivelRiesgoService nS;

    @GetMapping("listar")
    public ResponseEntity<List<NivelRiesgoListDTO>>listar(){
        ModelMapper m= new ModelMapper();
        List<NivelRiesgoListDTO>listaNiveles=nS.list().stream()
                .map(y->m.map(y, NivelRiesgoListDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaNiveles);
    }
    @PostMapping("/crear")
    public ResponseEntity<?> registrar(@RequestBody NivelRiesgoListDTO dto){
        ModelMapper m=new ModelMapper();
        NivelRiesgo c=m.map(dto, NivelRiesgo.class);
        NivelRiesgo cur= nS.insert(c);
        NivelRiesgoListDTO responseDTO=m.map(cur, NivelRiesgoListDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable int id, @RequestBody NivelRiesgoUpdateDTO dto)  {
        Optional<NivelRiesgo> existente = nS.listId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("NivelRiesgo no encontrado");
        }
        NivelRiesgo nivelriesgo = existente.get();
        nivelriesgo.setNameNivelRiesgo(dto.getNameNivelRiesgo());
        nivelriesgo.setColorHexNivelRiesgo(dto.getColorHexNivelRiesgo());
        nivelriesgo.setOrdenNivelRiesgo(dto.getOrdenNivelRiesgo());
        nS.update(nivelriesgo);
        return ResponseEntity.ok("NivelRiesgo actualizado correctamente");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<NivelRiesgo> nivel = nS.listId(id);
        if (nivel.isPresent()) {
            nS.delete(id);
            return ResponseEntity.ok("NivelRiesgo eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("NivelRiesgo no encontrado");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable int id) {
        Optional<NivelRiesgo> nivelRiesgo = nS.listId(id);

        if (nivelRiesgo == null) {
            return ResponseEntity.badRequest().body("No existe el nivel de riesgo con id: " + id);
        }

        ModelMapper m = new ModelMapper();
        NivelRiesgoListDTO dto = m.map(nivelRiesgo, NivelRiesgoListDTO.class);

        return ResponseEntity.ok(dto);
    }
}
