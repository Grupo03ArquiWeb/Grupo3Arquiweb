package pe.edu.upc.wasiseguro.dtos;

import pe.edu.upc.wasiseguro.entities.Usuario;

import java.time.LocalDateTime;
import java.util.UUID;

public class SesionUbicacionCompartidaDTO {
    private UUID id;
    private Usuario usuario;
    private boolean activa;
    private LocalDateTime iniciadaEn;
    private LocalDateTime finalizadaEn;

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
