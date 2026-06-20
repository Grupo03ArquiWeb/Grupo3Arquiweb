package pe.edu.upc.wasiseguro.dtos;

public class VozResponseDTO {

    private String comandoDetectado;
    private String mensaje;
    private String accion;

    public VozResponseDTO() {
    }

    public VozResponseDTO(String comandoDetectado, String mensaje, String accion) {
        this.comandoDetectado = comandoDetectado;
        this.mensaje = mensaje;
        this.accion = accion;
    }

    public String getComandoDetectado() {
        return comandoDetectado;
    }

    public void setComandoDetectado(String comandoDetectado) {
        this.comandoDetectado = comandoDetectado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
}
