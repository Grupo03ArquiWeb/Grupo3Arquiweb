package pe.edu.upc.wasiseguro.servicesinterfaces;

import pe.edu.upc.wasiseguro.entities.PlanSuscripcion;

import java.util.List;

public interface IPlanSuscripcionService {
    public void insertar(PlanSuscripcion planSuscripcion);
    public List<PlanSuscripcion> listar();
    public PlanSuscripcion listarId(int id);
    public void actualizar(PlanSuscripcion planSuscripcion);
    public void eliminar(int id);
}