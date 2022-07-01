package criticadefilmes.ProjetoIngrid.repository;

import criticadefilmes.ProjetoIngrid.entities.ComentarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository <ComentarioEntity, Integer>{

    List<ComentarioEntity> findByTitulo(String titulo);

    @Modifying
    @Query(nativeQuery=true, value="DELETE FROM comentarios WHERE ID_COMENTARIO = :id_comentario")
    void deleteById(Integer id_comentario);
}
