package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.dtos.RutaSeguridadDTO;
import pe.edu.upc.wasiseguro.entities.CalificacionRuta;
import pe.edu.upc.wasiseguro.repositories.ICalificacionRutaRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.ICalificacionRutaService;
import java.util.List;

@Service
public class CalificacionRutaServiceImplement implements ICalificacionRutaService {
    @Autowired
    private ICalificacionRutaRepository cR;
    @Override
    public void insert(CalificacionRuta calificacionRuta) { cR.save(calificacionRuta); }
    @Override
    public List<CalificacionRuta> list() { return cR.findAll(); }
    @Override
    public List<RutaSeguridadDTO> obtenerPromedioSeguridad() { return cR.getPromedioSeguridadPorRuta(); }
}