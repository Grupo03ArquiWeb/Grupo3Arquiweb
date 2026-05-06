package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.dtos.IncidenteCantidadDTO;
import pe.edu.upc.wasiseguro.entities.Incidente;
import pe.edu.upc.wasiseguro.repositories.IIncidenteRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IIncidenteService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class IncidenteServiceImplement implements IIncidenteService {
    @Autowired
    private IIncidenteRepository iR;

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
                .orElseThrow(() -> new RuntimeException("Error: El incidente con ID " + idIncidente + " no existe."));
        if (i.getUsuario().getEmail().equals(emailLogueado)) {
            iR.deleteById(idIncidente);
        } else {
            throw new RuntimeException("Seguridad: No puedes borrar un reporte que no te pertenece.");
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
                throw new RuntimeException("Seguridad: No puedes editar un reporte que no te pertenece.");
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
}