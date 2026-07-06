package pe.edu.upc.wasiseguro.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "nivel_riesgo")
public class NivelRiesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nivel_riesgo")
    private int idNivelRiesgo;

    @Column(name = "name_nivel_riesgo", length = 30, nullable = false, unique = true)
    private String nameNivelRiesgo;

    @Column(name = "color_hex_nivel_riesgo", length = 7, nullable = false)
    private String colorHexNivelRiesgo;

    @Column(name = "orden_nivel_riesgo", nullable = false)
    private int ordenNivelRiesgo;

    public NivelRiesgo() {
    }

    public NivelRiesgo(int idNivelRiesgo, String nameNivelRiesgo, String colorHexNivelRiesgo, int ordenNivelRiesgo) {
        this.idNivelRiesgo = idNivelRiesgo;
        this.nameNivelRiesgo = nameNivelRiesgo;
        this.colorHexNivelRiesgo = colorHexNivelRiesgo;
        this.ordenNivelRiesgo = ordenNivelRiesgo;
    }

    public int getIdNivelRiesgo() {
        return idNivelRiesgo;
    }

    public void setIdNivelRiesgo(int idNivelRiesgo) {
        this.idNivelRiesgo = idNivelRiesgo;
    }

    public String getNameNivelRiesgo() {
        return nameNivelRiesgo;
    }

    public void setNameNivelRiesgo(String nameNivelRiesgo) {
        this.nameNivelRiesgo = nameNivelRiesgo;
    }

    public String getColorHexNivelRiesgo() {
        return colorHexNivelRiesgo;
    }

    public void setColorHexNivelRiesgo(String colorHexNivelRiesgo) {
        this.colorHexNivelRiesgo = colorHexNivelRiesgo;
    }

    public int getOrdenNivelRiesgo() {
        return ordenNivelRiesgo;
    }

    public void setOrdenNivelRiesgo(int ordenNivelRiesgo) {
        this.ordenNivelRiesgo = ordenNivelRiesgo;
    }
}