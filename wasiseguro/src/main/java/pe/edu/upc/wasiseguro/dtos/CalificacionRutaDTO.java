package pe.edu.upc.wasiseguro.dtos;

import pe.edu.upc.wasiseguro.entities.Ruta;
import pe.edu.upc.wasiseguro.entities.Usuario;

import java.time.LocalDateTime;

public class CalificacionRutaDTO {
    private int id;
    private Ruta ruta;
    private Usuario usuario;
    private short puntaje;
    private String comentario;
    private LocalDateTime createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public short getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(short puntaje) {
        this.puntaje = puntaje;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
