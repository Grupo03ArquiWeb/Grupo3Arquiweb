package pe.edu.upc.wasiseguro.servicesinterfaces;

import pe.edu.upc.wasiseguro.entities.TipoIncidente;

import java.util.List;
import java.util.Optional;

public interface ITipoIncidenteService {
    public List<TipoIncidente> list();
    public TipoIncidente insert(TipoIncidente ti);
    public Optional<TipoIncidente> listId(int id);
    public void update(TipoIncidente ti);
    public void delete(int id);
}