package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.MessageSource;
import pe.edu.upc.wasiseguro.dtos.IncidenteCantidadDTO;
import pe.edu.upc.wasiseguro.dtos.IncidenteRankingDTO;
import pe.edu.upc.wasiseguro.entities.Incidente;
import pe.edu.upc.wasiseguro.entities.Usuario;
import pe.edu.upc.wasiseguro.repositories.IIncidenteRepository;
import pe.edu.upc.wasiseguro.repositories.IUsuarioRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IIncidenteService;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class IncidenteServiceImplement implements IIncidenteService {
    @Autowired
    private IIncidenteRepository iR;
    @Autowired
    private IUsuarioRepository uR;
    @Autowired
    private MessageSource messageSource;

    @Override
    public void insert(Incidente incidente) {
        if (incidente.getEstado() == null || incidente.getEstado().isEmpty()) {
            incidente.setEstado("PENDIENTE");
        }
        if (incidente.getFechaOcurrido() == null) {
            incidente.setFechaOcurrido(java.time.OffsetDateTime.now());
        }
        iR.save(incidente);
    }

    @Override
    public List<Incidente> list() { return iR.findAllByOrderByFechaOcurridoDesc(); }

    @Override
    public void delete(UUID idIncidente) { iR.deleteById(idIncidente); }

    @Override
    public List<Incidente> buscarPorEstado(String estado) { return iR.findByEstado(estado); }

    @Override
    public List<Incidente> buscarPorTipo(String nombreTipo) { return iR.findByTipoIncidenteNombre(nombreTipo); }

    @Override
    public List<Incidente> buscarPopulares(int minVotos) { return iR.findByVotosValidoGreaterThan(minVotos); }

    @Override
    public List<IncidenteCantidadDTO> reporteCantidades() { return iR.countIncidentesByType(); }

    @Override
    public List<IncidenteRankingDTO> reportePorUsuario() { return iR.obtenerRankingUsuarios(); }

    @Override
    public Incidente findById(UUID id) { return iR.findById(id).orElse(new Incidente()); }

    @Override
    public void deleteOwned(UUID idIncidente, String emailLogueado) {
        Incidente i = iR.findById(idIncidente).orElseThrow(() -> new RuntimeException("Error"));
        if (i.getUsuario().getEmail().equals(emailLogueado)) { iR.deleteById(idIncidente); }
        else { throw new RuntimeException("Seguridad"); }
    }

    @Override
    public void updateOwned(Incidente incidente, String emailLogueado) {
        Incidente original = iR.findById(incidente.getId()).orElse(null);
        if (original != null && original.getUsuario().getEmail().equals(emailLogueado)) {
            original.setDescripcion(incidente.getDescripcion());
            original.setFotoUrl(incidente.getFotoUrl());
            original.setEstado(incidente.getEstado());
            original.setLatitud(incidente.getLatitud());
            original.setLongitud(incidente.getLongitud());
            iR.save(original);
        }
    }

    @Override
    @Transactional
    public void votar(UUID idIncidente, String emailVotante, boolean esPositivo) {
        Incidente inc = iR.findById(idIncidente).orElseThrow(() -> new RuntimeException("Error"));
        if (!inc.getUsuariosVotantes().contains(emailVotante)) {
            inc.getUsuariosVotantes().add(emailVotante);
            if (esPositivo) inc.setVotosValido(inc.getVotosValido() + 1);
            else inc.setVotosInvalido(inc.getVotosInvalido() + 1);

            if (inc.getVotosInvalido() >= 3) inc.setEstado("PENDIENTE");
            iR.save(inc);
        }
    }

    @Override
    public void agregarComentario(UUID idIncidente, String texto, String emailAutor) {
        Incidente inc = iR.findById(idIncidente).orElseThrow(() -> new RuntimeException("Error"));
        Usuario user = uR.findByEmail(emailAutor);
        inc.getComentarios().add(new Incidente.ComentarioEmbeddable(texto, user.getNombre()));
        iR.save(inc);
    }

    @Override
    public List<Incidente.ComentarioEmbeddable> listarComentarios(UUID idIncidente) {
        return iR.findById(idIncidente).orElseThrow(() -> new RuntimeException("Error")).getComentarios();
    }

    @Override
    public String obtenerMensajeTraducido(Usuario usuario, String ubicacion) {
        String lang = (usuario.getIdioma() == null) ? "es" : usuario.getIdioma();
        return messageSource.getMessage("alerta.incidente", new Object[]{ubicacion}, new Locale(lang));
    }

    @Override
    public boolean verificarSiEnviarAlerta(Usuario usuario) {
        if (usuario.getSilenciadoHasta() != null && java.time.LocalDateTime.now().isBefore(usuario.getSilenciadoHasta())) return false;
        return "INSTANTE".equalsIgnoreCase(usuario.getFrecuenciaAlertas());
    }
}