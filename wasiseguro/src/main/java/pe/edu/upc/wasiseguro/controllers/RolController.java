package pe.edu.upc.wasiseguro.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.RolListDTO;
import pe.edu.upc.wasiseguro.dtos.RolUpdateDTO;
import pe.edu.upc.wasiseguro.entities.Rol;
import pe.edu.upc.wasiseguro.servicesinterfaces.IRolService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rol")
public class RolController {
    @Autowired
    private IRolService rolS;

    @GetMapping("listar")
    public ResponseEntity<List<RolListDTO>> listar(){
        ModelMapper m= new ModelMapper();
        List<RolListDTO>listarRoles=rolS.list().stream()
                .map(y->m.map(y, RolListDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listarRoles);
    }
    @PostMapping("/crear")
    public ResponseEntity<?> registrar(@RequestBody RolUpdateDTO dto){
        ModelMapper m=new ModelMapper();
        Rol c=m.map(dto, Rol.class);
        Rol cur= rolS.insert(c);
        RolUpdateDTO responseDTO=m.map(cur, RolUpdateDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable int id, @RequestBody RolUpdateDTO dto)  {
        Optional<Rol> existente = rolS.listId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Rol no encontrado");
        }
        Rol rol = existente.get();
        rol.setNombre(dto.getNombre());
        rol.setDescripcion(dto.getDescripcion());
        rol.setActivo(dto.isActivo());
        rolS.update(rol);
        return ResponseEntity.ok("Rol actualizado correctamente");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Rol> user = rolS.listId(id);
        if (user.isPresent()) {
            rolS.delete(id);
            return ResponseEntity.ok("Rol eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Rol no encontrado");
        }
    }
    // Filtro 1
    @GetMapping("/buscarnombre")
    public ResponseEntity<List<RolListDTO>> buscarPorNombre(@RequestParam String n) {
        ModelMapper m = new ModelMapper();
        List<RolListDTO> resultado = rolS.buscarPorNombre(n).stream()
                .map(r -> m.map(r, RolListDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultado);
    }

    // Filtro 2
    @GetMapping("/buscarporactivo")
    public ResponseEntity<List<RolListDTO>> buscarPorActivo(@RequestParam boolean activo) {
        ModelMapper m = new ModelMapper();
        List<RolListDTO> resultado = rolS.buscarPorActivo(activo).stream()
                .map(r -> m.map(r, RolListDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultado);
    }

    // Query
    @GetMapping("/usuariosporol")
    public ResponseEntity<List<Map<String, Object>>> usuariosPorRol() {
        return ResponseEntity.ok(rolS.contarUsuariosActivosPorRol());
    }
}
