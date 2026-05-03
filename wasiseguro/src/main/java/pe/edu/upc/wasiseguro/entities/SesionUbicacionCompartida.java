package pe.edu.upc.wasiseguro.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sesion_ubicacion_compartida")
public class SesionUbicacionCompartida {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "activa", nullable = false)
    private boolean activa;

    @Column(name = "iniciada_en", nullable = false)
    private LocalDateTime iniciadaEn;

    @Column(name = "finalizada_en")
    private LocalDateTime finalizadaEn;

    public SesionUbicacionCompartida() {
    }

    public SesionUbicacionCompartida(UUID id, Usuario usuario, boolean activa, LocalDateTime iniciadaEn, LocalDateTime finalizadaEn) {
        this.id = id;
        this.usuario = usuario;
        this.activa = activa;
        this.iniciadaEn = iniciadaEn;
        this.finalizadaEn = finalizadaEn;
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

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public LocalDateTime getIniciadaEn() {
        return iniciadaEn;
    }

    public void setIniciadaEn(LocalDateTime iniciadaEn) {
        this.iniciadaEn = iniciadaEn;
    }

    public LocalDateTime getFinalizadaEn() {
        return finalizadaEn;
    }

    public void setFinalizadaEn(LocalDateTime finalizadaEn) {
        this.finalizadaEn = finalizadaEn;
    }
}
