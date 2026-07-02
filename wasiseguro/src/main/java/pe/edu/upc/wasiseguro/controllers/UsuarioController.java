package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.*;
import pe.edu.upc.wasiseguro.entities.Rol;
import pe.edu.upc.wasiseguro.entities.Usuario;
import pe.edu.upc.wasiseguro.repositories.IRolRepository;
import pe.edu.upc.wasiseguro.repositories.IUsuarioRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IUsuarioService;

import java.time.OffsetDateTime;
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

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("listar")
    public ResponseEntity<List<UsuarioListDTO>> listar(){
        ModelMapper m= new ModelMapper();
        List<UsuarioListDTO>listaUsers=userS.list().stream()
                .map(y->{
                    UsuarioListDTO dto = m.map(y, UsuarioListDTO.class);
                    dto.setIdRol(y.getRol().getId());
                    if(y.getContactoConfianza()!=null){
                        dto.setContactoConfianza(y.getContactoConfianza().getId());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaUsers);
    }
    // US01 - Registro básico por correo
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
    // US06 - Actualizar datos básicos + foto de perfil
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERADOR', 'ROLE_USER')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable UUID id, @RequestBody UsuarioUpdateDTO dto)  {
        Optional<Usuario> existente = userS.listId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }
        Usuario user = existente.get();

        if (!user.getEmail().equalsIgnoreCase(dto.getEmail()) && userR.existsByEmail(dto.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El email ya está registrado en otro usuario");
        }
        user.setNombre(dto.getNombre());
        user.setApellido(dto.getApellido());
        user.setEmail(dto.getEmail());
        user.setTelefono(dto.getTelefono());
        user.setFotoPerfil(dto.getFotoPerfil());
        user.setIdioma(dto.getIdioma());
        Optional<Rol> rolOpt = rolR.findById(dto.getIdRol());
        if (rolOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Rol no válido");
        }
        user.setRol(rolOpt.get());
        userS.update(user);
        return ResponseEntity.ok("Usuario actualizado correctamente");
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
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
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERADOR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable UUID id) {
        Optional<Usuario> usr = userS.listId(id);

        if (usr.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No encontrado");
        }

        ModelMapper m = new ModelMapper();
        Usuario usuario = usr.get();

        UsuarioListDTO dto = m.map(usuario, UsuarioListDTO.class);

        dto.setIdRol(usuario.getRol().getId());

        if (usuario.getContactoConfianza() != null) {
            dto.setContactoConfianza(usuario.getContactoConfianza().getId());
        }

        return ResponseEntity.ok(dto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERADOR')")
    @GetMapping("/estadisticas/estado")
    public ResponseEntity<List<UsuarioEstadisticaEstadoDTO>> estadisticasPorEstado() {
        return ResponseEntity.ok(userS.estadisticasPorEstado());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERADOR')")
    @GetMapping("/estadisticas/idioma")
    public ResponseEntity<List<UsuarioEstadisticaIdiomaDTO>> estadisticasPorIdioma() {
        return ResponseEntity.ok(userS.estadisticasPorIdioma());
    }

    //Filtro 1
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERADOR')")
    @GetMapping("/buscarnombre")
    public List<UsuarioListDTO> buscarNombre(@RequestParam String n) {
        return userS.findByNombre(n).stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, UsuarioListDTO.class);
        }).collect(Collectors.toList());
    }
    //Filtro 2
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERADOR')")
    @GetMapping("/buscarporrol")
    public List<UsuarioListDTO> buscarPorRol(@RequestParam String r) {
        return userS.buscarPorRol(r).stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, UsuarioListDTO.class);
        }).collect(Collectors.toList());
    }
    //Filtro 3
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERADOR')")
    @GetMapping("/buscarpordominio")
    public List<UsuarioListDTO> buscarPorDominio(@RequestParam String d) {
        return userS.buscarPorDominio(d).stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, UsuarioListDTO.class);
        }).collect(Collectors.toList());
    }
    // US48 - Usuarios inactivos
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERADOR')")
    @GetMapping("/inactivos")
    public ResponseEntity<List<UsuarioListDTO>> buscarInactivos(@RequestParam int dias) {
        ModelMapper m = new ModelMapper();
        List<UsuarioListDTO> resultado = userS.buscarUsuariosInactivos(dias).stream()
                .map(y -> m.map(y, UsuarioListDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultado);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERADOR', 'ROLE_USER')")
    @PatchMapping("/{id}/cambiar-idioma")
    public ResponseEntity<String> cambiarIdioma(@PathVariable UUID id, @RequestParam String nuevoIdioma) {
        Optional<Usuario> userOpt = userS.listId(id);
        if (userOpt.isPresent()) {
            Usuario user = userOpt.get();
            user.setIdioma(nuevoIdioma);
            userS.update(user);
            return ResponseEntity.ok("Idioma actualizado a: " + nuevoIdioma);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERADOR', 'ROLE_USER')")
    @PatchMapping("/{id}/configurar-alertas")
    public ResponseEntity<String> configurarAlertas(
            @PathVariable UUID id,
            @io.swagger.v3.oas.annotations.Parameter(description = "Opciones: INSTANTE, HORA, DIARIO")
            @RequestParam String frecuencia,
            @RequestParam int minutosSilencio) {
        Usuario u = userR.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        u.setFrecuenciaAlertas(frecuencia.toUpperCase());
        u.setSilenciadoHasta(java.time.LocalDateTime.now().plusMinutes(minutosSilencio));
        userR.save(u);

        return ResponseEntity.ok("✅ Preferencias actualizadas.");
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERADOR', 'ROLE_USER')")
    @PatchMapping("/{id}/contacto-confianza")
    public ResponseEntity<String> asignarContactoConfianza(
            @PathVariable UUID id,
            @RequestParam UUID idContacto) {

        if (id.equals(idContacto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Un usuario no puede ser su propio contacto de confianza");
        }
        Optional<Usuario> usuarioOpt = userS.listId(id);
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
        Optional<Usuario> contactoOpt = userS.listId(idContacto);
        if (contactoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contacto no encontrado");
        }
        Usuario usuario = usuarioOpt.get();
        usuario.setContactoConfianza(contactoOpt.get());
        usuario.setUpdatedAt(OffsetDateTime.now());
        userS.update(usuario);
        return ResponseEntity.ok("Contacto de confianza asignado correctamente");
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERADOR')")
    @GetMapping("/{id}/contacto-confianza")
    public ResponseEntity<?> obtenerContactoConfianza(@PathVariable UUID id) {
        Optional<Usuario> usuarioOpt = userS.listId(id);
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
        Usuario contacto = usuarioOpt.get().getContactoConfianza();
        if (contacto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El usuario no tiene contacto de confianza asignado");
        }
        ModelMapper m = new ModelMapper();
        return ResponseEntity.ok(m.map(contacto, UsuarioListDTO.class));
    }

}
