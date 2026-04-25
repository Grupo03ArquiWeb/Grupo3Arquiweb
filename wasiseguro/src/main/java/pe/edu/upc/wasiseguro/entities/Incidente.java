package pe.edu.upc.wasiseguro.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "incidente")
public class Incidente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_tipo", nullable = false)
    private TipoIncidente tipoIncidente;

    @ManyToOne
    @JoinColumn(name = "id_zona")
    private ZonaRiesgo zonaRiesgo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "latitud", nullable = false)
    private double latitud;

    @Column(name = "longitud", nullable = false)
    private double longitud;

    @Column(name = "foto_url")
    private String fotoUrl;

    @Column(name = "fecha_ocurrido", nullable = false)
    private LocalDateTime fechaOcurrido;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;

    @Column(name = "votos_valido", nullable = false)
    private int votosValido;

    @Column(name = "votos_invalido", nullable = false)
    private int votosInvalido;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Incidente() {
    }

    public Incidente(UUID id, Usuario usuario, TipoIncidente tipoIncidente, ZonaRiesgo zonaRiesgo, String descripcion, double latitud, double longitud, String fotoUrl, LocalDateTime fechaOcurrido, String estado, int votosValido, int votosInvalido, LocalDateTime createdAt) {
        this.id = id;
        this.usuario = usuario;
        this.tipoIncidente = tipoIncidente;
        this.zonaRiesgo = zonaRiesgo;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fotoUrl = fotoUrl;
        this.fechaOcurrido = fechaOcurrido;
        this.estado = estado;
        this.votosValido = votosValido;
        this.votosInvalido = votosInvalido;
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

    public TipoIncidente getTipoIncidente() {
        return tipoIncidente;
    }

    public void setTipoIncidente(TipoIncidente tipoIncidente) {
        this.tipoIncidente = tipoIncidente;
    }

    public ZonaRiesgo getZonaRiesgo() {
        return zonaRiesgo;
    }

    public void setZonaRiesgo(ZonaRiesgo zonaRiesgo) {
        this.zonaRiesgo = zonaRiesgo;
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

    public int getVotosValido() {
        return votosValido;
    }

    public void setVotosValido(int votosValido) {
        this.votosValido = votosValido;
    }

    public int getVotosInvalido() {
        return votosInvalido;
    }

    public void setVotosInvalido(int votosInvalido) {
        this.votosInvalido = votosInvalido;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
