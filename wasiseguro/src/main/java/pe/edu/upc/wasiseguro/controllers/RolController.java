package pe.edu.upc.wasiseguro.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.RolDTO;
import pe.edu.upc.wasiseguro.entities.Rol;
import pe.edu.upc.wasiseguro.servicesinterfaces.IRolService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rol")
public class RolController {
    @Autowired
    private IRolService rolS;

    @GetMapping("listar")
    public ResponseEntity<List<RolDTO>> listar(){
        ModelMapper m= new ModelMapper();
        List<RolDTO>listarRoles=rolS.list().stream()
                .map(y->m.map(y, RolDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listarRoles);
    }
    @PostMapping("/crear")
    public ResponseEntity<?> registrar(@RequestBody RolDTO dto){
        ModelMapper m=new ModelMapper();
        Rol c=m.map(dto, Rol.class);
        Rol cur= rolS.insert(c);
        RolDTO responseDTO=m.map(cur,RolDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
