package pe.edu.upc.wasiseguro.servicecomplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.entities.ZonaRiesgo;
import pe.edu.upc.wasiseguro.repositories.IZonaRiesgoRepository;
import pe.edu.upc.wasiseguro.serviceinterface.IZonaRiesgoService;

import java.util.List;

@Service
public class ZonaRiesgoServiceImplement implements IZonaRiesgoService {
    @Autowired
    private IZonaRiesgoRepository zR;

    @Override
    public List<ZonaRiesgo> list() {
        return zR.findAll();
    }
}
