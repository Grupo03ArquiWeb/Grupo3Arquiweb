package pe.edu.upc.wasiseguro.dtos;

import java.time.OffsetDateTime;

public class IncidenteCantidadDTO {
    private String tipo;
    private String estado;
    private Long cantidad;
    private OffsetDateTime fechaReciente;
    private OffsetDateTime fechaAntigua;

    public IncidenteCantidadDTO() {}

    public IncidenteCantidadDTO(String tipo, String estado, Long cantidad, OffsetDateTime fechaReciente, OffsetDateTime fechaAntigua) {
        this.tipo = tipo;
        this.estado = estado;
        this.cantidad = cantidad;
        this.fechaReciente = fechaReciente;
        this.fechaAntigua = fechaAntigua;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Long getCantidad() { return cantidad; }
    public void setCantidad(Long cantidad) { this.cantidad = cantidad; }
    public OffsetDateTime getFechaReciente() { return fechaReciente; }
    public void setFechaReciente(OffsetDateTime fechaReciente) { this.fechaReciente = fechaReciente; }
    public OffsetDateTime getFechaAntigua() { return fechaAntigua; }
    public void setFechaAntigua(OffsetDateTime fechaAntigua) { this.fechaAntigua = fechaAntigua; }
}