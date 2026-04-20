package pe.edu.upc.wasiseguro.servicesinterfaces;

import pe.edu.upc.wasiseguro.entities.Usuario;
import java.util.List;

public interface IUsuarioService {
    public List<Usuario> list();
    public Usuario insert(Usuario user);
}
