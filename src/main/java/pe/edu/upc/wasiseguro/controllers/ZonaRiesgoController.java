package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.ZonaRiegoDTO;
import pe.edu.upc.wasiseguro.entities.ZonaRiesgo;
import pe.edu.upc.wasiseguro.serviceinterface.IZonaRiesgoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ZonaRiesgo")
public class ZonaRiesgoController {

    @Autowired
    private IZonaRiesgoService zS;

    @GetMapping
    public ResponseEntity<List<ZonaRiegoDTO>> listar() {
        ModelMapper z = new ModelMapper();
        List<ZonaRiegoDTO> listaZonas = zS.list().stream()
                .map(y -> z.map(y, ZonaRiegoDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(listaZonas);
    }

    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody ZonaRiegoDTO dto) {
        ModelMapper z = new ModelMapper();
        ZonaRiesgo zonaRiesgo = z.map(dto, ZonaRiesgo.class);
        zS.insert(zonaRiesgo);
        return ResponseEntity.ok("Zona de riesgo registrada correctamente");
    }

    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody ZonaRiegoDTO dto) {
        ModelMapper z = new ModelMapper();
        ZonaRiesgo zonaRiesgo = z.map(dto, ZonaRiesgo.class);
        zS.insert(zonaRiesgo);
        return ResponseEntity.ok("Zona de riesgo actualizada correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        ZonaRiesgo zonaRiesgo = zS.listId(id);

        if (zonaRiesgo == null) {
            return ResponseEntity.badRequest().body("No existe la zona de riesgo con id: " + id);
        }

        zS.delete(id);
        return ResponseEntity.ok("Zona de riesgo eliminada correctamente");
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> listarId(@PathVariable int id) {
        ZonaRiesgo zonaRiesgo = zS.listId(id);

        if (zonaRiesgo == null) {
            return ResponseEntity.badRequest().body("No existe la zona de riesgo con id: " + id);
        }

        ModelMapper z = new ModelMapper();
        ZonaRiegoDTO dto = z.map(zonaRiesgo, ZonaRiegoDTO.class);

        return ResponseEntity.ok(dto);
    }

    //Query
    @GetMapping("/activas")
    public ResponseEntity<?> listarActivas() {
        ModelMapper z = new ModelMapper();

        List<ZonaRiegoDTO> listaZonas = zS.listarActivas().stream()
                .map(y -> z.map(y, ZonaRiegoDTO.class))
                .collect(Collectors.toList());

        if (listaZonas.isEmpty()) {
            return ResponseEntity.badRequest().body("No existen zonas de riesgo activas");
        }

        return ResponseEntity.ok(listaZonas);
    }
    @GetMapping("/reporte/niveles")
    public ResponseEntity<?> reporteNiveles() {
        return ResponseEntity.ok(zS.cantidadZonasPorNivel());
    }
    @GetMapping("/reporte/zonas-activas-hora")
    public ResponseEntity<?> zonasActivasOrdenadasPorRiesgoHora() {
        List<String[]> lista = zS.zonasActivasOrdenadasPorRiesgoHora();

        if (lista.isEmpty()) {
            return ResponseEntity.badRequest().body("No existen zonas activas registradas");
        }

        return ResponseEntity.ok(lista);
    }
}
