package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.*;
import pe.edu.upc.wasiseguro.entities.NivelRiesgo;
import pe.edu.upc.wasiseguro.entities.Ruta;
import pe.edu.upc.wasiseguro.entities.Usuario;
import pe.edu.upc.wasiseguro.repositories.INivelRiesgoRepository;
import pe.edu.upc.wasiseguro.repositories.IUsuarioRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IRutaService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ruta")
public class RutaController {

    @Autowired
    private IRutaService rutaS;

    @Autowired
    private IUsuarioRepository userR;

    @Autowired
    private INivelRiesgoRepository nR;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("listar")
    public ResponseEntity<List<RutaListDTO>> listar(){
        ModelMapper m= new ModelMapper();
        List<RutaListDTO>listaUsers=rutaS.list().stream()
                .map(y->m.map(y, RutaListDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaUsers);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/crear")
    public ResponseEntity<?> registrar(@RequestBody RutaUpdateDTO dto){
        ModelMapper m=new ModelMapper();
        Optional<Usuario> userOpt = userR.findById(dto.getIdUsuario());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no encontrado");
        }
        Optional<NivelRiesgo> nrOpt = nR.findById(dto.getIdNivelRiesgo());
        if (nrOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nivel de riesgo no válido");
        }
        Ruta c=m.map(dto, Ruta.class);
        c.setUsuario(userOpt.get());
        c.setNivelRiesgo(nrOpt.get());
        Ruta cur= rutaS.insert(c);
        RutaUpdateDTO responseDTO=m.map(cur, RutaUpdateDTO.class);
        responseDTO.setIdUsuario(cur.getUsuario().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable UUID id, @RequestBody RutaUpdateDTO dto)  {
        Optional<Ruta> existente = rutaS.listId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ruta no encontrada");
        }
        Ruta ruta = existente.get();
        Optional<Usuario> userOpt = userR.findById(dto.getIdUsuario());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no válido");
        }
        ruta.setUsuario(userOpt.get());
        ruta.setOrigenLat(dto.getOrigenLat());
        ruta.setOrigenLng(dto.getOrigenLng());
        ruta.setDestinoLat(dto.getDestinoLat());
        ruta.setDestinoLng(dto.getDestinoLng());
        ruta.setNombreOrigen(dto.getNombreOrigen());
        ruta.setNombreDestino(dto.getNombreDestino());
        ruta.setDistanciaKm(dto.getDistanciaKm());
        ruta.setDuracionMin(dto.getDuracionMin());
        Optional<NivelRiesgo> nrOpt = nR.findById(dto.getIdNivelRiesgo());
        if (nrOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nivel de riesgo no válido");
        }
        ruta.setNivelRiesgo(nrOpt.get());
        ruta.setGeojsonTrayecto(dto.getGeojsonTrayecto());
        ruta.setEsPublica(dto.isEsPublica());
        ruta.setEsFavorita(dto.isEsFavorita());
        rutaS.update(ruta);
        return ResponseEntity.ok("Ruta actualizada correctamente");
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable UUID id) {
        Optional<Ruta> ruta = rutaS.listId(id);
        if (ruta.isPresent()) {
            rutaS.delete(id);
            return ResponseEntity.ok("Ruta eliminada exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ruta no encontrada");
        }
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/favoritas")
    public List<RutasFavoritasDTO> listarFavoritas() {
        return rutaS.listFavoritas();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/sugerencias-seguras")
    public ResponseEntity<List<RutaSugeridaDTO>> obtenerSugerenciasSeguras() {
        List<RutaSugeridaDTO> sugerencias = rutaS.sugerirRutasSeguras();
        return ResponseEntity.ok(sugerencias);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/listar/usuario/{idUsuario}")
    public ResponseEntity<List<Ruta>> listarPorUsuario(@PathVariable UUID idUsuario) {
        return ResponseEntity.ok(rutaS.listByUsuario(idUsuario));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/alternativas")
    public ResponseEntity<List<RutaSugeridaDTO>> obtenerAlternativas(@RequestParam double destLat, @RequestParam double destLng) {
        List<RutaSugeridaDTO> lista = rutaS.buscarRutasAlternativas(destLat, destLng);
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/comparar-costo-tiempo")
    public ResponseEntity<?> compararTiempos(@RequestParam double lat, @RequestParam double lng) {
        RutaComparacionDTO comparacion = rutaS.compararTiempos(lat, lng);
        if (comparacion == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Se necesitan al menos 2 rutas para comparar.");
        }
        return ResponseEntity.ok(comparacion);
    }
}