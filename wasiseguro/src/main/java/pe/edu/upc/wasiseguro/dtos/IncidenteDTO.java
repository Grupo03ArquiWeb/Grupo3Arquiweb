package pe.edu.upc.wasiseguro.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class IncidenteDTO {
    private UUID id;
    private String tipoNombre;
    private String descripcion;
    private double latitud;
    private double longitud;
    private String fotoUrl;
    private LocalDateTime fechaOcurrido;
    private String estado;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTipoNombre() {
        return tipoNombre;
    }

    public void setTipoNombre(String tipoNombre) {
        this.tipoNombre = tipoNombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public LocalDateTime getFechaOcurrido() {
        return fechaOcurrido;
    }

    public void setFechaOcurrido(LocalDateTime fechaOcurrido) {
        this.fechaOcurrido = fechaOcurrido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}