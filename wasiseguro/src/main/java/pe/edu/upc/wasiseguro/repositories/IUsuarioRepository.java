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

//Usuario findByEmail(String email); sirve para facebook tmb
    // Filtros
    Usuario findByEmail(String email);
    boolean existsByEmail(String email);
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT u FROM Usuario u WHERE u.rol.nombre = :nombreRol")
    List<Usuario> buscarUsuariosPorRol(@Param("nombreRol") String nombreRol);

    @Query("SELECT u FROM Usuario u WHERE u.email LIKE CONCAT('%@', :dominioEmail)")
    List<Usuario> buscarUsuariosPorDominioEmail(@Param("dominioEmail") String dominioEmail);

    // Query
    @Query(value = """
    SELECT *
    FROM usuario u
    WHERE u.activo = true
      AND u.updated_at < NOW() - (:dias * INTERVAL '1 day')
    ORDER BY u.updated_at ASC
""", nativeQuery = true)
    List<Usuario> buscarUsuariosInactivos(@Param("dias") int dias);

    @Query("SELECT CASE WHEN u.activo = true THEN 'Activo' ELSE 'Inactivo' END, COUNT(u) " +
            "FROM Usuario u GROUP BY u.activo")
    List<Object[]> estadisticasPorEstado();

    @Query("SELECT u.idioma, COUNT(u) FROM Usuario u GROUP BY u.idioma")
    List<Object[]> estadisticasPorIdioma();
}
