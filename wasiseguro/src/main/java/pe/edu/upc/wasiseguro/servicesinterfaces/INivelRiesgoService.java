package pe.edu.upc.wasiseguro.servicesinterfaces;

import pe.edu.upc.wasiseguro.entities.NivelRiesgo;

import java.util.List;
import java.util.Optional;

public interface INivelRiesgoService {
    public List<NivelRiesgo>list();
    public NivelRiesgo insert(NivelRiesgo nr);
    public Optional<NivelRiesgo> listId(int id);
    public void update(NivelRiesgo nr);
    public void delete(int id);
}
