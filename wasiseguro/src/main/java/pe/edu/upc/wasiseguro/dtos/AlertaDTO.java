package pe.edu.upc.wasiseguro.dtos;

import java.time.OffsetDateTime;
import java.util.UUID;

public class AlertaDTO {
    private UUID id;
    private UUID idUsuario;
    private UUID idIncidente;
    private int idZona;
    private String titulo;
    private String mensaje;
    private Double latitud;
    private Double longitud;
    private int radioMetros;
    private boolean leida;
    private boolean activa;
    private OffsetDateTime createdAt;
    private OffsetDateTime expiraEn;

    public AlertaDTO() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getIdUsuario() { return idUsuario; }
    public void setIdUsuario(UUID idUsuario) { this.idUsuario = idUsuario; }

    public UUID getIdIncidente() { return idIncidente; }
    public void setIdIncidente(UUID idIncidente) { this.idIncidente = idIncidente; }

    public int getIdZona() { return idZona; }
    public void setIdZona(int idZona) { this.idZona = idZona; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public Double getLatitud() { return latitud; }
    public void setLatitud(Double latitud) { this.latitud = latitud; }

    public Double getLongitud() { return longitud; }
    public void setLongitud(Double longitud) { this.longitud = longitud; }

    public int getRadioMetros() { return radioMetros; }
    public void setRadioMetros(int radioMetros) { this.radioMetros = radioMetros; }

    public boolean isLeida() { return leida; }
    public void setLeida(boolean leida) { this.leida = leida; }

    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

    public OffsetDateTime getExpiraEn() { return expiraEn; }
    public void setExpiraEn(OffsetDateTime expiraEn) { this.expiraEn = expiraEn; }
}