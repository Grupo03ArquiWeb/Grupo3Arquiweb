package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.entities.Rol;
import pe.edu.upc.wasiseguro.repositories.IRolRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IRolService;

import java.util.List;

@Service
public class RolServiceImplement implements IRolService {
    @Autowired
    private IRolRepository rolR;

    @Override
    public List<Rol> list() {
        return rolR.findAll();
    }

    @Override
    public Rol insert(Rol rol) {
        return rolR.save(rol);
    }
}
