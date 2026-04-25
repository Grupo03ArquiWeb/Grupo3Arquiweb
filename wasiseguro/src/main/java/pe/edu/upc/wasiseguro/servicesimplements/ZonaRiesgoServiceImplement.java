package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.entities.ZonaRiesgo;
import pe.edu.upc.wasiseguro.repositories.IZonaRiesgoRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IZonaRiesgoService;

import java.util.List;
import java.util.Optional;

@Service
public class ZonaRiesgoServiceImplement implements IZonaRiesgoService {
    @Autowired
    private IZonaRiesgoRepository zR;

    @Override
    public List<ZonaRiesgo> list() {
        return zR.findAll();
    }

    @Override
    public ZonaRiesgo insert(ZonaRiesgo zr) {
        return zR.save(zr);
    }

    @Override
    public Optional<ZonaRiesgo> listId(int id)    {
        return zR.findById(id);
    }

    @Override
    public void update(ZonaRiesgo zr)  {
        zR.save(zr);
    }

    @Override
    public void delete(int id)   {
        zR.deleteById(id);
    }
}