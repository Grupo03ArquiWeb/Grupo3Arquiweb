package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.wasiseguro.dtos.ZonaRiegoDTO;
import pe.edu.upc.wasiseguro.serviceinterface.IZonaRiesgoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("ZonaRiesgo")
=======
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.ZonaRiesgoListDTO;
import pe.edu.upc.wasiseguro.dtos.ZonaRiesgoUpdateDTO;
import pe.edu.upc.wasiseguro.entities.NivelRiesgo;
import pe.edu.upc.wasiseguro.entities.ZonaRiesgo;
import pe.edu.upc.wasiseguro.repositories.INivelRiesgoRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IZonaRiesgoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ZonaRiesgo")
>>>>>>> main
public class ZonaRiesgoController {
    @Autowired
    private IZonaRiesgoService zS;

<<<<<<< HEAD
    @GetMapping
    public ResponseEntity<List<ZonaRiegoDTO>>listar(){
        ModelMapper z= new ModelMapper();
        List<ZonaRiegoDTO>listaZonas =zS.list().stream()
                .map(y->z.map(y,ZonaRiegoDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaZonas);

    }
}
=======
    @Autowired
    private INivelRiesgoRepository nivelR;

    @GetMapping("listar")
    public ResponseEntity<List<ZonaRiesgoListDTO>>listar(){
        ModelMapper z= new ModelMapper();
        List<ZonaRiesgoListDTO>listaZonas =zS.list().stream()
                .map(y->z.map(y, ZonaRiesgoListDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaZonas);
    }
    @PostMapping("/crear")
    public ResponseEntity<?> registrar(@RequestBody ZonaRiesgoListDTO dto){
        ModelMapper m=new ModelMapper();
        ZonaRiesgo c=m.map(dto, ZonaRiesgo.class);
        Optional<NivelRiesgo> nivelrOpt = nivelR.findById(dto.getIdNivelRiesgo());
        if (nivelrOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Nivel de Riesgo no válido");
        }
        c.setNivelRiesgo(nivelrOpt.get());
        ZonaRiesgo cur= zS.insert(c);
        ZonaRiesgoListDTO responseDTO=m.map(cur, ZonaRiesgoListDTO.class);
        responseDTO.setIdNivelRiesgo(cur.getNivelRiesgo().getIdNivelRiesgo());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable int id, @RequestBody ZonaRiesgoUpdateDTO dto)  {
        Optional<ZonaRiesgo> existente = zS.listId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Zona de Riesgo no encontrado");
        }
        ZonaRiesgo zonariesgo = existente.get();
        zonariesgo.setNombreZonaRiesgo(dto.getNombreZonaRiesgo());
        zonariesgo.setDescripcionZonaRiesgo(dto.getDescripcionZonaRiesgo());
        Optional<NivelRiesgo> nivelrOpt = nivelR.findById(dto.getIdNivelRiesgo());
        if (nivelrOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Nivel de Riesgo no válido");
        }
        zonariesgo.setNivelRiesgo(nivelrOpt.get());
        zonariesgo.setGeomZonaRiesgo(dto.getGeomZonaRiesgo());
        zonariesgo.setRiesgoporHora(dto.getRiesgoporHora());
        zonariesgo.setActivoZonaRiesgo(dto.isActivoZonaRiesgo());
        zonariesgo.setUpdateAtZonaRiesgo(dto.getUpdateAtZonaRiesgo());
        zS.update(zonariesgo);
        return ResponseEntity.ok("ZonaRiesgo actualizado correctamente");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<ZonaRiesgo> zona = zS.listId(id);
        if (zona.isPresent()) {
            zS.delete(id);
            return ResponseEntity.ok("Zona de Riesgo eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Zona de Riesgo no encontrado");
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?> listarId(@PathVariable int id) {
        Optional<ZonaRiesgo> zonaRiesgo = zS.listId(id);

        if (zonaRiesgo == null) {
            return ResponseEntity.badRequest().body("No existe la zona de riesgo con id: " + id);
        }

        ModelMapper z = new ModelMapper();
        ZonaRiesgoListDTO dto = z.map(zonaRiesgo, ZonaRiesgoListDTO.class);

        return ResponseEntity.ok(dto);
    }
    //Query
    @GetMapping("/activas")
    public ResponseEntity<?> listarActivas() {
        ModelMapper z = new ModelMapper();

        List<ZonaRiesgoListDTO> listaZonas = zS.listarActivas().stream()
                .map(y -> z.map(y, ZonaRiesgoListDTO.class))
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
>>>>>>> main
