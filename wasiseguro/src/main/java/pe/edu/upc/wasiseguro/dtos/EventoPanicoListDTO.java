package pe.edu.upc.wasiseguro.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class EventoPanicoListDTO {
    private UUID id;
    private UUID idUsuario;
    private double latitud;
    private double longitud;
    private String mensajeExtra;
    private boolean atendido;
    private LocalDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
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

    public boolean isAtendido() {
        return atendido;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}