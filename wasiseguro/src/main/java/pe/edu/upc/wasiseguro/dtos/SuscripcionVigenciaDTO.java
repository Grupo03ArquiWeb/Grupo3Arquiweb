package pe.edu.upc.wasiseguro.dtos;

import java.time.LocalDate;

public class SuscripcionVigenciaDTO {
    private int id;
    private String nombreUsuario;
    private String planNombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estadoRegistrado;
    private String vigencia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPlanNombre() {
        return planNombre;
    }

    public void setPlanNombre(String planNombre) {
        this.planNombre = planNombre;
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

    public String getEstadoRegistrado() {
        return estadoRegistrado;
    }

    public void setEstadoRegistrado(String estadoRegistrado) {
        this.estadoRegistrado = estadoRegistrado;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }
}