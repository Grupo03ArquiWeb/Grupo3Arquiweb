package pe.edu.upc.wasiseguro.servicesinterfaces;

import pe.edu.upc.wasiseguro.entities.Usuario;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUsuarioService {
    public List<Usuario> list();
    public Usuario insert(Usuario user);
    public Optional<Usuario> listId(UUID id);
    public void update(Usuario u);
    public void delete(UUID id);
}
