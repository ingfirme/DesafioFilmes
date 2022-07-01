package criticadefilmes.ProjetoIngrid.service;


import criticadefilmes.ProjetoIngrid.entities.ReacaoEntity;
import criticadefilmes.ProjetoIngrid.entities.UsuarioEntity;
import criticadefilmes.ProjetoIngrid.repository.ReacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReacaoService {

    private final ReacaoRepository reacaoRepository;


    public ReacaoEntity salvar(ReacaoEntity reacao, UsuarioEntity usuario)  {

        ReacaoEntity reacaoComentario = reacaoRepository.save(new ReacaoEntity(
                null,
                reacao.getId_comentario(),
                usuario.getNickname(),
                reacao.getReacao()));

            return reacaoComentario;
    }

    public List<ReacaoEntity> findByNickname(Integer nickname){
        return reacaoRepository.findByNickname(nickname);
    }

}
