package pe.edu.upc.wasiseguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.entities.Alerta;
import java.util.List;
import java.util.UUID;

@Repository
public interface IAlertaRepository extends JpaRepository<Alerta, UUID> {

    // Listar alertas no leídas por usuario
    List<Alerta> findByUsuarioIdAndLeidaFalse(UUID idUsuario);

    // Consulta personalizada para alertas vigentes (US17)
    @Query("SELECT a FROM Alerta a WHERE a.activa = true AND a.expiraEn > CURRENT_TIMESTAMP")
    List<Alerta> buscarAlertasVigentes();
}