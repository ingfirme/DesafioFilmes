package criticadefilmes.ProjetoIngrid.repository;

import criticadefilmes.ProjetoIngrid.entities.CitacaoComentarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CitacaoComentarioRepository extends JpaRepository <CitacaoComentarioEntity, Integer>{
    @Modifying
    @Query(nativeQuery=true, value="DELETE FROM citar_comentario WHERE ID_COMENTARIO = :id_comentario")
    void deleteById(Integer id_comentario);

}
