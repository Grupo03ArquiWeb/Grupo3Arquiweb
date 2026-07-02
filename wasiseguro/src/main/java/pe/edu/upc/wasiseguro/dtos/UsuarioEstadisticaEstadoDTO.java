package pe.edu.upc.wasiseguro.dtos;

public class UsuarioEstadisticaEstadoDTO {
    private String estado; // "Activo" / "Inactivo"
    private Long cantidad;

    public UsuarioEstadisticaEstadoDTO() {}
    public UsuarioEstadisticaEstadoDTO(String estado, Long cantidad) {
        this.estado = estado;
        this.cantidad = cantidad;
    }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Long getCantidad() { return cantidad; }
    public void setCantidad(Long cantidad) { this.cantidad = cantidad; }
}