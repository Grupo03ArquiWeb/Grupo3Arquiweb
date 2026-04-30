package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.*;
import pe.edu.upc.wasiseguro.entities.*;
import pe.edu.upc.wasiseguro.repositories.IUsuarioRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IEventoPanicoService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/EventoPanico")
public class EventoPanicoController {

    @Autowired
    private IEventoPanicoService epS;

    @Autowired
    private IUsuarioRepository userR;

    @GetMapping("listar")
    public ResponseEntity<List<EventoPanicoListDTO>> listar(){
        ModelMapper m= new ModelMapper();
        List<EventoPanicoListDTO>listaUsers=epS.list().stream()
                .map(y->m.map(y, EventoPanicoListDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaUsers);
    }
    @PostMapping("/crear")
    public ResponseEntity<?> registrar(@RequestBody EventoPanicoListDTO dto){

        ModelMapper m=new ModelMapper();
        EventoPanico c=m.map(dto, EventoPanico.class);
        Optional<Usuario> userOpt = userR.findById(dto.getIdUsuario());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Usuario no válido");
        }
        c.setUsuario(userOpt.get());
        EventoPanico cur= epS.insert(c);
        EventoPanicoListDTO responseDTO=m.map(cur, EventoPanicoListDTO.class);
        responseDTO.setIdUsuario(cur.getUsuario().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);


    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable UUID id, @RequestBody EventoPanicoListDTO dto)  {
        Optional<EventoPanico> existente = epS.listId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Evento no encontrado");
        }
        EventoPanico eventP = existente.get();
        Optional<Usuario> userOpt = userR.findById(dto.getId());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Usuario no válido");
        }
        eventP.setUsuario(userOpt.get());
        eventP.setLatitud(dto.getLatitud());
        eventP.setLongitud(dto.getLongitud());
        eventP.setMensajeExtra(dto.getMensajeExtra());
        epS.update(eventP);
        return ResponseEntity.ok("Evento de Pánico actualizado correctamente");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable UUID id) {
        Optional<EventoPanico> user = epS.listId(id);
        if (user.isPresent()) {
            epS.delete(id);
            return ResponseEntity.ok("Usuario eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }
    }
}
