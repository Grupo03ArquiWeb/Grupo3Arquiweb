package pe.edu.upc.wasiseguro.dtos;

public class TipoIncidentePorEstadoDTO {
    private boolean activo;
    private long cantidad;

    public TipoIncidentePorEstadoDTO() {
    }

    public TipoIncidentePorEstadoDTO(boolean activo, long cantidad) {
        this.activo = activo;
        this.cantidad = cantidad;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }
}