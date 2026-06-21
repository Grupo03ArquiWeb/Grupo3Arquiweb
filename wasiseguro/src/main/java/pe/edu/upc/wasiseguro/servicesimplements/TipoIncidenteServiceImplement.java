package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.dtos.TipoIncidentePorEstadoDTO;
import pe.edu.upc.wasiseguro.dtos.TipoIncidenteEstadisticasDTO;
import pe.edu.upc.wasiseguro.entities.TipoIncidente;
import pe.edu.upc.wasiseguro.repositories.ITipoIncidenteRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.ITipoIncidenteService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoIncidenteServiceImplement implements ITipoIncidenteService {

    @Autowired
    private ITipoIncidenteRepository tR;

    @Override
    public List<TipoIncidente> list() {
        return tR.findAll();
    }

    @Override
    public TipoIncidente insert(TipoIncidente t) {
        return tR.save(t);
    }

    @Override
    public Optional<TipoIncidente> listId(int id) {
        return tR.findById(id);
    }

    @Override
    public void update(TipoIncidente t) {
        tR.save(t);
    }

    @Override
    public void delete(int id) {
        tR.deleteById(id);
    }

    @Override
    public List<TipoIncidente> buscarPorNombre(String nombre) {
        return tR.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<TipoIncidente> listarActivos() {
        return tR.findByActivoTrue();
    }

    @Override
    public List<TipoIncidentePorEstadoDTO> cantidadPorEstado() {
        return tR.cantidadPorEstado();
    }

    @Override
    public List<TipoIncidenteEstadisticasDTO> estadisticasIncidentes() {
        List<Object[]> resultados = tR.estadisticasIncidentes();
        return resultados.stream().map(row -> {
            TipoIncidenteEstadisticasDTO dto = new TipoIncidenteEstadisticasDTO();
            dto.setNombre((String) row[0]);
            dto.setDescripcion((String) row[1]);
            dto.setCantidadIncidentes((Long) row[2]);
            dto.setIncidentesResueltos((Long) row[3]);
            dto.setIncidentesPendientes((Long) row[4]);
            return dto;
        }).collect(Collectors.toList());
    }
}