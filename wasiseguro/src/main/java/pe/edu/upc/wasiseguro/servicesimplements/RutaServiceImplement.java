package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.dtos.RutaSugeridaDTO;
import pe.edu.upc.wasiseguro.dtos.RutasFavoritasDTO;
import pe.edu.upc.wasiseguro.entities.Ruta;
import pe.edu.upc.wasiseguro.repositories.IRutaRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IRutaService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RutaServiceImplement implements IRutaService {

    @Autowired
    private IRutaRepository rutaR;

    @Override
    public List<Ruta> list() {
        return rutaR.findAll();
    }

    @Override
    public Ruta insert(Ruta ruta) {
        return rutaR.save(ruta);
    }

    @Override
    public Optional<Ruta> listId(UUID id) {
        return rutaR.findById(id);
    }

    @Override
    public void update(Ruta r) {
        rutaR.save(r);
    }

    @Override
    public void delete(UUID id) {
        rutaR.deleteById(id);
    }

    @Override
    public List<RutasFavoritasDTO> listFavoritas() {
        return rutaR.findFavoritas();
    }

    @Override
    public List<RutaSugeridaDTO> sugerirRutasSeguras() {
        return rutaR.sugerirRutasSeguras();
    }

    @Override
    public List<Ruta> listByUsuario(UUID idUsuario) {
        return rutaR.findRutasByUsuarioId(idUsuario);
    }
}