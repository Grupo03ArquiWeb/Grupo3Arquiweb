package pe.edu.upc.wasiseguro.dtos;

public class TipoIncidenteEstadisticasDTO {
    private String nombre;
    private String descripcion;
    private Long cantidadIncidentes;
    private Long incidentesResueltos;
    private Long incidentesPendientes;

    public TipoIncidenteEstadisticasDTO() {}

    public TipoIncidenteEstadisticasDTO(String nombre, String descripcion,
                                         Long cantidadIncidentes, Long incidentesResueltos,
                                         Long incidentesPendientes) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidadIncidentes = cantidadIncidentes;
        this.incidentesResueltos = incidentesResueltos;
        this.incidentesPendientes = incidentesPendientes;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Long getCantidadIncidentes() { return cantidadIncidentes; }
    public void setCantidadIncidentes(Long cantidadIncidentes) { this.cantidadIncidentes = cantidadIncidentes; }

    public Long getIncidentesResueltos() { return incidentesResueltos; }
    public void setIncidentesResueltos(Long incidentesResueltos) { this.incidentesResueltos = incidentesResueltos; }

    public Long getIncidentesPendientes() { return incidentesPendientes; }
    public void setIncidentesPendientes(Long incidentesPendientes) { this.incidentesPendientes = incidentesPendientes; }
}