package pe.edu.upc.wasiseguro.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "calificacion_ruta")
public class CalificacionRuta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_ruta", nullable = false)
    private Ruta ruta;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "puntaje", nullable = false)
    private short puntaje;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public CalificacionRuta() {
    }

    public CalificacionRuta(int id, Ruta ruta, Usuario usuario, short puntaje, String comentario, LocalDateTime createdAt) {
        this.id = id;
        this.ruta = ruta;
        this.usuario = usuario;
        this.puntaje = puntaje;
        this.comentario = comentario;
        this.createdAt = createdAt;
    }

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
