package pe.edu.upc.wasiseguro.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.entities.PlanSuscripcion;

@Repository
public interface IPlanSuscripcionRepository extends JpaRepository<PlanSuscripcion,Integer> {
}