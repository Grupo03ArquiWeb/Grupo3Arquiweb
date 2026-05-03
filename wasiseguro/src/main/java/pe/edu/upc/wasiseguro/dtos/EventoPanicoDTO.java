package pe.edu.upc.wasiseguro.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class EventoPanicoDTO {
    private UUID id;
    private double latitud;
    private double longitud;
    private String mensajeExtra;
    private LocalDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getMensajeExtra() {
        return mensajeExtra;
    }

    public void setMensajeExtra(String mensajeExtra) {
        this.mensajeExtra = mensajeExtra;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}