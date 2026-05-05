package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.UsuarioCreateDTO;
import pe.edu.upc.wasiseguro.dtos.UsuarioListDTO;
import pe.edu.upc.wasiseguro.dtos.UsuarioUpdateDTO;
import pe.edu.upc.wasiseguro.entities.Rol;
import pe.edu.upc.wasiseguro.entities.Usuario;
import pe.edu.upc.wasiseguro.repositories.IRolRepository;
import pe.edu.upc.wasiseguro.repositories.IUsuarioRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IUsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService userS;

    @Autowired
    private IUsuarioRepository userR;

    @Autowired
    private IRolRepository rolR;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("listar")
    public ResponseEntity<List<UsuarioListDTO>> listar(){
        ModelMapper m= new ModelMapper();
        List<UsuarioListDTO>listaUsers=userS.list().stream()
                .map(y->m.map(y, UsuarioListDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaUsers);
    }
    @PostMapping("/crear")
    public ResponseEntity<?> registrar(@RequestBody UsuarioCreateDTO dto){
        if (userR.existsByEmail(dto.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El email ya está registrado");
        }

        Optional<Rol> rolOpt = rolR.findById(dto.getIdRol());
        if (rolOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Rol no encontrado");
        }

        Usuario user = new Usuario();
        user.setNombre(dto.getNombre());
        user.setApellido(dto.getApellido());
        user.setEmail(dto.getEmail());
        user.setTelefono(dto.getTelefono());

        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user.setRol(rolOpt.get());

        user.setEmailVerificado(true);

        Usuario saved = userS.insert(user);

        ModelMapper m = new ModelMapper();
        UsuarioListDTO response = m.map(saved, UsuarioListDTO.class);
        response.setIdRol(saved.getRol().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable UUID id, @RequestBody UsuarioUpdateDTO dto)  {
        Optional<Usuario> existente = userS.listId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }
        Usuario user = existente.get();
        user.setNombre(dto.getNombre());
        user.setApellido(dto.getApellido());
        user.setEmail(dto.getEmail());
        user.setTelefono(dto.getTelefono());
        Optional<Rol> rolOpt = rolR.findById(dto.getIdRol());
        if (rolOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Rol no válido");
        }
        user.setRol(rolOpt.get());
        userS.update(user);
        return ResponseEntity.ok("Usuario actualizado correctamente");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable UUID id) {
        Optional<Usuario> user = userS.listId(id);
        if (user.isPresent()) {
            userS.delete(id);
            return ResponseEntity.ok("Usuario eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }
    }
    //Filtro 1
    @GetMapping("/buscarnombre")
    public List<UsuarioListDTO> buscarNombre(@RequestParam String n) {
        return userS.findByNombre(n).stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, UsuarioListDTO.class);
        }).collect(Collectors.toList());
    }
    //Filtro 2
    @GetMapping("/buscarporrol")
    public List<UsuarioListDTO> buscarPorRol(@RequestParam String r) {
        return userS.buscarPorRol(r).stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, UsuarioListDTO.class);
        }).collect(Collectors.toList());
    }
    //Filtro 3
    @GetMapping("/buscarpordominio")
    public List<UsuarioListDTO> buscarPorDominio(@RequestParam String d) {
        return userS.buscarPorDominio(d).stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, UsuarioListDTO.class);
        }).collect(Collectors.toList());
    }
    // Query
    // GET /api/usuario/inactivos?dias=30
    @GetMapping("/inactivos")
    public ResponseEntity<List<UsuarioListDTO>> buscarInactivos(@RequestParam int dias) {
        ModelMapper m = new ModelMapper();
        List<UsuarioListDTO> resultado = userS.buscarUsuariosInactivos(dias).stream()
                .map(y -> m.map(y, UsuarioListDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultado);
    }

}
