package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.wasiseguro.dtos.NivelRiesgoListDTO;
import pe.edu.upc.wasiseguro.serviceinterface.INivelRiesgoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NivelRiesgoController {
    @Autowired
    private INivelRiesgoService nS;

    @GetMapping
    public ResponseEntity<List<NivelRiesgoListDTO>>listar(){
        ModelMapper m= new ModelMapper();
        List<NivelRiesgoListDTO>listaNiveles=nS.list().stream()
                .map(y->m.map(y,NivelRiesgoListDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaNiveles);
    }
}
