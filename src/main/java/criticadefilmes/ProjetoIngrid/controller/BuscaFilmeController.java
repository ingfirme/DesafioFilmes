package criticadefilmes.ProjetoIngrid.controller;

import criticadefilmes.ProjetoIngrid.entities.UsuarioEntity;
import criticadefilmes.ProjetoIngrid.http.requests.OmdbapiRequest;
import criticadefilmes.ProjetoIngrid.service.Omdbapi.FilmeService;
import criticadefilmes.ProjetoIngrid.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@AllArgsConstructor
public class BuscaFilmeController {

    private final FilmeService filmeService;
    private final UserService userService;

    @GetMapping(value = "/buscarFilme")
    public ResponseEntity<?> getTitulo(@RequestParam(value = "titulo", defaultValue = "") String titulo,
                                       @RequestHeader(value = "Authorization" ) String token ) {
        try {
            UsuarioEntity usuario = userService.buscaUsuario(token);
        }catch (Exception e){
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        OmdbapiRequest buscaTitulo = filmeService.buscarFilme(titulo);
        return buscaTitulo != null ? ResponseEntity.ok().body(buscaTitulo): ResponseEntity.notFound().build();
    }
}
