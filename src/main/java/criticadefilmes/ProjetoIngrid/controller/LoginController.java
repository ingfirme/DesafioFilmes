package criticadefilmes.ProjetoIngrid.controller;

import criticadefilmes.ProjetoIngrid.entities.UsuarioEntity;
import criticadefilmes.ProjetoIngrid.http.requests.LoginRequest;
import criticadefilmes.ProjetoIngrid.repository.UserRepository;
import criticadefilmes.ProjetoIngrid.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class LoginController {
    private final UserRepository userRepository;
    private final UserService userService;
    int quantidadeFalha =0;

    @PostMapping(value = "/login")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(@RequestBody LoginRequest usuario)
    {
        UsuarioEntity user = userService.findByEmail(usuario.getUsuario());
        if(user != null) {
            try {
                if (user.getEmail().equals(usuario.getUsuario()) && user.getSenha().equals(usuario.getSenha())) {
                    String status = "Sucesso";
                    statusLogin(usuario, status);
                    String jwtToken = Jwts.builder()
                            .setSubject(usuario.getUsuario())
                            .setIssuer("localhost:8080")
                            .setIssuedAt(new Date())
                            .setExpiration(Date.from(LocalDateTime.now().plusMinutes(15L).atZone(ZoneId.systemDefault()).toInstant()))
                            .signWith(CHAVE, SignatureAlgorithm.HS256)
                            .compact();

                    return Response.status(Response.Status.OK).entity(jwtToken).build();
                } else if (user.getSenha() != (usuario.getSenha()) && user.getEmail().equals(usuario.getUsuario())) {
                    String status = "Falha";
                    LoginRequest statusLogin = statusLogin(usuario, status);
                    if(statusLogin.getStatus() == "Falha") {
                        return Response.status(Response.Status.UNAUTHORIZED).entity("Senha inválida").build();
                    }else if(statusLogin.getStatus() == "Bloqueado"){
                        return Response.status(Response.Status.UNAUTHORIZED).entity("limite de tentativas excedido").build();
                    }
                }
            } catch (Exception ex) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário não cadastrado").build();
    }
    private final SecretKey CHAVE = Keys.hmacShaKeyFor("7f-j&amp;CKk=coNzZc0y7_4obMP?#TfcYq%fcD0mDpenW2nc!lfGoZ|d?f&amp;RNbDHUX6".getBytes(StandardCharsets.UTF_8));

    @Cacheable(value = "usuario", key="#uuid")
    public LoginRequest statusLogin(LoginRequest usuario, String status){
        if(status == "Falha"){
            quantidadeFalha= ++quantidadeFalha;
            usuario.setStatus(status);
        }else {
            quantidadeFalha= 0;
            status = "Sucesso";
            usuario.setStatus(status);

        }
        if (quantidadeFalha >= 4){
            status = "Bloqueado";
            usuario.setStatus(status);
            return usuario;
        }else {
            return usuario;
        }
    }

}

