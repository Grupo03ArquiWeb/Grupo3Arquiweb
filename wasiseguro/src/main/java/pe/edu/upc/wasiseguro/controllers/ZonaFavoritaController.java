package pe.edu.upc.wasiseguro.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.wasiseguro.dtos.ZonaFavoritaDTO;
import pe.edu.upc.wasiseguro.entities.ZonaFavorita;
import pe.edu.upc.wasiseguro.servicesinterfaces.IZonaFavoritaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/zonas-favoritas")
public class ZonaFavoritaController {
    @Autowired
    private IZonaFavoritaService zS;

    @PostMapping
    public void registrar(@RequestBody ZonaFavoritaDTO dto) {
        ModelMapper m = new ModelMapper();
        ZonaFavorita z = m.map(dto, ZonaFavorita.class);
        zS.insert(z);
    }

    @GetMapping
    public List<ZonaFavoritaDTO> listar() {
        ModelMapper m = new ModelMapper();
        return zS.list().stream().map(y -> m.map(y, ZonaFavoritaDTO.class)).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        zS.delete(id);
    }
}