package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.PlanSuscripcionDTO;
import pe.edu.upc.wasiseguro.entities.PlanSuscripcion;
import pe.edu.upc.wasiseguro.servicesinterfaces.IPlanSuscripcionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/planSuscripcion")
public class PlanSuscripcionController {

    @Autowired
    private IPlanSuscripcionService pS;

    @PostMapping("/registrar")
    public void registrar(@RequestBody PlanSuscripcionDTO dto) {
        ModelMapper m = new ModelMapper();
        PlanSuscripcion p = m.map(dto, PlanSuscripcion.class);
        if (p.getCreatedAt() == null) {
            p.setCreatedAt(java.time.LocalDateTime.now());
        }
        pS.insertar(p);
    }

    @GetMapping("/listar")
    public List<PlanSuscripcionDTO> listar() {
        return pS.listar().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, PlanSuscripcionDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PlanSuscripcionDTO listarId(@PathVariable("id") int id) {
        ModelMapper m = new ModelMapper();
        return m.map(pS.listarId(id), PlanSuscripcionDTO.class);
    }

    @PutMapping("/actualizar")
    public void actualizar(@RequestBody PlanSuscripcionDTO dto) {
        ModelMapper m = new ModelMapper();
        PlanSuscripcion p = m.map(dto, PlanSuscripcion.class);
        pS.actualizar(p);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") int id) {
        pS.eliminar(id);
    }
}