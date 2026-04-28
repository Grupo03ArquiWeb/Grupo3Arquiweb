package pe.edu.upc.wasiseguro.servicesinterfaces;

import pe.edu.upc.wasiseguro.entities.Alerta;
import java.util.List;
import java.util.UUID;

public interface IAlertaService {
    public void insert(Alerta alerta);
    public List<Alerta> list();
    public void delete(UUID idAlerta);
    public List<Alerta> buscarNoLeidas(UUID idUsuario);
    public List<Alerta> buscarVigentes();
}