package pe.edu.upc.wasiseguro.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "evento_panico")
public class EventoPanico {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "latitud", nullable = false)
    private double latitud;

    @Column(name = "longitud", nullable = false)
    private double longitud;

    @Column(name = "mensaje_extra")
    private String mensajeExtra;

    @Column(name = "atendido", nullable = false)
    private boolean atendido;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public EventoPanico() {
    }

    public EventoPanico(UUID id, Usuario usuario, double latitud, double longitud, String mensajeExtra, boolean atendido, LocalDateTime createdAt) {
        this.id = id;
        this.usuario = usuario;
        this.latitud = latitud;
        this.longitud = longitud;
        this.mensajeExtra = mensajeExtra;
        this.atendido = atendido;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
