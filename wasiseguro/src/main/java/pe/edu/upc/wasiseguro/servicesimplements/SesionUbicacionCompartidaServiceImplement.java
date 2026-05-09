package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.entities.SesionUbicacionCompartida;
import pe.edu.upc.wasiseguro.repositories.ISesionUbicacionCompartidaRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.ISesionUbicacionCompartidaService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SesionUbicacionCompartidaServiceImplement implements ISesionUbicacionCompartidaService {

    @Autowired
    private ISesionUbicacionCompartidaRepository sR;

    @Override
    public List<SesionUbicacionCompartida> list() {
        return sR.findAll();
    }

    @Override
    public SesionUbicacionCompartida insert(SesionUbicacionCompartida sesion) {
        return sR.save(sesion);
    }

    @Override
    public Optional<SesionUbicacionCompartida> listId(UUID id) {
        return sR.findById(id);
    }

    @Override
    public void delete(UUID id) {
        sR.deleteById(id);
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(SesionUbicacionCompartida sesion) {
        sR.save(sesion);
    }
}