package pe.edu.upc.wasiseguro.servicesinterfaces;

import pe.edu.upc.wasiseguro.entities.Rol;

import java.util.List;
import java.util.Optional;

public interface IRolService{
    public List<Rol> list();
    public Rol insert(Rol rol);
    public Optional<Rol> listId(int id);
    public void update(Rol r);
    public void delete(int id);
}
