package pe.edu.upc.wasiseguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.entities.EventoPanico;

@Repository
public interface IEventoPanicoRepository extends JpaRepository<EventoPanico,Integer> {
}