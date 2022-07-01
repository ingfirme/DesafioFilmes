package criticadefilmes.ProjetoIngrid.repository;

import criticadefilmes.ProjetoIngrid.entities.ReacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReacaoRepository extends JpaRepository <ReacaoEntity, Integer>{
    List<ReacaoEntity> findByNickname(Integer nickname);
    @Modifying
    @Query(nativeQuery=true, value="DELETE FROM reacoes WHERE ID_COMENTARIO = :id_comentario")
    void deleteById(Integer id_comentario);

}
