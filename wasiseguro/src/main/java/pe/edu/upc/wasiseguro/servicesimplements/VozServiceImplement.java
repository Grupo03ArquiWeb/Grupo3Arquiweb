package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.api17732.dtos.VozResponseDTO;
import pe.edu.upc.api17732.servicesinterfaces.IVozService;

@Service
public class VozServiceImplement implements IVozService {

    @Override
    public VozResponseDTO procesarComando(String texto) {

        if (texto == null || texto.trim().isEmpty()) {
            return new VozResponseDTO(
                    "SIN_COMANDO",
                    "No se reconoció ningún comando de voz.",
                    "NINGUNA"
            );
        }

        String comando = texto.toLowerCase();

        if (comando.contains("ruta segura") || comando.contains("rutas seguras")) {
            return new VozResponseDTO(
                    "RUTA_SEGURA",
                    "Se buscará la ruta más segura disponible.",
                    "/api/ruta/sugerir-rutas-seguras"
            );
        }

        if (comando.contains("alerta")) {
            return new VozResponseDTO(
                    "ACTIVAR_ALERTA",
                    "Se activará la opción de alerta.",
                    "/api/alerta"
            );
        }

        if (comando.contains("compartir ubicación") || comando.contains("compartir ubicacion")) {
            return new VozResponseDTO(
                    "COMPARTIR_UBICACION",
                    "Se abrirá la opción para compartir ubicación.",
                    "/api/sesion-ubicacion-compartida"
            );
        }

        if (comando.contains("zonas de riesgo") || comando.contains("zona de riesgo")) {
            return new VozResponseDTO(
                    "ZONAS_RIESGO",
                    "Se mostrará la información de zonas de riesgo.",
                    "/api/zona-riesgo"
            );
        }

        if (comando.contains("cancelar")) {
            return new VozResponseDTO(
                    "CANCELAR",
                    "Operación cancelada.",
                    "NINGUNA"
            );
        }

        return new VozResponseDTO(
                "NO_RECONOCIDO",
                "No se reconoció el comando indicado.",
                "NINGUNA"
        );
    }
}