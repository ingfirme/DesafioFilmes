package criticadefilmes.ProjetoIngrid.entities;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="dados_usuario")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nickname;
    @Column
    private String nome;
    @Column
    private String sobrenome;

    @Column(unique = true)
    private String email;
    @Column
    private String idade;
    @Column
    private String senha;
    @Column
    private Integer qtde_nota;
    @Column
    private Integer qtde_comentario;
    @Column
    private Integer qtde_resposta;
    @Column
    private Integer qtde_total;
    @Column
    private Integer id_perfil;

}
