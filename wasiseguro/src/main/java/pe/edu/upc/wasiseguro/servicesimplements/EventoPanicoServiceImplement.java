package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import pe.edu.upc.wasiseguro.entities.EventoPanico;
import pe.edu.upc.wasiseguro.repositories.IEventoPanicoRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IEventoPanicoService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EventoPanicoServiceImplement implements IEventoPanicoService {

    @Autowired
    private IEventoPanicoRepository epR;

    @Override
    public List<EventoPanico> list() {
         return epR.findAll();
    }

    @Override
    public EventoPanico insert(EventoPanico ep) {
        return epR.save(ep);
    }

    @Override
    public Optional<EventoPanico> listId(UUID id) {
        return epR.findById(id);
    }

    @Override
    public void update(EventoPanico ep) {
        epR.save(ep);
    }

    @Override
    public void delete(UUID id) {
        epR.deleteById(id);
    }
}
