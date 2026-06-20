package pe.edu.upc.wasiseguro.dtos;

public class VozRequestDTO {
    private String texto;

    public VozRequestDTO() {
    }

    public VozRequestDTO(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
