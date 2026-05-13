package pe.edu.upc.wasiseguro.servicesinterfaces;

import pe.edu.upc.wasiseguro.entities.EventoPanico;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IEventoPanicoService {
    public List<EventoPanico> list();
    public EventoPanico insert(EventoPanico ep);
    public Optional<EventoPanico> listId(UUID id);
    public void update(EventoPanico ep);
    public void delete(UUID id);

    // Filtros
    List<EventoPanico> buscarPorAtendido(boolean atendido);
    List<EventoPanico> buscarPorUsuario(UUID idUsuario);
}
