package pe.edu.upc.wasiseguro.dtos;

import java.time.LocalDate;

public class SuscripcionEstadisticaEstadoDTO {
    private String estado;
    private Long cantidadSuscripciones;
    private String nombrePlanConMasSuscripciones;
    private Double porcentaje;
    private LocalDate fechaInicioReciente;
    private LocalDate fechaFinReciente;

    public SuscripcionEstadisticaEstadoDTO() {}

    public SuscripcionEstadisticaEstadoDTO(String estado, Long cantidadSuscripciones,
                                           String nombrePlanConMasSuscripciones, Double porcentaje,
                                           LocalDate fechaInicioReciente, LocalDate fechaFinReciente) {
        this.estado = estado;
        this.cantidadSuscripciones = cantidadSuscripciones;
        this.nombrePlanConMasSuscripciones = nombrePlanConMasSuscripciones;
        this.porcentaje = porcentaje;
        this.fechaInicioReciente = fechaInicioReciente;
        this.fechaFinReciente = fechaFinReciente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getCantidadSuscripciones() {
        return cantidadSuscripciones;
    }
    public void setCantidadSuscripciones(Long cantidadSuscripciones) { this.cantidadSuscripciones = cantidadSuscripciones; }

    public String getNombrePlanConMasSuscripciones() { return nombrePlanConMasSuscripciones; }
    public void setNombrePlanConMasSuscripciones(String nombrePlanConMasSuscripciones) { this.nombrePlanConMasSuscripciones = nombrePlanConMasSuscripciones; }

    public Double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(Double porcentaje) { this.porcentaje = porcentaje; }

    public LocalDate getFechaInicioReciente() { return fechaInicioReciente; }
    public void setFechaInicioReciente(LocalDate fechaInicioReciente) { this.fechaInicioReciente = fechaInicioReciente; }

    public LocalDate getFechaFinReciente() { return fechaFinReciente; }
    public void setFechaFinReciente(LocalDate fechaFinReciente) { this.fechaFinReciente = fechaFinReciente; }
}