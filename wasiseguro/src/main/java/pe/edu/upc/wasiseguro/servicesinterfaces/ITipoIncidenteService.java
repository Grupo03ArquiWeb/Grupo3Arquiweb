package pe.edu.upc.wasiseguro.servicesinterfaces;

import pe.edu.upc.wasiseguro.dtos.TipoIncidentePorEstadoDTO;
import pe.edu.upc.wasiseguro.entities.TipoIncidente;

import java.util.List;
import java.util.Optional;

public interface ITipoIncidenteService {
    public List<TipoIncidente> list();
    public TipoIncidente insert(TipoIncidente t);
    public Optional<TipoIncidente> listId(int id);
    public void update(TipoIncidente t);
    public void delete(int id);
    public List<TipoIncidente> buscarPorNombre(String nombre);
    public List<TipoIncidente> listarActivos();
    public List<TipoIncidentePorEstadoDTO> cantidadPorEstado();
}