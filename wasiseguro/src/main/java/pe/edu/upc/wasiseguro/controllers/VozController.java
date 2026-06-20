package pe.edu.upc.wasiseguro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.api17732.dtos.VozRequestDTO;
import pe.edu.upc.api17732.dtos.VozResponseDTO;
import pe.edu.upc.api17732.servicesinterfaces.IVozService;

@RestController
@RequestMapping("/api/voz")
public class VozController {

    @Autowired
    private IVozService vozService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/comando")
    public VozResponseDTO procesarComando(@RequestBody VozRequestDTO vozRequestDTO) {
        return vozService.procesarComando(vozRequestDTO.getTexto());
    }
}
