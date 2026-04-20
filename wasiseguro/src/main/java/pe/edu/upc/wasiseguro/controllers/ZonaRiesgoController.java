package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.wasiseguro.dtos.ZonaRiesgoDTO;
import pe.edu.upc.wasiseguro.serviceinterface.IZonaRiesgoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("ZonaRiesgo")
public class ZonaRiesgoController {
    @Autowired
    private IZonaRiesgoService zS;

    @GetMapping
    public ResponseEntity<List<ZonaRiesgoDTO>>listar(){
        ModelMapper z= new ModelMapper();
        List<ZonaRiesgoDTO>listaZonas =zS.list().stream()
                .map(y->z.map(y,ZonaRiesgoDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaZonas);

    }
}