package pe.edu.upc.wasiseguro.dtos;

import java.time.LocalDateTime;

public interface EventoRecienteDTO {
    String getNombre();
    String getApellido();
    String getEmail();
    String getTelefono();
    Double getLatitud();
    Double getLongitud();
    String getMensajeExtra();
    Boolean getAtendido();
    LocalDateTime getCreadoEn();
}
