package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.ZonaFavoritaDTO;
import pe.edu.upc.wasiseguro.entities.ZonaFavorita;
import pe.edu.upc.wasiseguro.entities.Usuario;
import pe.edu.upc.wasiseguro.servicesinterfaces.IZonaFavoritaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/zonas-favoritas")
public class ZonaFavoritaController {

    @Autowired
    private IZonaFavoritaService zS;

    @Autowired
    private pe.edu.upc.wasiseguro.repositories.IUsuarioRepository uR;

    @PostMapping
    public void registrar(@RequestBody ZonaFavoritaDTO dto) {
        if (dto.getIdUsuario() == null) {
            throw new RuntimeException("El ID de usuario no puede ser nulo");
        }

        ModelMapper m = new ModelMapper();
        ZonaFavorita z = m.map(dto, ZonaFavorita.class);
        Usuario u = uR.findById(dto.getIdUsuario()).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));
        z.setUsuario(u);

        if (z.getCreatedAt() == null) {
            z.setCreatedAt(java.time.LocalDateTime.now());
        }
        zS.insert(z);
    }

    @GetMapping
    public List<ZonaFavoritaDTO> listar() {
        ModelMapper m = new ModelMapper();
        return zS.list().stream().map(y -> {
            ZonaFavoritaDTO dto = m.map(y, ZonaFavoritaDTO.class);
            if (y.getUsuario() != null) {
                dto.setIdUsuario(y.getUsuario().getId());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @PutMapping
    public void modificar(@RequestBody ZonaFavoritaDTO dto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Usuario u = uR.findByEmail(email);

        ModelMapper m = new ModelMapper();
        ZonaFavorita z = m.map(dto, ZonaFavorita.class);

        z.setUsuario(u);

        zS.update(z);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        zS.delete(id);
    }
}