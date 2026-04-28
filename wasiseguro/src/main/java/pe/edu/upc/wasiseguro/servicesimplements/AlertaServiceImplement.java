package pe.edu.upc.wasiseguro.servicescomplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.entities.Alerta;
import pe.edu.upc.wasiseguro.repositories.IAlertaRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IAlertaService;

import java.util.List;
import java.util.UUID;

@Service
public class AlertaServiceImplement implements IAlertaService {
    @Autowired
    private IAlertaRepository aR;

    @Override
    public void insert(Alerta alerta) { aR.save(alerta); }

    @Override
    public List<Alerta> list() { return aR.findAll(); }

    @Override
    public void delete(UUID idAlerta) { aR.deleteById(idAlerta); }

    @Override
    public List<Alerta> buscarNoLeidas(UUID idUsuario) { return aR.buscarNoLeidasPorUsuario(idUsuario); }

    @Override
    public List<Alerta> buscarVigentes() { return aR.buscarAlertasVigentes(); }
}