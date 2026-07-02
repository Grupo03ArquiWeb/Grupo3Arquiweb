package pe.edu.upc.wasiseguro.dtos;

public class UsuarioEstadisticaIdiomaDTO {
    private String idioma;
    private Long cantidad;

    public UsuarioEstadisticaIdiomaDTO() {}
    public UsuarioEstadisticaIdiomaDTO(String idioma, Long cantidad) {
        this.idioma = idioma;
        this.cantidad = cantidad;
    }
    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    public Long getCantidad() { return cantidad; }
    public void setCantidad(Long cantidad) { this.cantidad = cantidad; }
}