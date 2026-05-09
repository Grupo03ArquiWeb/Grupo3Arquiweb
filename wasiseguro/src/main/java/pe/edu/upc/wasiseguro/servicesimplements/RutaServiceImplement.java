package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.dtos.RutaComparacionDTO;
import pe.edu.upc.wasiseguro.dtos.RutaSugeridaDTO;
import pe.edu.upc.wasiseguro.dtos.RutasFavoritasDTO;
import pe.edu.upc.wasiseguro.entities.Ruta;
import pe.edu.upc.wasiseguro.repositories.IRutaRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IRutaService;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RutaServiceImplement implements IRutaService {
    @Autowired
    private IRutaRepository rutaR;

    @Override
    public List<Ruta> list() { return rutaR.findAll(); }
    @Override
    public Ruta insert(Ruta ruta) { return rutaR.save(ruta); }
    @Override
    public Optional<Ruta> listId(UUID id) { return rutaR.findById(id); }
    @Override
    public void update(Ruta r) { rutaR.save(r); }
    @Override
    public void delete(UUID id) { rutaR.deleteById(id); }

    @Override
    public List<RutasFavoritasDTO> listFavoritas() { return rutaR.findByEsFavoritaTrue(); }

    @Override
    public List<RutaSugeridaDTO> sugerirRutasSeguras() { return rutaR.sugerirRutasSeguras(); }

    @Override
    public List<Ruta> listByUsuario(UUID idUsuario) { return rutaR.findByUsuarioId(idUsuario); }

    @Override
    public List<RutaSugeridaDTO> buscarRutasAlternativas(double destLat, double destLng) {
        return rutaR.findTop2ByDestinoLatAndDestinoLng(destLat, destLng);
    }

    @Override
    public RutaComparacionDTO compararTiempos(double destLat, double destLng) {
        List<Ruta> rutas = rutaR.findByDestinoLatAndDestinoLng(destLat, destLng);
        if (rutas.size() < 2) return null;
        Ruta rapida = rutas.stream().min(Comparator.comparingDouble(r -> r.getDuracionMin().doubleValue())).get();
        Ruta segura = rutas.stream().filter(r -> r.getNivelRiesgo().getIdNivelRiesgo() == 1).findFirst().orElse(rapida);
        double diferencia = segura.getDuracionMin().doubleValue() - rapida.getDuracionMin().doubleValue();
        RutaComparacionDTO dto = new RutaComparacionDTO();
        dto.setNombreDestino(rapida.getNombreDestino());
        dto.setTiempoRapida(rapida.getDuracionMin().doubleValue());
        dto.setTiempoSegura(segura.getDuracionMin().doubleValue());
        dto.setTiempoExtra(diferencia + " min");
        return dto;
    }
}