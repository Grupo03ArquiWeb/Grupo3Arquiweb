package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.dtos.EventoRecienteDTO;
import pe.edu.upc.wasiseguro.dtos.ResumenEventoPanicoDTO;
import pe.edu.upc.wasiseguro.entities.EventoPanico;
import pe.edu.upc.wasiseguro.repositories.IEventoPanicoRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IEventoPanicoService;

import java.util.*;

@Service
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

    // Filtros
    @Override
    public List<EventoPanico> buscarPorAtendido(boolean atendido) {
        return epR.findByAtendido(atendido);
    }

    @Override
    public List<EventoPanico> buscarPorUsuario(UUID idUsuario) {
        return epR.findByUsuarioId(idUsuario);
    }

    @Override
    public List<ResumenEventoPanicoDTO> resumenEventosPorUsuario() {
        return epR.resumenEventosPorUsuario();
    }

    @Override
    public List<EventoRecienteDTO> eventosRecientesConDatosUsuario(int limite) {
        return epR.eventosRecientesConDatosUsuario(limite);
    }

}
