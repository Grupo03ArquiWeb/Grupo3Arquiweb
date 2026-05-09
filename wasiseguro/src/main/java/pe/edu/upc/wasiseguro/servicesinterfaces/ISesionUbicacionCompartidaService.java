package pe.edu.upc.wasiseguro.servicesinterfaces;

import pe.edu.upc.wasiseguro.entities.SesionUbicacionCompartida;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISesionUbicacionCompartidaService {

    List<SesionUbicacionCompartida> list();

    SesionUbicacionCompartida insert(SesionUbicacionCompartida sesion);

    Optional<SesionUbicacionCompartida> listId(UUID id);
    void delete(UUID id);

    void delete(int id);

    void update(SesionUbicacionCompartida sesion);
}