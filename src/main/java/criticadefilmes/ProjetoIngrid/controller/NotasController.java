package criticadefilmes.ProjetoIngrid.controller;

import criticadefilmes.ProjetoIngrid.entities.NotaEntity;
import criticadefilmes.ProjetoIngrid.entities.UsuarioEntity;
import criticadefilmes.ProjetoIngrid.service.NotaService;
import criticadefilmes.ProjetoIngrid.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class NotasController {
    private final NotaService notaService;
    private final UserService userService;

    @PostMapping(value = "/nota")
    public ResponseEntity salvarNota(@RequestBody NotaEntity nota,
                           @RequestHeader(value = "Authorization" ) String token ) throws Exception {
        try {
            UsuarioEntity usuario = userService.buscaUsuario(token);
        }catch (Exception e){
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        UsuarioEntity usuario = userService.buscaUsuario(token);
        List<Optional<NotaEntity>> listaNotas = notaService.buscaNota(usuario.getNickname());
        if (listaNotas.isEmpty()){
            return ResponseEntity.ok().body(notaService.salvar(nota, usuario));
        }

        for (Optional<NotaEntity> listaNota: listaNotas){
            if (listaNota.get().getTitulo().equals(nota.getTitulo())) {
                return new ResponseEntity("Nota ja existe, favor avaliar outro filme",
                        HttpStatus.BAD_REQUEST);
            }
        }
        return ResponseEntity.ok().body(notaService.salvar(nota, usuario));
    }
}

