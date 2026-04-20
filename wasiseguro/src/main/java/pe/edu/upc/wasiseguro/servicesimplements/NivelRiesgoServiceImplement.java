package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.entities.NivelRiesgo;
import pe.edu.upc.wasiseguro.repositories.INivelRiesgoRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.INivelRiesgoService;

import java.util.List;

@Service
public class NivelRiesgoServiceImplement implements INivelRiesgoService {
    @Autowired
    private INivelRiesgoRepository nR;

    @Override
    public List<NivelRiesgo> list(){
        return nR.findAll();
    }
}
