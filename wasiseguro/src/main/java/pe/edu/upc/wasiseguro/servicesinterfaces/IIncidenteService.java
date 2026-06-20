package pe.edu.upc.wasiseguro.servicesinterfaces;

import pe.edu.upc.wasiseguro.dtos.IncidenteCantidadDTO;
import pe.edu.upc.wasiseguro.dtos.IncidenteRankingDTO;
import pe.edu.upc.wasiseguro.entities.Incidente;
import pe.edu.upc.wasiseguro.entities.Usuario;

import java.util.List;
import java.util.UUID;

public interface IIncidenteService {
    void insert(Incidente incidente);
    List<Incidente> list();
    void delete(UUID idIncidente);
    List<Incidente> buscarPorEstado(String estado);
    List<Incidente> buscarPorTipo(String nombreTipo);
    List<Incidente> buscarPopulares(int minVotos);
    List<IncidenteCantidadDTO> reporteCantidades();
    List<IncidenteRankingDTO> reportePorUsuario();
    void deleteOwned(UUID idIncidente, String emailLogueado);
    void updateOwned(Incidente incidente, String emailLogueado);
    Incidente findById(UUID id);
    void votar(UUID idIncidente, String emailVotante, boolean esPositivo);
    void agregarComentario(UUID idIncidente, String texto, String emailAutor);
    List<Incidente.ComentarioEmbeddable> listarComentarios(UUID idIncidente);
    String obtenerMensajeTraducido(Usuario usuario, String ubicacion);
    boolean verificarSiEnviarAlerta(Usuario usuario);
}