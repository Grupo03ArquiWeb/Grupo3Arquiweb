package pe.edu.upc.wasiseguro.dtos;

import java.util.UUID;

public interface ResumenEventoPanicoDTO {
    UUID getIdUsuario();
    String getNombre();
    String getApellido();
    String getEmail();
    Long getTotalEventos();
    Long getTotalAtendidos();
    Long getTotalPendientes();
}
