package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.dtos.UsuarioEstadisticaEstadoDTO;
import pe.edu.upc.wasiseguro.dtos.UsuarioEstadisticaIdiomaDTO;
import pe.edu.upc.wasiseguro.entities.Usuario;
import pe.edu.upc.wasiseguro.repositories.IUsuarioRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IUsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
        return userR.save(user);
    }

    @Override
    public Optional<Usuario> listId(UUID id) {
        return userR.findById(id);
    }

    @Override
    public void update(Usuario u) {
        userR.save(u);
    }

    @Override
    public void delete(UUID id) {
        userR.deleteById(id);
    }

    // Filtros
    @Override
    public List<Usuario> findByNombre(String nombre) {
        return userR.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<Usuario> buscarPorRol(String nombreRol) {
        return userR.buscarUsuariosPorRol(nombreRol);
    }

    @Override
    public List<Usuario> buscarPorDominio(String dominio) {
        return userR.buscarUsuariosPorDominioEmail(dominio);
    }

    // Query
    @Override
    public List<Usuario> buscarUsuariosInactivos(int dias) {
        return userR.buscarUsuariosInactivos(dias);
    }

    //facebook api
    @Override
    public Usuario buscarPorEmail(String email) {
        return userR.findByEmail(email);
    }

    @Override
    public List<UsuarioEstadisticaEstadoDTO> estadisticasPorEstado() {
        return userR.estadisticasPorEstado().stream()
                .map(row -> new UsuarioEstadisticaEstadoDTO((String) row[0], (Long) row[1]))
                .collect(Collectors.toList());
    }

    @Override
    public List<UsuarioEstadisticaIdiomaDTO> estadisticasPorIdioma() {
        return userR.estadisticasPorIdioma().stream()
                .map(row -> new UsuarioEstadisticaIdiomaDTO((String) row[0], (Long) row[1]))
                .collect(Collectors.toList());
    }
}
