package pe.edu.upc.wasiseguro.dtos;

public class NivelRiesgoListDTO {

    private int idNivelRiesgo;
    private String nameNivelRiesgo;
    private String colorHexNivelRiesgo;
    private int ordenNivelRiesgo;

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
