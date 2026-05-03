package pe.edu.upc.wasiseguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.entities.Alerta;

import java.util.List;
import java.util.UUID;

@Repository
public interface IAlertaRepository extends JpaRepository<Alerta, UUID> {

    @Query("SELECT a FROM Alerta a WHERE a.usuario.id = :idUsuario AND a.leida = false")
    List<Alerta> buscarNoLeidasPorUsuario(@Param("idUsuario") UUID idUsuario);

    @Query("SELECT a FROM Alerta a WHERE a.activa = true AND a.expiraEn > CURRENT_TIMESTAMP")
    List<Alerta> buscarAlertasVigentes();
}