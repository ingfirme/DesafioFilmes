package criticadefilmes.ProjetoIngrid.service;


import criticadefilmes.ProjetoIngrid.entities.CitacaoComentarioEntity;
import criticadefilmes.ProjetoIngrid.entities.ComentarioEntity;
import criticadefilmes.ProjetoIngrid.entities.UsuarioEntity;
import criticadefilmes.ProjetoIngrid.repository.CitacaoComentarioRepository;
import criticadefilmes.ProjetoIngrid.repository.ComentarioRepository;
import criticadefilmes.ProjetoIngrid.repository.ReacaoRepository;
import criticadefilmes.ProjetoIngrid.repository.RespostaComentarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final CitacaoComentarioRepository citacaoComentarioRepository;
    private final UserService userService;
    private final ReacaoRepository reacaoRepository;
    private final RespostaComentarioRepository respostaComentarioRepository;


    public ComentarioEntity salvar(ComentarioEntity comentarioEntity, UsuarioEntity usuario) throws Exception {

        ComentarioEntity salvarComentario = comentarioRepository.save(new ComentarioEntity(
                null,
                comentarioEntity.getTitulo(),
                usuario.getNickname(),
                comentarioEntity.getComentario(),
                false));

            userService.adicionarPontuacaoComentario(usuario);


            return salvarComentario;
    }

    public List<ComentarioEntity> buscarComentarios(String titulo){
        return comentarioRepository.findByTitulo(titulo);
    }

    public void deletarComentario(Integer id_comentario){
            reacaoRepository.deleteById(id_comentario);
            respostaComentarioRepository.deleteById(id_comentario);
            citacaoComentarioRepository.deleteById(id_comentario);
            comentarioRepository.deleteById(id_comentario);
    }

    public Boolean existeComentario(Integer id_comentario) {
            return comentarioRepository.existsById(id_comentario);
    }

    public ComentarioEntity marcaRepeditoComentario(Integer id_comentario) {
        Optional<ComentarioEntity> buscarComentario = comentarioRepository.findById(id_comentario);
                ComentarioEntity atualizaComentario = comentarioRepository.save(new ComentarioEntity(
                        buscarComentario.get().getId_comentario(),
                        buscarComentario.get().getComentario(),
                        buscarComentario.get().getNickname(),
                        buscarComentario.get().getComentario(),
                true));
        return comentarioRepository.save(atualizaComentario);
    }

    public CitacaoComentarioEntity salvarCitacao(CitacaoComentarioEntity citacaoComentarioEntity){
        return citacaoComentarioRepository.save(new CitacaoComentarioEntity(
                null,
                citacaoComentarioEntity.getId_comentario(),
                citacaoComentarioEntity.getComentario()));
    }
}
