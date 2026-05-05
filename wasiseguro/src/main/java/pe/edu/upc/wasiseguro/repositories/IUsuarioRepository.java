package pe.edu.upc.wasiseguro.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.entities.Usuario;

import java.util.List;
import java.util.UUID;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, UUID> {

    // Filtros
    Usuario findByEmail(String email);
    boolean existsByEmail(String email);
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT u FROM Usuario u WHERE u.rol.nombre = :nombreRol")
    List<Usuario> buscarUsuariosPorRol(@Param("nombreRol") String nombreRol);

    @Query("SELECT u FROM Usuario u WHERE u.email LIKE %:dominioEmail%")
    List<Usuario> buscarUsuariosPorDominioEmail(@Param("dominioEmail") String dominioEmail);

    // Query
    @Query("""
        SELECT u FROM Usuario u
        WHERE u.activo = true
          AND u.updatedAt < CURRENT_TIMESTAMP - :dias * 1 DAY
        ORDER BY u.updatedAt ASC
    """)
    List<Usuario> buscarUsuariosInactivos(@Param("dias") int dias);
}
