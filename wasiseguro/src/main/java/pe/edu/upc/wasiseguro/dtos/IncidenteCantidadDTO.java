package pe.edu.upc.wasiseguro.dtos;

public class IncidenteCantidadDTO {
    private String tipo;
    private String estado;
    private Long cantidad;

    public IncidenteCantidadDTO() {}

    public IncidenteCantidadDTO(String tipo, String estado, Long cantidad) {
        this.tipo = tipo;
        this.estado = estado;
        this.cantidad = cantidad;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Long getCantidad() { return cantidad; }
    public void setCantidad(Long cantidad) { this.cantidad = cantidad; }
}