package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.wasiseguro.dtos.NivelRiesgoDTO;
import pe.edu.upc.wasiseguro.servicesinterfaces.INivelRiesgoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/NivelRiesgo")
public class NivelRiesgoController {
    @Autowired
    private INivelRiesgoService nS;

    @GetMapping("listar")
    public ResponseEntity<List<NivelRiesgoDTO>>listar(){
        ModelMapper m= new ModelMapper();
        List<NivelRiesgoDTO>listaNiveles=nS.list().stream()
                .map(y->m.map(y, NivelRiesgoDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaNiveles);
    }
}
