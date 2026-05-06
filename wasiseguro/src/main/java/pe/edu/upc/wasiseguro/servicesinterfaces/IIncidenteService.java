package pe.edu.upc.wasiseguro.servicesinterfaces;

import pe.edu.upc.wasiseguro.dtos.IncidenteCantidadDTO;
import pe.edu.upc.wasiseguro.entities.Incidente;

import java.util.List;
import java.util.UUID;

public interface IIncidenteService {
    public void insert(Incidente incidente);
    public List<Incidente> list();
    public void delete(UUID idIncidente);
    public List<Incidente> buscarPorEstado(String estado);
    public List<Incidente> buscarPorTipo(String nombreTipo);
    public List<Incidente> buscarPopulares(int minVotos);
    public List<IncidenteCantidadDTO> reporteCantidades();
    public List<IncidenteCantidadDTO> reportePorUsuario();
    public void deleteOwned(UUID idIncidente, String emailLogueado);
    public void updateOwned(Incidente incidente, String emailLogueado);
    public Incidente findById(UUID id);
    public void votar(UUID idIncidente, String emailVotante, boolean esPositivo);
}