package pe.edu.upc.wasiseguro.dtos;

import java.math.BigDecimal;
//query estadistica por plan
public class SuscripcionEstadisticaPlanDTO {
    private String nombrePlan;
    private Long cantidadSuscripciones;
    private BigDecimal precioMensual;
    private BigDecimal precioAnual;
    private BigDecimal totalIngresosMensuales;
    private boolean planActivo;

    public SuscripcionEstadisticaPlanDTO() {}

    public SuscripcionEstadisticaPlanDTO(String nombrePlan, Long cantidadSuscripciones,
                                          BigDecimal precioMensual, BigDecimal precioAnual,
                                          BigDecimal totalIngresosMensuales, boolean planActivo) {
        this.nombrePlan = nombrePlan;
        this.cantidadSuscripciones = cantidadSuscripciones;
        this.precioMensual = precioMensual;
        this.precioAnual = precioAnual;
        this.totalIngresosMensuales = totalIngresosMensuales;
        this.planActivo = planActivo;
    }

    public String getNombrePlan() { return nombrePlan; }
    public void setNombrePlan(String nombrePlan) { this.nombrePlan = nombrePlan; }

    public Long getCantidadSuscripciones() { return cantidadSuscripciones; }
    public void setCantidadSuscripciones(Long cantidadSuscripciones) { this.cantidadSuscripciones = cantidadSuscripciones; }

    public BigDecimal getPrecioMensual() { return precioMensual; }
    public void setPrecioMensual(BigDecimal precioMensual) { this.precioMensual = precioMensual; }

    public BigDecimal getPrecioAnual() { return precioAnual; }
    public void setPrecioAnual(BigDecimal precioAnual) { this.precioAnual = precioAnual; }

    public BigDecimal getTotalIngresosMensuales() { return totalIngresosMensuales; }
    public void setTotalIngresosMensuales(BigDecimal totalIngresosMensuales) { this.totalIngresosMensuales = totalIngresosMensuales; }

    public boolean isPlanActivo() { return planActivo; }
    public void setPlanActivo(boolean planActivo) { this.planActivo = planActivo; }
}