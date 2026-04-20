package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.entities.Rol;
import pe.edu.upc.wasiseguro.entities.Usuario;
import pe.edu.upc.wasiseguro.repositories.IUsuarioRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IUsuarioService;

import java.util.List;

@Service
public class UsuarioServiceImplement implements IUsuarioService {
    @Autowired
    private IUsuarioRepository userR;

    @Override
    public List<Usuario> list() {
        return userR.findAll();
    }

    @Override
    public Usuario insert(Usuario user) {
        Rol rolDefault = new Rol();
        rolDefault.setId(1);
        user.setRol(rolDefault);
        return userR.save(user);
    }
}
