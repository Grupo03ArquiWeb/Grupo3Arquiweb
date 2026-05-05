package pe.edu.upc.wasiseguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.entities.Rol;

import java.util.List;

@Repository
public interface IRolRepository extends JpaRepository<Rol,Integer> {
    // Filtros
    List<Rol> findByNombreContainingIgnoreCase(String nombre);
    List<Rol> findByActivo(boolean activo);

    // Query
    @Query("""
        SELECT r.nombre AS rol,
               COUNT(u.id) AS totalUsuariosActivos
        FROM Rol r
        LEFT JOIN Usuario u ON u.rol = r AND u.activo = true
        GROUP BY r.id, r.nombre
        ORDER BY totalUsuariosActivos DESC
    """)
    List<Object[]> contarUsuariosActivosPorRol();
}