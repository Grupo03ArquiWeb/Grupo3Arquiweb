package pe.edu.upc.wasiseguro.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "latitud", nullable = false)
    private Double latitud;

    @Column(name = "longitud", nullable = false)
    private Double longitud;

    @Column(name = "foto_url", length = 500)
    private String fotoUrl;

    @Column(name = "fecha_ocurrido", nullable = false)
    private OffsetDateTime fechaOcurrido = OffsetDateTime.now();

    @Column(name = "estado", nullable = false, length = 20)
    private String estado = "pendiente";

    @Column(name = "votos_valido", nullable = false)
    private Integer votosValido = 0;

    @Column(name = "votos_invalido", nullable = false)
    private Integer votosInvalido = 0;

    @Column(name = "es_anonimo", nullable = false)
    private boolean esAnonimo = false;

    @ElementCollection
    @CollectionTable(name = "incidente_votantes", joinColumns = @JoinColumn(name = "incidente_id"))
    @Column(name = "usuario_email")
    private Set<String> usuariosVotantes = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "incidente_comentarios", joinColumns = @JoinColumn(name = "id_incidente"))
    private List<ComentarioEmbeddable> comentarios = new ArrayList<>();

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
    public boolean isEsAnonimo() { return esAnonimo; }
    public void setEsAnonimo(boolean esAnonimo) { this.esAnonimo = esAnonimo; }
    public Set<String> getUsuariosVotantes() { return usuariosVotantes; }
    public void setUsuariosVotantes(Set<String> usuariosVotantes) { this.usuariosVotantes = usuariosVotantes; }
    public List<ComentarioEmbeddable> getComentarios() { return comentarios; }
    public void setComentarios(List<ComentarioEmbeddable> comentarios) { this.comentarios = comentarios; }

    @Embeddable
    public static class ComentarioEmbeddable {
        @Column(name = "texto", length = 500)
        private String texto;
        @Column(name = "autor")
        private String autor;
        @Column(name = "fecha")
        private LocalDateTime fecha = LocalDateTime.now();

        public ComentarioEmbeddable() {}
        public ComentarioEmbeddable(String texto, String autor) {
            this.texto = texto;
            this.autor = autor;
            this.fecha = LocalDateTime.now();
        }
        public String getTexto() { return texto; }
        public void setTexto(String texto) { this.texto = texto; }
        public String getAutor() { return autor; }
        public void setAutor(String autor) { this.autor = autor; }
        public LocalDateTime getFecha() { return fecha; }
        public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    }
}