package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.entities.Suscripcion;
import pe.edu.upc.wasiseguro.repositories.ISuscripcionRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.ISuscripcionService;

import java.util.List;
import java.util.Optional;

@Service
public class SuscripcionServiceImplement implements ISuscripcionService {

    @Autowired
    private ISuscripcionRepository sR;

    @Override
    public List<Suscripcion> list() { return sR.findAll(); }

    @Override
    public Suscripcion insert(Suscripcion s) { return sR.save(s); }

    @Override
    public Optional<Suscripcion> listId(int id) { return sR.findById(id); }

    @Override
    public void update(Suscripcion s) { sR.save(s); }

    @Override
    public void delete(int id) { sR.deleteById(id); }
}