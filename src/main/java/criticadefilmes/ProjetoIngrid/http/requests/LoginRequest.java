package criticadefilmes.ProjetoIngrid.http.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class LoginRequest {
    private int idUsuario;
    private String usuario;
    private String senha;
    private String status;

    public LoginRequest(int idUsuario, String usuario, String status) {
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }
    
    public String getStatus() {
        return status;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
