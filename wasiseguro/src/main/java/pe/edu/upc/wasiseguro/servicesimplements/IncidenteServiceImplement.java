package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.entities.Incidente;
import pe.edu.upc.wasiseguro.repositories.IIncidenteRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IIncidenteService;

import java.util.List;
import java.util.UUID;

@Service
public class IncidenteServiceImplement implements IIncidenteService {
    @Autowired
    private IIncidenteRepository iR;

    @Override
    public void insert(Incidente incidente) { iR.save(incidente); }

    @Override
    public List<Incidente> list() { return iR.findAll(); }

    @Override
    public void delete(UUID idIncidente) { iR.deleteById(idIncidente); }

    @Override
    public List<Incidente> buscarPorEstado(String estado) { return iR.buscarPorEstado(estado); }

    @Override
    public List<Incidente> buscarPorTipo(String nombreTipo) { return iR.buscarPorTipo(nombreTipo); }

    @Override
    public List<Incidente> buscarPopulares(int minVotos) { return iR.buscarIncidentesMuyReportados(minVotos); }
}
