package criticadefilmes.ProjetoIngrid.controller;

import criticadefilmes.ProjetoIngrid.entities.UsuarioEntity;
import criticadefilmes.ProjetoIngrid.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/users")
    public ResponseEntity save(@RequestBody UsuarioEntity usuarioEntity) {

        UsuarioEntity buscaUsuario = userService.findByEmail(usuarioEntity.getEmail());
        if (buscaUsuario == null){
            return ResponseEntity.ok().body(userService.criar(usuarioEntity));
        }

        return new ResponseEntity("Usuario ja existe",
                    HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/atualizaUsuario")
    public ResponseEntity atualiza(@RequestParam (name = "email") String email,
                                   @RequestHeader(value = "Authorization" ) String token ) throws NoSuchAlgorithmException {
        try {
            UsuarioEntity usuario = userService.buscaUsuario(token);
        }catch (Exception e){
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        UsuarioEntity usuario = userService.buscaUsuario(token);

        if(usuario.getId_perfil() == 4) {
            UsuarioEntity buscaUsuario = userService.findByEmail(email);

            return ResponseEntity.ok().body(userService.atualizaPerfil(buscaUsuario.getNickname()));
        }

        return new ResponseEntity("Voce nao possui perfil necessario para realizar essa acao",
                HttpStatus.BAD_REQUEST);
    }
}
