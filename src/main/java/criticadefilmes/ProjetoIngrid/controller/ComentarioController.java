package criticadefilmes.ProjetoIngrid.controller;

import criticadefilmes.ProjetoIngrid.entities.*;
import criticadefilmes.ProjetoIngrid.service.ComentarioService;
import criticadefilmes.ProjetoIngrid.service.ReacaoService;
import criticadefilmes.ProjetoIngrid.service.RespostaComentarioService;
import criticadefilmes.ProjetoIngrid.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class ComentarioController {
    private final UserService userService;
    private final ComentarioService comentarioService;
    private final RespostaComentarioService respostaComentarioService;
    private final ReacaoService reacaoService;

    @PostMapping(value = "/comentario")
    public ResponseEntity salvar(@RequestBody ComentarioEntity comentario,
                                   @RequestHeader(value = "Authorization" ) String token ) throws Exception {
        try {
            UsuarioEntity usuario = userService.buscaUsuario(token);
        }catch (Exception e){
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        UsuarioEntity usuario = userService.buscaUsuario(token);
        if(usuario.getId_perfil() > 1){
            return ResponseEntity.ok().body(comentarioService.salvar(comentario, usuario));

        }

        return new ResponseEntity("Voce nao possui perfil necessario para realizar essa acao",
                HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/comentarios")
    public ResponseEntity<List<ComentarioEntity>> getComentarios(@RequestParam(name = "titulo")String titulo,
                                                    @RequestHeader(value = "Authorization" ) String token ) throws NoSuchAlgorithmException {
        try {
            UsuarioEntity usuario = userService.buscaUsuario(token);
        }catch (Exception e){
            return new ResponseEntity("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        UsuarioEntity usuario = userService.buscaUsuario(token);
        return ResponseEntity.ok().body(comentarioService.buscarComentarios(titulo));
    }

    @PostMapping(value = "/resposta_comentario")
    public ResponseEntity salvarResposta(@RequestParam (name = "id_comentario")Integer id_comentario,
                                 @RequestBody RespostaComentarioEntity respostaComentario,
                                 @RequestHeader(value = "Authorization" ) String token ) throws Exception {
        try {
            UsuarioEntity usuario = userService.buscaUsuario(token);
        }catch (Exception e){
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        UsuarioEntity usuario = userService.buscaUsuario(token);
        if(usuario.getId_perfil() > 1) {

            return ResponseEntity.ok().body(respostaComentarioService.salvar(id_comentario, respostaComentario, usuario));
        }

        return new ResponseEntity("Voce nao possui perfil necessario para realizar essa acao",
                HttpStatus.BAD_REQUEST);
    }
    @PostMapping(value = "/reacoes")
    public ResponseEntity reacao(@RequestBody ReacaoEntity reacaoEntity,
                                         @RequestHeader(value = "Authorization" ) String token ) throws Exception {
        try {
            UsuarioEntity usuario = userService.buscaUsuario(token);
        }catch (Exception e){
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        UsuarioEntity usuario = userService.buscaUsuario(token);
        List<ReacaoEntity> reacoes = reacaoService.findByNickname(usuario.getNickname());
        if (usuario.getId_perfil() > 2) {
            if (reacoes.isEmpty()) {
                return ResponseEntity.ok().body(reacaoService.salvar(reacaoEntity, usuario));
            }
            for (ReacaoEntity reacao : reacoes) {
                if (reacao.getId_comentario().equals(reacaoEntity.getId_comentario())) {
                    return new ResponseEntity("Voce ja reagiu a esse comentario",
                            HttpStatus.BAD_REQUEST);
                }
            }

            return ResponseEntity.ok().body(reacaoService.salvar(reacaoEntity, usuario));
        }

        return new ResponseEntity("Voce nao possui perfil necessario para realizar essa acao",
                HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/deletaComentario")
    public ResponseEntity deletarComentario(@RequestParam (name = "id_comentario")Integer id_comentario,
                                            @RequestHeader(value = "Authorization" ) String token ) throws Exception {
        try {
            UsuarioEntity usuario = userService.buscaUsuario(token);
        }catch (Exception e){
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        UsuarioEntity usuario = userService.buscaUsuario(token);
        if(usuario.getId_perfil() == 4) {
            if(comentarioService.existeComentario(id_comentario)){
            comentarioService.deletarComentario(id_comentario);
                return new ResponseEntity("Comentario deletado",
                        HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity("Não existe o comentario",
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Voce nao possui perfil necessario para realizar essa acao",
                HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/marcarComentarioRepetido")
    public ResponseEntity comentarioRepetido(@RequestBody ComentarioEntity comentario,
                                            @RequestHeader(value = "Authorization" ) String token ) throws Exception {
        try {
            UsuarioEntity usuario = userService.buscaUsuario(token);
        }catch (Exception e){
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        UsuarioEntity usuario = userService.buscaUsuario(token);
        if(usuario.getId_perfil() == 4) {
            if(comentarioService.existeComentario(comentario.getId_comentario())){
                comentarioService.marcaRepeditoComentario(comentario.getId_comentario());
                return new ResponseEntity("Comentario marcado como duplicado",
                        HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity("Não existe o comentario",
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Voce nao possui perfil necessario para realizar essa acao",
                HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/citarComentario")
    public ResponseEntity citarRepetido(@RequestBody CitacaoComentarioEntity comentario,
                                             @RequestHeader(value = "Authorization" ) String token ) throws Exception {
        try {
            UsuarioEntity usuario = userService.buscaUsuario(token);
        }catch (Exception e){
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        UsuarioEntity usuario = userService.buscaUsuario(token);
        if(usuario.getId_perfil() >= 3) {
            if(comentarioService.existeComentario(comentario.getId_comentario())){
                comentarioService.salvarCitacao(comentario);


                return new ResponseEntity("Citacao realizada",
                        HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity("Não existe o comentario",
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Voce nao possui perfil necessario para realizar essa acao",
                HttpStatus.BAD_REQUEST);
    }
}
