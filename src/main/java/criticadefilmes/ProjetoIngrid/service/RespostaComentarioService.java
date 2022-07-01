package criticadefilmes.ProjetoIngrid.service;


import criticadefilmes.ProjetoIngrid.entities.RespostaComentarioEntity;
import criticadefilmes.ProjetoIngrid.entities.UsuarioEntity;
import criticadefilmes.ProjetoIngrid.repository.RespostaComentarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RespostaComentarioService {

    private final RespostaComentarioRepository respostaComentarioRepository;
    private final UserService userService;


    public RespostaComentarioEntity salvar(Integer id_comentario, RespostaComentarioEntity respostaComentario, UsuarioEntity usuario) {

        RespostaComentarioEntity salvarComentario = respostaComentarioRepository.save(
                new RespostaComentarioEntity(
                null,
                        usuario.getNickname(),
                        id_comentario,
                        respostaComentario.getComentario()));

        userService.adicionarPontuacaoResposta(usuario);

        return salvarComentario;
    }
}
