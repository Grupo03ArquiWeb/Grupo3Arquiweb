package pe.edu.upc.wasiseguro.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "alerta")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_incidente")
    private Incidente incidente;

    @ManyToOne
    @JoinColumn(name = "id_zona")
    private ZonaRiesgo zonaRiesgo;

    @Column(name = "titulo", length = 200, nullable = false)
    private String titulo;

    @Column(name = "mensaje", nullable = false)
    private String mensaje;

    @Column(name = "latitud")
    private Double latitud;

    @Column(name = "longitud")
    private Double longitud;

    @Column(name = "radio_metros", nullable = false)
    private int radioMetros;

    @Column(name = "leida", nullable = false)
    private boolean leida;

    @Column(name = "activa", nullable = false)
    private boolean activa;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "expira_en")
    private LocalDateTime expiraEn;

    public Alerta() {
    }

    public Alerta(UUID id, Usuario usuario, Incidente incidente, ZonaRiesgo zonaRiesgo, String titulo, String mensaje, Double latitud, Double longitud, int radioMetros, boolean leida, boolean activa, LocalDateTime createdAt, LocalDateTime expiraEn) {
        this.id = id;
        this.usuario = usuario;
        this.incidente = incidente;
        this.zonaRiesgo = zonaRiesgo;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.latitud = latitud;
        this.longitud = longitud;
        this.radioMetros = radioMetros;
        this.leida = leida;
        this.activa = activa;
        this.createdAt = createdAt;
        this.expiraEn = expiraEn;
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

    public Incidente getIncidente() {
        return incidente;
    }

    public void setIncidente(Incidente incidente) {
        this.incidente = incidente;
    }

    public ZonaRiesgo getZonaRiesgo() {
        return zonaRiesgo;
    }

    public void setZonaRiesgo(ZonaRiesgo zonaRiesgo) {
        this.zonaRiesgo = zonaRiesgo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public int getRadioMetros() {
        return radioMetros;
    }

    public void setRadioMetros(int radioMetros) {
        this.radioMetros = radioMetros;
    }

    public boolean isLeida() {
        return leida;
    }

    public void setLeida(boolean leida) {
        this.leida = leida;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiraEn() {
        return expiraEn;
    }

    public void setExpiraEn(LocalDateTime expiraEn) {
        this.expiraEn = expiraEn;
    }
}
