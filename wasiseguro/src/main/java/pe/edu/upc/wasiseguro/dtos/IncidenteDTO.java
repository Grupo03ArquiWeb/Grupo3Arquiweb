package pe.edu.upc.wasiseguro.dtos;

import java.time.OffsetDateTime;
import java.util.UUID;

public class IncidenteDTO {
    private UUID id;
    private UUID idUsuario;
    private int idTipo;
    private int idZona;
    private String descripcion;
    private Double latitud;
    private Double longitud;
    private String fotoUrl;
    private OffsetDateTime fechaOcurrido;
    private String estado;
    private int votosValido;
    private int votosInvalido;

    public IncidenteDTO() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getIdUsuario() { return idUsuario; }
    public void setIdUsuario(UUID idUsuario) { this.idUsuario = idUsuario; }

    public int getIdTipo() { return idTipo; }
    public void setIdTipo(int idTipo) { this.idTipo = idTipo; }

    public int getIdZona() { return idZona; }
    public void setIdZona(int idZona) { this.idZona = idZona; }

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

    public int getVotosValido() { return votosValido; }
    public void setVotosValido(int votosValido) { this.votosValido = votosValido; }

    public int getVotosInvalido() { return votosInvalido; }
    public void setVotosInvalido(int votosInvalido) { this.votosInvalido = votosInvalido; }
}