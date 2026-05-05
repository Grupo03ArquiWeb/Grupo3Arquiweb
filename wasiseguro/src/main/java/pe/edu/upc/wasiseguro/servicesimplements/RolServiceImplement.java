package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.entities.Rol;
import pe.edu.upc.wasiseguro.repositories.IRolRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IRolService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
    @Override
    public List<Rol> buscarPorNombre(String nombre) {
        return rolR.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<Rol> buscarPorActivo(boolean activo) {
        return rolR.findByActivo(activo);
    }

    @Override
    public List<Map<String, Object>> contarUsuariosActivosPorRol() {
        return rolR.contarUsuariosActivosPorRol().stream()
                .map(row -> {
                    Map<String, Object> map = new LinkedHashMap<>();
                    map.put("rol", row[0]);
                    map.put("totalUsuariosActivos", row[1]);
                    return map;
                })
                .toList();
    }
}
