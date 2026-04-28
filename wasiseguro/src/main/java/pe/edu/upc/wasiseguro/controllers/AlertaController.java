package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.AlertaDTO;
import pe.edu.upc.wasiseguro.entities.Alerta;
import pe.edu.upc.wasiseguro.servicesinterfaces.IAlertaService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alertas")
public class AlertaController {
    @Autowired
    private IAlertaService aS;

    @PostMapping
    public void registrar(@RequestBody AlertaDTO dto) {
        ModelMapper m = new ModelMapper();
        Alerta a = m.map(dto, Alerta.class);
        aS.insert(a);
    }

    @GetMapping
    public List<AlertaDTO> listar() {
        return aS.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, AlertaDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") UUID id) { aS.delete(id); }

    @GetMapping("/noleidas")
    public List<AlertaDTO> buscarNoLeidas(@RequestParam UUID u) {
        return aS.buscarNoLeidas(u).stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, AlertaDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/vigentes")
    public List<AlertaDTO> buscarVigentes() {
        return aS.buscarVigentes().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, AlertaDTO.class);
        }).collect(Collectors.toList());
    }
}