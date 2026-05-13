package pe.edu.upc.wasiseguro.serviceimplement;

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

    @Override
    public void insert(ZonaRiesgo zonaRiesgo) {

    }

    @Override
    public ZonaRiesgo listId(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<ZonaRiesgo> listarActivas() {
        return zR.findByActivoZonaRiesgoTrue();
    }

    @Override
    public List<String[]> cantidadZonasPorNivel() {
        return zR.cantidadZonasPorNivel();
    }

    @Override
    public List<String[]> zonasActivasOrdenadasPorRiesgoHora() {
        return zR.zonasActivasOrdenadasPorRiesgoHora();
    }

}
