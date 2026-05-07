package pe.edu.upc.wasiseguro.dtos;

public class RutaComparacionDTO {
    private String nombreDestino;
    private double tiempoRapida;
    private double tiempoSegura;
    private String tiempoExtra;

    public String getNombreDestino() { return nombreDestino; }
    public void setNombreDestino(String nombreDestino) { this.nombreDestino = nombreDestino; }
    public double getTiempoRapida() { return tiempoRapida; }
    public void setTiempoRapida(double tiempoRapida) { this.tiempoRapida = tiempoRapida; }
    public double getTiempoSegura() { return tiempoSegura; }
    public void setTiempoSegura(double tiempoSegura) { this.tiempoSegura = tiempoSegura; }
    public String getTiempoExtra() { return tiempoExtra; }
    public void setTiempoExtra(String tiempoExtra) { this.tiempoExtra = tiempoExtra; }
}