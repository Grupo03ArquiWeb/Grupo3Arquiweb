package pe.edu.upc.wasiseguro.servicesinterfaces;

import pe.edu.upc.wasiseguro.entities.ZonaFavorita;
import java.util.List;

public interface IZonaFavoritaService {
    public void insert(ZonaFavorita zonaFavorita);
    public List<ZonaFavorita> list();
    public void delete(int id);
}