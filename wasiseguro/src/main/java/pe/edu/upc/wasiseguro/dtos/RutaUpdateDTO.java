package pe.edu.upc.wasiseguro.dtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class RutaUpdateDTO {
    private UUID idUsuario;
    private double origenLat;
    private double origenLng;
    private double destinoLat;
    private double destinoLng;
    private String nombreOrigen;
    private String nombreDestino;
    private BigDecimal distanciaKm;
    private BigDecimal duracionMin;
    private int idNivelRiesgo;
    private String geojsonTrayecto;
    private boolean esPublica;
    private boolean esFavorita;
    private List<String> indicaciones;

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getOrigenLat() {
        return origenLat;
    }

    public void setOrigenLat(double origenLat) {
        this.origenLat = origenLat;
    }

    public double getOrigenLng() {
        return origenLng;
    }

    public void setOrigenLng(double origenLng) {
        this.origenLng = origenLng;
    }

    public double getDestinoLat() {
        return destinoLat;
    }

    public void setDestinoLat(double destinoLat) {
        this.destinoLat = destinoLat;
    }

    public double getDestinoLng() {
        return destinoLng;
    }

    public void setDestinoLng(double destinoLng) {
        this.destinoLng = destinoLng;
    }

    public String getNombreOrigen() {
        return nombreOrigen;
    }

    public void setNombreOrigen(String nombreOrigen) {
        this.nombreOrigen = nombreOrigen;
    }

    public String getNombreDestino() {
        return nombreDestino;
    }

    public void setNombreDestino(String nombreDestino) {
        this.nombreDestino = nombreDestino;
    }

    public BigDecimal getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(BigDecimal distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public BigDecimal getDuracionMin() {
        return duracionMin;
    }

    public void setDuracionMin(BigDecimal duracionMin) {
        this.duracionMin = duracionMin;
    }

    public int getIdNivelRiesgo() {
        return idNivelRiesgo;
    }

    public void setIdNivelRiesgo(int idNivelRiesgo) {
        this.idNivelRiesgo = idNivelRiesgo;
    }

    public String getGeojsonTrayecto() {
        return geojsonTrayecto;
    }

    public void setGeojsonTrayecto(String geojsonTrayecto) {
        this.geojsonTrayecto = geojsonTrayecto;
    }

    public boolean isEsPublica() {
        return esPublica;
    }

    public void setEsPublica(boolean esPublica) {
        this.esPublica = esPublica;
    }

    public boolean isEsFavorita() {
        return esFavorita;
    }

    public void setEsFavorita(boolean esFavorita) {
        this.esFavorita = esFavorita;
    }

    public List<String> getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(List<String> indicaciones) {
        this.indicaciones = indicaciones;
    }
}