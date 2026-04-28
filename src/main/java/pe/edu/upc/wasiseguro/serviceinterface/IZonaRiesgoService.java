package pe.edu.upc.wasiseguro.serviceinterface;

import pe.edu.upc.wasiseguro.entities.ZonaRiesgo;

import java.util.List;

public interface IZonaRiesgoService {
    public List<ZonaRiesgo> list();

    void insert(ZonaRiesgo zonaRiesgo);

    ZonaRiesgo listId(int id);

    void delete(int id);
    List<ZonaRiesgo> listarActivas();
    List<String[]> cantidadZonasPorNivel();
    List<String[]> zonasActivasOrdenadasPorRiesgoHora();

}
