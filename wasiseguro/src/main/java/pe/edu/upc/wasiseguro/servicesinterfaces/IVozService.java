package pe.edu.upc.wasiseguro.servicesinterfaces;

import pe.edu.upc.wasiseguro.dtos.VozResponseDTO;

public interface IVozService {
    VozResponseDTO procesarComando(String texto);
}
