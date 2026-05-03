package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.entities.Rol;
import pe.edu.upc.wasiseguro.repositories.IRolRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IRolService;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Rol> listId(int id) {
        return rolR.findById(id);
    }

    @Override
    public void update(Rol r)  {
        rolR.save(r);
    }

    @Override
    public void delete(int id)  {
        rolR.deleteById(id);
    }
}
