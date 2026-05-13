package pe.edu.upc.wasiseguro.dtos;
import java.util.UUID;

public interface RutasFavoritasDTO {
    UUID getId();
    String getNombreOrigen();
    String getNombreDestino();
    boolean getEsFavorita();
}