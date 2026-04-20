package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.UsuarioCreateDTO;
import pe.edu.upc.wasiseguro.dtos.UsuarioDTO;
import pe.edu.upc.wasiseguro.entities.Usuario;
import pe.edu.upc.wasiseguro.servicesinterfaces.IUsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService userS;

    @GetMapping("listar")
    public ResponseEntity<List<UsuarioDTO>> listar(){
        ModelMapper m= new ModelMapper();
        List<UsuarioDTO>listaUsers=userS.list().stream()
                .map(y->m.map(y, UsuarioDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaUsers);
    }
    @PostMapping("/crear")
    public ResponseEntity<?> registrar(@RequestBody UsuarioCreateDTO dto){
        ModelMapper m=new ModelMapper();
        Usuario c=m.map(dto, Usuario.class);
        c.setPasswordHash(dto.getPassword());
        Usuario cur= userS.insert(c);
        UsuarioDTO responseDTO=m.map(cur,UsuarioDTO.class);
        responseDTO.setRolNombre(cur.getRol().getNombre());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
