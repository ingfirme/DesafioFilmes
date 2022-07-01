package criticadefilmes.ProjetoIngrid.repository;

import criticadefilmes.ProjetoIngrid.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <UsuarioEntity, String>{
    UsuarioEntity findByEmail(String email);

    @Modifying
    @Query(value = "update dados_usuario set id_perfil = 4 where nickname = :nickname", nativeQuery=true)
    void atualizaPerfil(Integer nickname);
}
