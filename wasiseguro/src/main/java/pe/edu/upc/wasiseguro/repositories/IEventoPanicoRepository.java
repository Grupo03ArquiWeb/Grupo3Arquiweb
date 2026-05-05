package pe.edu.upc.wasiseguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.entities.EventoPanico;

import java.util.List;
import java.util.UUID;

@Repository
public interface IEventoPanicoRepository extends JpaRepository<EventoPanico, UUID> {
    // Filtros
    List<EventoPanico> findByAtendido(boolean atendido);
    List<EventoPanico> findByUsuarioId(UUID idUsuario);
}