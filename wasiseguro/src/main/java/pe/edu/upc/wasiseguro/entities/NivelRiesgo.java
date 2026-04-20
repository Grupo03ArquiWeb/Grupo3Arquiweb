package pe.edu.upc.wasiseguro.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Nivel_Riesgo")
public class NivelRiesgo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNivelRiesgo;
    @Column(name = "nameNivelRiesgo", length = 20, nullable = false)
    private String nameNivelRiesgo;
    @Column(name = "colorHexNivelRiesgo", length = 20, nullable = false)
    private String colorHexNivelRiesgo;
    @Column(name = "ordenNivelRiesgo", length = 20, nullable = false)
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
