package criticadefilmes.ProjetoIngrid.service;


import criticadefilmes.ProjetoIngrid.entities.NotaEntity;
import criticadefilmes.ProjetoIngrid.entities.UsuarioEntity;

import criticadefilmes.ProjetoIngrid.repository.NotaRepository;
import criticadefilmes.ProjetoIngrid.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NotaService {

    private final NotaRepository notaRepository;
    private final UserService userService;

    public NotaEntity salvar(NotaEntity notaEntity, UsuarioEntity usuario) {

            NotaEntity salvarNota = notaRepository.save(new NotaEntity(
                    null,
                    notaEntity.getTitulo(),
                    usuario.getNickname(),
                    notaEntity.getNota()));

            userService.adicionarPontuacaoNota(usuario);

            return salvarNota;
     }

    public List<Optional<NotaEntity>> buscaNota(Integer titulo){
        return notaRepository.findByNickname(titulo);
    }
}
