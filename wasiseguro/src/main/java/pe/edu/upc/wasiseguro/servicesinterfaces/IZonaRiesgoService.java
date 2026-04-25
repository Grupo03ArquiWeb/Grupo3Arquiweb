package pe.edu.upc.wasiseguro.servicesinterfaces;

import pe.edu.upc.wasiseguro.entities.ZonaRiesgo;

import java.util.List;
import java.util.Optional;

public interface IZonaRiesgoService {
    public List<ZonaRiesgo> list();
    public ZonaRiesgo insert(ZonaRiesgo zr);
    public Optional<ZonaRiesgo> listId(int id);
    public void update(ZonaRiesgo zr);
    public void delete(int id);

}
