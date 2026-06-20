package pe.edu.upc.wasiseguro.dtos;

import java.util.UUID;

public class RutaSugeridaDTO {
    private UUID id;
    private String nombreOrigen;
    private String nombreDestino;
    private String geojsonTrayecto;
    private boolean esSegura;
    private Double seguridadScore;

    public RutaSugeridaDTO() {}

    public RutaSugeridaDTO(UUID id, String nombreOrigen, String nombreDestino, String geojsonTrayecto, boolean esSegura, Double seguridadScore) {
        this.id = id;
        this.nombreOrigen = nombreOrigen;
        this.nombreDestino = nombreDestino;
        this.geojsonTrayecto = geojsonTrayecto;
        this.esSegura = esSegura;
        this.seguridadScore = seguridadScore;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNombreOrigen() { return nombreOrigen; }
    public void setNombreOrigen(String nombreOrigen) { this.nombreOrigen = nombreOrigen; }
    public String getNombreDestino() { return nombreDestino; }
    public void setNombreDestino(String nombreDestino) { this.nombreDestino = nombreDestino; }
    public String getGeojsonTrayecto() { return geojsonTrayecto; }
    public void setGeojsonTrayecto(String geojsonTrayecto) { this.geojsonTrayecto = geojsonTrayecto; }
    public boolean isEsSegura() { return esSegura; }
    public void setEsSegura(boolean esSegura) { this.esSegura = esSegura; }
    public Double getSeguridadScore() { return seguridadScore; }
    public void setSeguridadScore(Double seguridadScore) { this.seguridadScore = seguridadScore; }
}