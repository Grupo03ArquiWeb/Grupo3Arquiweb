package pe.edu.upc.wasiseguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.dtos.EventoRecienteDTO;
import pe.edu.upc.wasiseguro.dtos.ResumenEventoPanicoDTO;
import pe.edu.upc.wasiseguro.entities.EventoPanico;

import java.util.List;
import java.util.UUID;

@Repository
public interface IEventoPanicoRepository extends JpaRepository<EventoPanico, UUID> {
    // Filtros
    List<EventoPanico> findByAtendido(boolean atendido);
    List<EventoPanico> findByUsuarioId(UUID idUsuario);

    @Query("""
        SELECT
            u.id               AS idUsuario,
            u.nombre           AS nombre,
            u.apellido         AS apellido,
            u.email            AS email,
            COUNT(ep.id)                                           AS totalEventos,
            SUM(CASE WHEN ep.atendido = true  THEN 1 ELSE 0 END)  AS totalAtendidos,
            SUM(CASE WHEN ep.atendido = false THEN 1 ELSE 0 END)  AS totalPendientes
        FROM EventoPanico ep
        JOIN ep.usuario u
        GROUP BY u.id, u.nombre, u.apellido, u.email
        ORDER BY totalEventos DESC
    """)
    List<ResumenEventoPanicoDTO> resumenEventosPorUsuario();

    @Query(value = """
        SELECT
            u.nombre            AS nombre,
            u.apellido          AS apellido,
            u.email             AS email,
            u.telefono          AS telefono,
            ep.latitud          AS latitud,
            ep.longitud         AS longitud,
            ep.mensaje_extra    AS mensajeExtra,
            ep.atendido         AS atendido,
            ep.created_at       AS creadoEn
        FROM evento_panico ep
        INNER JOIN usuario u ON ep.id_usuario = u.id
        ORDER BY ep.created_at DESC
        LIMIT :limite
    """, nativeQuery = true)
    List<EventoRecienteDTO> eventosRecientesConDatosUsuario(@Param("limite") int limite);
}