package pe.edu.upc.wasiseguro.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "zona_riesgo")
public class ZonaRiesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zona_riesgo")
    private int idZonaRiesgo;

    @Column(name = "nombre_zona_riesgo", length = 20, nullable = false)
    private String nombreZonaRiesgo;

    @Column(name = "descripcion_zona_riesgo", length = 20, nullable = false)
    private String descripcionZonaRiesgo;

    @ManyToOne
    @JoinColumn(name = "id_nivel_riesgo", nullable = false)
    private NivelRiesgo nivelRiesgo;

    @Column(name = "geom_zona_riesgo", length = 20, nullable = false)
    private String geomZonaRiesgo;

    @Column(name = "riesgopor_hora", length = 20, nullable = false)
    private String riesgoporHora;

    @Column(name = "actividad_zona_riesgo", nullable = false)
    private boolean activoZonaRiesgo;

    @Column(name = "created_zona_riesgo", nullable = false)
    private LocalDate createdAtZonaRiesgo = LocalDate.now();

    @Column(name = "update_zona_riesgo", nullable = false)
    private LocalDate updateAtZonaRiesgo;

    public ZonaRiesgo() {
    }

    public ZonaRiesgo(int idZonaRiesgo, String nombreZonaRiesgo, String descripcionZonaRiesgo,
                      NivelRiesgo nivelRiesgo, String geomZonaRiesgo, String riesgoporHora,
                      boolean activoZonaRiesgo, LocalDate createdAtZonaRiesgo,
                      LocalDate updateAtZonaRiesgo) {
        this.idZonaRiesgo = idZonaRiesgo;
        this.nombreZonaRiesgo = nombreZonaRiesgo;
        this.descripcionZonaRiesgo = descripcionZonaRiesgo;
        this.nivelRiesgo = nivelRiesgo;
        this.geomZonaRiesgo = geomZonaRiesgo;
        this.riesgoporHora = riesgoporHora;
        this.activoZonaRiesgo = activoZonaRiesgo;
        this.createdAtZonaRiesgo = createdAtZonaRiesgo;
        this.updateAtZonaRiesgo = updateAtZonaRiesgo;
    }

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

    public NivelRiesgo getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(NivelRiesgo nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
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