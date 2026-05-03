package pe.edu.upc.wasiseguro.dtos;

import pe.edu.upc.wasiseguro.entities.NivelRiesgo;

import java.time.LocalDate;

public class ZonaRiesgoListDTO {
    private int idZonaRiesgo;
    private String nombreZonaRiesgo;
    private String descripcionZonaRiesgo;
    private int  idNivelRiesgo;
    private String geomZonaRiesgo;
    private String riesgoporHora;
    private boolean activoZonaRiesgo;
    private LocalDate createdAtZonaRiesgo;
    private LocalDate updateAtZonaRiesgo;

    public int getIdZonaRiesgo() {
        return idZonaRiesgo;
    }

    public void setIdZonaRiesgo(int idZonaRiesgo) {
        this.idZonaRiesgo = idZonaRiesgo;
    }

    public String getNombreZonaRiesgo() {
        return nombreZonaRiesgo;
    }

    public void setNombreZonaRiesgo(String nombreZonaRiesgo) {
        this.nombreZonaRiesgo = nombreZonaRiesgo;
    }

    public String getDescripcionZonaRiesgo() {
        return descripcionZonaRiesgo;
    }

    public void setDescripcionZonaRiesgo(String descripcionZonaRiesgo) {
        this.descripcionZonaRiesgo = descripcionZonaRiesgo;
    }

    public int getIdNivelRiesgo() {
        return idNivelRiesgo;
    }

    public void setIdNivelRiesgo(int idNivelRiesgo) {
        this.idNivelRiesgo = idNivelRiesgo;
    }

    public String getGeomZonaRiesgo() {
        return geomZonaRiesgo;
    }

    public void setGeomZonaRiesgo(String geomZonaRiesgo) {
        this.geomZonaRiesgo = geomZonaRiesgo;
    }

    public String getRiesgoporHora() {
        return riesgoporHora;
    }

    public void setRiesgoporHora(String riesgoporHora) {
        this.riesgoporHora = riesgoporHora;
    }

    public boolean isActivoZonaRiesgo() {
        return activoZonaRiesgo;
    }

    public void setActivoZonaRiesgo(boolean activoZonaRiesgo) {
        this.activoZonaRiesgo = activoZonaRiesgo;
    }

    public LocalDate getCreatedAtZonaRiesgo() {
        return createdAtZonaRiesgo;
    }

    public void setCreatedAtZonaRiesgo(LocalDate createdAtZonaRiesgo) {
        this.createdAtZonaRiesgo = createdAtZonaRiesgo;
    }

    public LocalDate getUpdateAtZonaRiesgo() {
        return updateAtZonaRiesgo;
    }

    public void setUpdateAtZonaRiesgo(LocalDate updateAtZonaRiesgo) {
        this.updateAtZonaRiesgo = updateAtZonaRiesgo;
    }
}