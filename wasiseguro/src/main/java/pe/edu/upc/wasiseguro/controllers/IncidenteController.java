package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.IncidenteCantidadDTO;
import pe.edu.upc.wasiseguro.dtos.IncidenteDTO;
import pe.edu.upc.wasiseguro.entities.Incidente;
import pe.edu.upc.wasiseguro.servicesinterfaces.IIncidenteService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/incidentes")
public class IncidenteController {
    @Autowired
    private IIncidenteService iS;

    @PostMapping("/crear")
    public void registrar(@RequestBody IncidenteDTO dto) {
        ModelMapper m = new ModelMapper();
        Incidente i = m.map(dto, Incidente.class);
        iS.insert(i);
    }

    @GetMapping("/listar")
    public List<IncidenteDTO> listar() {
        return iS.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, IncidenteDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") UUID id) { iS.delete(id); }

    @GetMapping("/buscarestado")
    public List<IncidenteDTO> buscarPorEstado(@RequestParam String e) {
        return iS.buscarPorEstado(e).stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, IncidenteDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/buscartipo")
    public List<IncidenteDTO> buscarPorTipo(@RequestParam String t) {
        return iS.buscarPorTipo(t).stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, IncidenteDTO.class);
        }).collect(Collectors.toList());
    }
    @GetMapping("/reporte-cantidades")
    public List<IncidenteCantidadDTO> obtenerReporte() {
        return iS.reporteCantidades();
    }
    @GetMapping("/reporte-usuarios")
    public List<IncidenteCantidadDTO> reportePorUsuarios() {
        return iS.reportePorUsuario(); // (Asegúrate de conectarlo en tu Service)
    }
}