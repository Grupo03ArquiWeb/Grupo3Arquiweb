package pe.edu.upc.wasiseguro.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wasiseguro.entities.ZonaFavorita;

import java.util.List;

@Repository
public interface IZonaFavoritaRepository extends JpaRepository<ZonaFavorita,Integer> {

    public List<ZonaFavorita> findByUsuarioId(java.util.UUID idUsuario);

}