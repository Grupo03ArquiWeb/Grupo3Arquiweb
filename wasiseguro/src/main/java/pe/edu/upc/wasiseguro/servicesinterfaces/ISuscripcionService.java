package pe.edu.upc.wasiseguro.servicesinterfaces;

import pe.edu.upc.wasiseguro.entities.Suscripcion;
import java.util.List;
import java.util.Optional;

public interface ISuscripcionService {
    public List<Suscripcion> list();
    public Suscripcion insert(Suscripcion s);
    public Optional<Suscripcion> listId(int id);
    public void update(Suscripcion s);
    public void delete(int id);
}