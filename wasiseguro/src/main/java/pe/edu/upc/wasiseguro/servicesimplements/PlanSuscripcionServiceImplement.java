package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.entities.PlanSuscripcion;
import pe.edu.upc.wasiseguro.repositories.IPlanSuscripcionRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.IPlanSuscripcionService;

import java.util.List;

@Service
public class PlanSuscripcionServiceImplement implements IPlanSuscripcionService {

    @Autowired
    private IPlanSuscripcionRepository pR;

    @Override
    public void insertar(PlanSuscripcion planSuscripcion) {
        pR.save(planSuscripcion);
    }

    @Override
    public List<PlanSuscripcion> listar() {
        return pR.findAll();
    }

    @Override
    public PlanSuscripcion listarId(int id) {
        return pR.findById(id).orElse(new PlanSuscripcion());
    }

    @Override
    public void actualizar(PlanSuscripcion planSuscripcion) {
        pR.save(planSuscripcion);
    }

    @Override
    public void eliminar(int id) {
        pR.deleteById(id);
    }
}