package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.entities.ZonaFavorita;
import pe.edu.upc.wasiseguro.repositories.IZonaFavoritaRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IZonaFavoritaService;

import java.util.List;

@Service
public class ZonaFavoritaServiceImplement implements IZonaFavoritaService {
    @Autowired
    private IZonaFavoritaRepository zR;

    @Override
    public void insert(ZonaFavorita zonaFavorita) {
        zR.save(zonaFavorita);
    }

    @Override
    public void update(ZonaFavorita zonaFavorita) {
        zR.save(zonaFavorita);
    }

    @Override
    public List<ZonaFavorita> list() {
        return zR.findAll();
    }

    @Override
    public void delete(int id) {
        zR.deleteById(id);
    }
}