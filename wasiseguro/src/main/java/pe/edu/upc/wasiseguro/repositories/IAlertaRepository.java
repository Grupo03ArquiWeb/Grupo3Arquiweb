package pe.edu.upc.wasiseguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.entities.Alerta;

import java.util.UUID;

@Repository
public interface IAlertaRepository extends JpaRepository<Alerta, UUID> {
}