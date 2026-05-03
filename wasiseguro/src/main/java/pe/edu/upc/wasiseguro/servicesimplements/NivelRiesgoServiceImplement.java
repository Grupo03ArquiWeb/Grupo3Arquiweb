package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.entities.NivelRiesgo;
import pe.edu.upc.wasiseguro.repositories.INivelRiesgoRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.INivelRiesgoService;

import java.util.List;
import java.util.Optional;

@Service
public class NivelRiesgoServiceImplement implements INivelRiesgoService {
    @Autowired
    private INivelRiesgoRepository nR;

    @Override
    public List<NivelRiesgo> list(){
        return nR.findAll();
    }

    @Override
    public NivelRiesgo insert(NivelRiesgo nr)  {
        return nR.save(nr);
    }

    @Override
    public Optional<NivelRiesgo> listId(int id)   {
        return nR.findById(id);
    }

    @Override
    public void update(NivelRiesgo nr)  {
        nR.save(nr);
    }

    @Override
    public void delete(int id)  {
        nR.deleteById(id);
    }
}
