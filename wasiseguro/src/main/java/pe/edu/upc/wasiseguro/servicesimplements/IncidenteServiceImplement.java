package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.MessageSource;
import pe.edu.upc.wasiseguro.dtos.IncidenteCantidadDTO;
import pe.edu.upc.wasiseguro.entities.Incidente;
import pe.edu.upc.wasiseguro.entities.Usuario;
import pe.edu.upc.wasiseguro.repositories.IIncidenteRepository;
import pe.edu.upc.wasiseguro.repositories.IUsuarioRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IIncidenteService;

import java.time.LocalDateTime;
import java.util.Arrays;
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
        iR.save(incidente);
    }

    @Override
    public List<Incidente> list() {
        return iR.listarTodoOrdenado();
    }

    @Override
    public void delete(UUID idIncidente) {
        iR.deleteById(idIncidente);
    }

    @Override
    public List<Incidente> buscarPorEstado(String estado) {
        return iR.buscarPorEstado(estado);
    }

    @Override
    public List<Incidente> buscarPorTipo(String nombreTipo) {
        return iR.buscarPorTipo(nombreTipo);
    }

    @Override
    public List<Incidente> buscarPopulares(int minVotos) {
        return iR.buscarIncidentesMuyReportados(minVotos);
    }

    @Override
    public List<IncidenteCantidadDTO> reporteCantidades() {
        return iR.countIncidentesByType();
    }

    @Override
    public List<IncidenteCantidadDTO> reportePorUsuario() {
        return iR.reportePorUsuario();
    }

    @Override
    public Incidente findById(UUID id) {
        return iR.findById(id).orElse(new Incidente());
    }

    @Override
    public void deleteOwned(UUID idIncidente, String emailLogueado) {
        Incidente i = iR.findById(idIncidente)
                .orElseThrow(() -> new RuntimeException("Error: El incidente no existe."));
        if (i.getUsuario().getEmail().equals(emailLogueado)) {
            iR.deleteById(idIncidente);
        } else {
            throw new RuntimeException("Seguridad: No puedes borrar un reporte ajeno.");
        }
    }

    @Override
    public void updateOwned(Incidente incidente, String emailLogueado) {
        Incidente original = iR.findById(incidente.getId()).orElse(null);
        if (original != null) {
            if (original.getUsuario().getEmail().equals(emailLogueado)) {
                original.setDescripcion(incidente.getDescripcion());
                original.setFotoUrl(incidente.getFotoUrl());
                iR.save(original);
            } else {
                throw new RuntimeException("Seguridad: No puedes editar un reporte ajeno.");
            }
        }
    }

    @Override
    @Transactional
    public void votar(UUID idIncidente, String emailVotante, boolean esPositivo) {
        Incidente inc = iR.findById(idIncidente)
                .orElseThrow(() -> new RuntimeException("Incidente no encontrado"));

        if (inc.getUsuariosVotantes().contains(emailVotante)) {
            throw new RuntimeException("Error: Ya has votado en este reporte.");
        }

        inc.getUsuariosVotantes().add(emailVotante);

        if (esPositivo) {
            inc.setVotosValido(inc.getVotosValido() + 1);
        } else {
            inc.setVotosInvalido(inc.getVotosInvalido() + 1);
        }

        if (inc.getVotosInvalido() >= 3) {
            inc.setEstado("FALSO / VERIFICAR");
        }

        iR.save(inc);
    }

    @Override
    public void agregarComentario(UUID idIncidente, String texto, String emailAutor) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new RuntimeException("El comentario no puede estar vacío.");
        }

        List<String> insultos = Arrays.asList("ofensivo1", "ofensivo2");
        for (String s : insultos) {
            if (texto.toLowerCase().contains(s)) throw new RuntimeException("Lenguaje inapropiado.");
        }

        Incidente inc = iR.findById(idIncidente).orElseThrow(() -> new RuntimeException("Incidente no encontrado"));
        Usuario user = uR.findByEmail(emailAutor);

        inc.getComentarios().add(new Incidente.ComentarioEmbeddable(texto, user.getNombre()));
        iR.save(inc);
    }

    @Override
    public List<Incidente.ComentarioEmbeddable> listarComentarios(UUID idIncidente) {
        return iR.findById(idIncidente)
                .orElseThrow(() -> new RuntimeException("Incidente no encontrado"))
                .getComentarios();
    }

    @Override
    public String obtenerMensajeTraducido(Usuario usuario, String ubicacion) {
        String lang = (usuario.getIdioma() == null) ? "es" : usuario.getIdioma();
        Locale locale = new Locale(lang);
        return messageSource.getMessage("alerta.incidente", new Object[]{ubicacion}, locale);
    }
    @Override
    public boolean verificarSiEnviarAlerta(Usuario usuario) {
        if (usuario.getSilenciadoHasta() != null && java.time.LocalDateTime.now().isBefore(usuario.getSilenciadoHasta())) {
            return false;
        }
        return "INSTANTE".equalsIgnoreCase(usuario.getFrecuenciaAlertas());
    }

}