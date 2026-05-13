package pe.edu.upc.wasiseguro.dtos;

public class RutaSeguridadDTO {
    private String nombreDestino;
    private Double promedioSeguridad;
    private Long totalCalificaciones;
    private Integer calificacionMaxima;
    private Integer calificacionMinima;

    public RutaSeguridadDTO() {}

    public RutaSeguridadDTO(String nombreDestino, Double promedioSeguridad, Long totalCalificaciones, Short calificacionMaxima, Short calificacionMinima) {
        this.nombreDestino = nombreDestino;
        this.promedioSeguridad = promedioSeguridad;
        this.totalCalificaciones = totalCalificaciones;
        this.calificacionMaxima = calificacionMaxima != null ? calificacionMaxima.intValue() : 0;
        this.calificacionMinima = calificacionMinima != null ? calificacionMinima.intValue() : 0;
    }

    public String getNombreDestino() { return nombreDestino; }
    public void setNombreDestino(String nombreDestino) { this.nombreDestino = nombreDestino; }
    public Double getPromedioSeguridad() { return promedioSeguridad; }
    public void setPromedioSeguridad(Double promedioSeguridad) { this.promedioSeguridad = promedioSeguridad; }
    public Long getTotalCalificaciones() { return totalCalificaciones; }
    public void setTotalCalificaciones(Long totalCalificaciones) { this.totalCalificaciones = totalCalificaciones; }
    public Integer getCalificacionMaxima() { return calificacionMaxima; }
    public void setCalificacionMaxima(Integer calificacionMaxima) { this.calificacionMaxima = calificacionMaxima; }
    public Integer getCalificacionMinima() { return calificacionMinima; }
    public void setCalificacionMinima(Integer calificacionMinima) { this.calificacionMinima = calificacionMinima; }
}