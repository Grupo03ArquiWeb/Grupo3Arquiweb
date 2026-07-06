package pe.edu.upc.wasiseguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.entities.ZonaRiesgo;

import java.util.List;

@Repository
public interface IZonaRiesgoRepository extends JpaRepository<ZonaRiesgo, Integer> {

    List<ZonaRiesgo> findByActivoZonaRiesgoTrue();

    List<ZonaRiesgo> findByNombreZonaRiesgoContainingIgnoreCase(String nombre);

    @Query(value = """
        SELECT nr.name_nivel_riesgo, COUNT(zr.id_zona_riesgo)
        FROM zona_riesgo zr
        INNER JOIN nivel_riesgo nr
        ON zr.id_nivel_riesgo = nr.id_nivel_riesgo
        GROUP BY nr.name_nivel_riesgo
        """, nativeQuery = true)
    List<String[]> cantidadZonasPorNivel();

    @Query(value = """
        SELECT zr.nombre_zona_riesgo, zr.riesgopor_hora, nr.name_nivel_riesgo
        FROM zona_riesgo zr
        INNER JOIN nivel_riesgo nr
        ON zr.id_nivel_riesgo = nr.id_nivel_riesgo
        WHERE zr.actividad_zona_riesgo = true
        ORDER BY zr.riesgopor_hora ASC
        """, nativeQuery = true)
    List<String[]> zonasActivasOrdenadasPorRiesgoHora();
}