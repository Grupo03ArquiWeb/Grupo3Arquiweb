package pe.edu.upc.wasiseguro.serviceinterface;

import pe.edu.upc.wasiseguro.entities.NivelRiesgo;

import java.util.List;

public interface INivelRiesgoService {
    public void insert (NivelRiesgo nivelRiesgo);
    public List<NivelRiesgo> list();
    public void delete(int id);
    public NivelRiesgo listId(int id);
}
