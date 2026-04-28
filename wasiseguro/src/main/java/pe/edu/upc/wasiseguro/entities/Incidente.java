package pe.edu.upc.wasiseguro.entities;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "incidente")
public class Incidente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_tipo", nullable = false)
    private TipoIncidente tipoIncidente;

    @Column(name = "descripcion", columnDefinition = "VARCHAR(MAX)")
    private String descripcion;

    @Column(name = "latitud", nullable = false)
    private Double latitud;

    @Column(name = "longitud", nullable = false)
    private Double longitud;

    @Column(name = "foto_url", columnDefinition = "VARCHAR(MAX)")
    private String fotoUrl;

    @Column(name = "fecha_ocurrido", nullable = false)
    private OffsetDateTime fechaOcurrido = OffsetDateTime.now();

    @Column(name = "estado", nullable = false, length = 20)
    private String estado = "pendiente";

    @Column(name = "votos_valido", nullable = false)
    private Integer votosValido = 0;

    @Column(name = "votos_invalido", nullable = false)
    private Integer votosInvalido = 0;

    public Incidente() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public TipoIncidente getTipoIncidente() { return tipoIncidente; }
    public void setTipoIncidente(TipoIncidente tipoIncidente) { this.tipoIncidente = tipoIncidente; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getLatitud() { return latitud; }
    public void setLatitud(Double latitud) { this.latitud = latitud; }

    public Double getLongitud() { return longitud; }
    public void setLongitud(Double longitud) { this.longitud = longitud; }

    public String getFotoUrl() { return fotoUrl; }
    public void setFotoUrl(String fotoUrl) { this.fotoUrl = fotoUrl; }

    public OffsetDateTime getFechaOcurrido() { return fechaOcurrido; }
    public void setFechaOcurrido(OffsetDateTime fechaOcurrido) { this.fechaOcurrido = fechaOcurrido; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Integer getVotosValido() { return votosValido; }
    public void setVotosValido(Integer votosValido) { this.votosValido = votosValido; }

    public Integer getVotosInvalido() { return votosInvalido; }
    public void setVotosInvalido(Integer votosInvalido) { this.votosInvalido = votosInvalido; }
}