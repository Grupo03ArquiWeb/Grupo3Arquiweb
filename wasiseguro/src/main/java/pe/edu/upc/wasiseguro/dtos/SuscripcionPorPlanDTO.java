package pe.edu.upc.wasiseguro.dtos;

public class SuscripcionPorPlanDTO {
    private String nombrePlan;
    private Long cantidad;

    public SuscripcionPorPlanDTO() {
    }

    public SuscripcionPorPlanDTO(String nombrePlan, Long cantidad) {
        this.nombrePlan = nombrePlan;
        this.cantidad = cantidad;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}