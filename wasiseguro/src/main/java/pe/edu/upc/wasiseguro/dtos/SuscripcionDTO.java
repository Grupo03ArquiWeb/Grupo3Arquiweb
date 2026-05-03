package pe.edu.upc.wasiseguro.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SuscripcionDTO {
    private int id;
    private String planNombre;
    private BigDecimal precioMensual;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlanNombre() {
        return planNombre;
    }

    public void setPlanNombre(String planNombre) {
        this.planNombre = planNombre;
    }

    public BigDecimal getPrecioMensual() {
        return precioMensual;
    }

    public void setPrecioMensual(BigDecimal precioMensual) {
        this.precioMensual = precioMensual;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}