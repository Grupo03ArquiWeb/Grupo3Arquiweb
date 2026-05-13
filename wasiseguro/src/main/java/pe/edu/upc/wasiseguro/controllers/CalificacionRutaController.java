package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.CalificacionRutaDTO;
import pe.edu.upc.wasiseguro.dtos.RutaSeguridadDTO;
import pe.edu.upc.wasiseguro.entities.CalificacionRuta;
import pe.edu.upc.wasiseguro.servicesinterfaces.ICalificacionRutaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/calificaciones-rutas")
public class CalificacionRutaController {
    @Autowired
    private ICalificacionRutaService cS;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping
    public void registrar(@RequestBody CalificacionRutaDTO dto) {
        ModelMapper m = new ModelMapper();
        CalificacionRuta c = m.map(dto, CalificacionRuta.class);
        cS.insert(c);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public List<CalificacionRutaDTO> listar() {
        ModelMapper m = new ModelMapper();
        return cS.list().stream().map(y -> m.map(y, CalificacionRutaDTO.class)).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/promedio-seguridad")
    public List<RutaSeguridadDTO> verPromedio() {
        return cS.obtenerPromedioSeguridad();
    }
}