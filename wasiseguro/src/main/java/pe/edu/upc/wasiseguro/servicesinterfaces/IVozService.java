package pe.edu.upc.wasiseguro.servicesinterfaces;

import pe.edu.upc.api17732.dtos.VozResponseDTO;

public interface IVozService {
    VozResponseDTO procesarComando(String texto);
}
