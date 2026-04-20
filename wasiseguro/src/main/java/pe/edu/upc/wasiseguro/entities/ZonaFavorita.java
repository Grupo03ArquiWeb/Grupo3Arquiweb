package pe.edu.upc.wasiseguro.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "zona_favorita")
public class ZonaFavorita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "nombre_alias", length = 100, nullable = false)
    private String nombreAlias;

    @Column(name = "latitud", nullable = false)
    private double latitud;

    @Column(name = "longitud", nullable = false)
    private double longitud;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public ZonaFavorita() {
    }

    public ZonaFavorita(int id, Usuario usuario, String nombreAlias, double latitud, double longitud, LocalDateTime createdAt) {
        this.id = id;
        this.usuario = usuario;
        this.nombreAlias = nombreAlias;
        this.latitud = latitud;
        this.longitud = longitud;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombreAlias() {
        return nombreAlias;
    }

    public void setNombreAlias(String nombreAlias) {
        this.nombreAlias = nombreAlias;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
