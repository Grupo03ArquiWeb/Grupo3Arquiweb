package pe.edu.upc.wasiseguro.servicesinterfaces;

import pe.edu.upc.wasiseguro.entities.Ruta;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IRutaService {
    public List<Ruta> list();
    public Ruta insert(Ruta ruta);
    public Optional<Ruta> listId(UUID id);
    public void update(Ruta r);
    public void delete(UUID id);

}
