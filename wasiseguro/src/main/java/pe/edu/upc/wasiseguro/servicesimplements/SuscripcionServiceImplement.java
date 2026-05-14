package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.wasiseguro.dtos.SuscripcionEstadisticaPlanDTO;
import pe.edu.upc.wasiseguro.dtos.SuscripcionEstadisticaEstadoDTO;
import pe.edu.upc.wasiseguro.entities.Suscripcion;
import pe.edu.upc.wasiseguro.repositories.ISuscripcionRepository;
import pe.edu.upc.wasiseguro.servicesinterfaces.ISuscripcionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SuscripcionServiceImplement implements ISuscripcionService {

    @Autowired
    private ISuscripcionRepository sR;

    @Override
    public List<Suscripcion> list() {
        return sR.findAll();
    }

    @Override
    public Suscripcion insert(Suscripcion s) {
        return sR.save(s);
    }

    @Override
    public Optional<Suscripcion> listId(int id) {
        return sR.findById(id);
    }

    @Override
    public void update(Suscripcion s) {
        sR.save(s);
    }

    @Override
    public void delete(int id) {
        sR.deleteById(id);
    }

    @Override
    public List<Suscripcion> filtrarPorEstado(String estado) {
        return sR.findAll().stream()
                .filter(s -> s.getEstado().equalsIgnoreCase(estado))
                .collect(Collectors.toList());
    }

    @Override
    public List<Suscripcion> filtrarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return sR.findAll().stream()
                .filter(s -> !s.getFechaInicio().isBefore(fechaInicio) && !s.getFechaInicio().isAfter(fechaFin))
                .collect(Collectors.toList());
    }

    @Override
    public List<SuscripcionEstadisticaEstadoDTO> estadisticasPorEstado() {
        List<Object[]> resultados = sR.estadisticasPorEstado();
        return resultados.stream().map(row -> {
            SuscripcionEstadisticaEstadoDTO dto = new SuscripcionEstadisticaEstadoDTO();
            dto.setEstado((String) row[0]);
            dto.setCantidadSuscripciones((Long) row[1]);
            dto.setNombrePlanConMasSuscripciones((String) row[2]);
            dto.setPorcentaje((double) dto.getCantidadSuscripciones() * 100.0 / sR.count());
            dto.setFechaInicioReciente((LocalDate) row[3]);
            dto.setFechaFinReciente((LocalDate) row[4]);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<SuscripcionEstadisticaPlanDTO> estadisticasPorPlan() {
        List<Object[]> resultados = sR.estadisticasPorPlan();
        return resultados.stream().map(row -> {
            SuscripcionEstadisticaPlanDTO dto = new SuscripcionEstadisticaPlanDTO();
            dto.setNombrePlan((String) row[0]);
            dto.setCantidadSuscripciones((Long) row[1]);
            dto.setPrecioMensual((BigDecimal) row[2]);
            dto.setPrecioAnual((BigDecimal) row[3]);
            dto.setTotalIngresosMensuales((BigDecimal) row[4]);
            dto.setPlanActivo((Boolean) row[5]);
            return dto;
        }).collect(Collectors.toList());
    }
}