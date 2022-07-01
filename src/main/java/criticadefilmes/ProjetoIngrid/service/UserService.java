package criticadefilmes.ProjetoIngrid.service;

import criticadefilmes.ProjetoIngrid.entities.UsuarioEntity;
import criticadefilmes.ProjetoIngrid.exceptions.UsuarioDuplicadoException;
import criticadefilmes.ProjetoIngrid.repository.UserRepository;
import criticadefilmes.ProjetoIngrid.security.JWebToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
@Transactional
@Service
@AllArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    public UsuarioEntity criar(final UsuarioEntity usuarioEntity) {
        validaDuplicado(usuarioEntity);

        return userRepository.save(new UsuarioEntity(
                null,
                usuarioEntity.getNome(),
                usuarioEntity.getSobrenome(),
                usuarioEntity.getEmail(),
                usuarioEntity.getIdade(),
                usuarioEntity.getSenha(),
                0,
                0,
                0,
                0,
                1
                ));
    }

    public UsuarioEntity findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public UsuarioEntity buscaUsuario(String token ) throws NoSuchAlgorithmException {

        String email = recuperaEmail(token);
        UsuarioEntity buscarUsuario = findByEmail(email);
        return buscarUsuario;

    }
    public UsuarioEntity atualizar(UsuarioEntity user){
        return userRepository.save(user);
    }
    public String recuperaEmail(String token) throws NoSuchAlgorithmException {
        token = token.replace("Bearer","");
        JWebToken validarToken = new JWebToken(token);
        String email = validarToken.getSubject();
        return email;
    }

    private void validaDuplicado(UsuarioEntity usuario)  {
        Optional<UsuarioEntity> buscaUsuario = Optional.ofNullable(userRepository.findByEmail(usuario.getEmail()));
        if(buscaUsuario.isPresent()){
            throw new UsuarioDuplicadoException("Email dupliicado");
        }
    }

    public void adicionarPontuacaoNota(UsuarioEntity usuario) {
        int pontuacaoAtual = usuario.getQtde_nota();
        pontuacaoAtual = ++pontuacaoAtual;
        usuario.setQtde_nota(pontuacaoAtual);

        int pontuacaoTotal = usuario.getQtde_total();
        pontuacaoTotal = ++pontuacaoTotal;
        usuario.setQtde_total(pontuacaoTotal);
        alterarPerfil(usuario);

        userRepository.save(usuario);
    }
    public void adicionarPontuacaoComentario(UsuarioEntity usuario) {
        int pontuacaoAtual = usuario.getQtde_comentario();
        pontuacaoAtual = ++pontuacaoAtual;
        usuario.setQtde_comentario(pontuacaoAtual);

        int pontuacaoTotal = usuario.getQtde_total();
        pontuacaoTotal = ++pontuacaoTotal;
        usuario.setQtde_total(pontuacaoTotal);

        alterarPerfil(usuario);
        userRepository.save(usuario);
    }
    public void adicionarPontuacaoResposta(UsuarioEntity usuario) {
        int pontuacaoAtual = usuario.getQtde_resposta();
        pontuacaoAtual = ++pontuacaoAtual;
        usuario.setQtde_resposta(pontuacaoAtual);

        int pontuacaoTotal = usuario.getQtde_total();
        pontuacaoTotal = ++pontuacaoTotal;
        usuario.setQtde_total(pontuacaoTotal);
        alterarPerfil(usuario);

        userRepository.save(usuario);
    }

    public void alterarPerfil(UsuarioEntity usuario) {

        if(usuario.getId_perfil() != 4) {
            if (usuario.getQtde_total() >= 20 && usuario.getQtde_total() < 100) {
                usuario.setId_perfil(2);
                userRepository.save(usuario);
            }
            if (usuario.getQtde_total() >= 100 && usuario.getQtde_total() < 1000) {
                usuario.setId_perfil(3);
                userRepository.save(usuario);
            }
            if (usuario.getQtde_total() >= 1000) {
                usuario.setId_perfil(4);
                userRepository.save(usuario);
            }
        }
    }

    public Integer atualizaPerfil(Integer nickname) {
        userRepository.atualizaPerfil(nickname);
        return nickname;
    }
}
