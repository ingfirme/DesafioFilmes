package criticadefilmes.ProjetoIngrid.repository;

import criticadefilmes.ProjetoIngrid.entities.RespostaComentarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RespostaComentarioRepository extends JpaRepository <RespostaComentarioEntity, Integer>{
    @Modifying
    @Query(nativeQuery=true, value="DELETE FROM resposta_comentarios WHERE ID_COMENTARIO = :id_comentario")
    void deleteById(Integer id_comentario);
}
