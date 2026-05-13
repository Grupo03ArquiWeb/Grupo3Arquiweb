package pe.edu.upc.wasiseguro.dtos;

import java.time.OffsetDateTime;

public class IncidenteRankingDTO {
    private String nombre;
    private String email;
    private Long cantidad;
    private OffsetDateTime primeraVez;
    private OffsetDateTime ultimaVez;

    public IncidenteRankingDTO(String nombre, String email, Long cantidad, OffsetDateTime primeraVez, OffsetDateTime ultimaVez) {
        this.nombre = nombre;
        this.email = email;
        this.cantidad = cantidad;
        this.primeraVez = primeraVez;
        this.ultimaVez = ultimaVez;
    }

    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public Long getCantidad() { return cantidad; }
    public OffsetDateTime getPrimeraVez() { return primeraVez; }
    public OffsetDateTime getUltimaVez() { return ultimaVez; }
}