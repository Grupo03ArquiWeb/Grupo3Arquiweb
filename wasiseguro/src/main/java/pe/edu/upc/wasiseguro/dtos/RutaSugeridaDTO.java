package pe.edu.upc.wasiseguro.dtos;
import java.util.UUID;

public interface RutaSugeridaDTO {
    UUID getId();
    String getNombreOrigen();
    String getNombreDestino();
    String getGeojsonTrayecto();
    Boolean getEsSegura();
    Double getSeguridadScore();
}