package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public void registrar(@RequestBody CalificacionRutaDTO dto) {
        ModelMapper m = new ModelMapper();
        CalificacionRuta c = m.map(dto, CalificacionRuta.class);
        cS.insert(c);
    }

    @GetMapping
    public List<CalificacionRutaDTO> listar() {
        ModelMapper m = new ModelMapper();
        return cS.list().stream().map(y -> m.map(y, CalificacionRutaDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/promedio-seguridad")
    public List<RutaSeguridadDTO> verPromedio() {
        return cS.obtenerPromedioSeguridad();
    }
}