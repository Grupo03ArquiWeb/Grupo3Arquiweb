package pe.edu.upc.wasiseguro.servicesinterfaces;

import pe.edu.upc.wasiseguro.dtos.RutaSeguridadDTO;
import pe.edu.upc.wasiseguro.entities.CalificacionRuta;
import java.util.List;

public interface ICalificacionRutaService {
    public void insert(CalificacionRuta calificacionRuta);
    public List<CalificacionRuta> list();
    public List<RutaSeguridadDTO> obtenerPromedioSeguridad();
}