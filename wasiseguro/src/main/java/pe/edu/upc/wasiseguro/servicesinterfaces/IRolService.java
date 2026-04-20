package pe.edu.upc.wasiseguro.servicesinterfaces;

import pe.edu.upc.wasiseguro.entities.Rol;

import java.util.List;

public interface IRolService{
    public List<Rol> list();
    public Rol insert(Rol rol);
}
