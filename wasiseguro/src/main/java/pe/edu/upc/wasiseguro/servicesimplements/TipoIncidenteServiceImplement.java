package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.entities.TipoIncidente;
import pe.edu.upc.wasiseguro.repositories.ITipoIncidenteRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.ITipoIncidenteService;

import java.util.List;
import java.util.Optional;

@Service
public class TipoIncidenteServiceImplement implements ITipoIncidenteService {

    @Autowired
    private ITipoIncidenteRepository tR;

    @Override
    public List<TipoIncidente> list() {
        return tR.findAll();
    }

    @Override
    public TipoIncidente insert(TipoIncidente ti) {
        return tR.save(ti);
    }

    @Override
    public Optional<TipoIncidente> listId(int id) {
        return tR.findById(id);
    }

    @Override
    public void update(TipoIncidente ti) {
        tR.save(ti);
    }

    @Override
    public void delete(int id) {
        tR.deleteById(id);
    }
}