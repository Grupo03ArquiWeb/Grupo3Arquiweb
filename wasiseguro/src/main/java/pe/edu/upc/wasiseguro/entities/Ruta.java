package pe.edu.upc.wasiseguro.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ruta")
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "origen_lat", nullable = false)
    private double origenLat;

    @Column(name = "origen_lng", nullable = false)
    private double origenLng;

    @Column(name = "destino_lat", nullable = false)
    private double destinoLat;

    @Column(name = "destino_lng", nullable = false)
    private double destinoLng;

    @Column(name = "nombre_origen", length = 200)
    private String nombreOrigen;

    @Column(name = "nombre_destino", length = 200)
    private String nombreDestino;

    @Column(name = "distancia_km")
    private BigDecimal distanciaKm;

    @Column(name = "duracion_min")
    private BigDecimal duracionMin;

    @OneToOne
    @JoinColumn(name = "nivel_riesgo")
    private NivelRiesgo nivelRiesgo;

    @Column(name = "geojson_trayecto", columnDefinition = "jsonb")
    private String geojsonTrayecto;

    @Column(name = "es_publica", nullable = false)
    private boolean esPublica;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Ruta() {
    }

    public Ruta(UUID id, Usuario usuario, double origenLat, double origenLng, double destinoLat, double destinoLng, String nombreOrigen, String nombreDestino, BigDecimal distanciaKm, BigDecimal duracionMin, NivelRiesgo nivelRiesgo, String geojsonTrayecto, boolean esPublica, LocalDateTime createdAt) {
        this.id = id;
        this.usuario = usuario;
        this.origenLat = origenLat;
        this.origenLng = origenLng;
        this.destinoLat = destinoLat;
        this.destinoLng = destinoLng;
        this.nombreOrigen = nombreOrigen;
        this.nombreDestino = nombreDestino;
        this.distanciaKm = distanciaKm;
        this.duracionMin = duracionMin;
        this.nivelRiesgo = nivelRiesgo;
        this.geojsonTrayecto = geojsonTrayecto;
        this.esPublica = esPublica;
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

    public double getOrigenLat() {
        return origenLat;
    }

    public void setOrigenLat(double origenLat) {
        this.origenLat = origenLat;
    }

    public double getOrigenLng() {
        return origenLng;
    }

    public void setOrigenLng(double origenLng) {
        this.origenLng = origenLng;
    }

    public double getDestinoLat() {
        return destinoLat;
    }

    public void setDestinoLat(double destinoLat) {
        this.destinoLat = destinoLat;
    }

    public double getDestinoLng() {
        return destinoLng;
    }

    public void setDestinoLng(double destinoLng) {
        this.destinoLng = destinoLng;
    }

    public String getNombreOrigen() {
        return nombreOrigen;
    }

    public void setNombreOrigen(String nombreOrigen) {
        this.nombreOrigen = nombreOrigen;
    }

    public String getNombreDestino() {
        return nombreDestino;
    }

    public void setNombreDestino(String nombreDestino) {
        this.nombreDestino = nombreDestino;
    }

    public BigDecimal getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(BigDecimal distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public BigDecimal getDuracionMin() {
        return duracionMin;
    }

    public void setDuracionMin(BigDecimal duracionMin) {
        this.duracionMin = duracionMin;
    }

    public NivelRiesgo getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(NivelRiesgo nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public String getGeojsonTrayecto() {
        return geojsonTrayecto;
    }

    public void setGeojsonTrayecto(String geojsonTrayecto) {
        this.geojsonTrayecto = geojsonTrayecto;
    }

    public boolean isEsPublica() {
        return esPublica;
    }

    public void setEsPublica(boolean esPublica) {
        this.esPublica = esPublica;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
