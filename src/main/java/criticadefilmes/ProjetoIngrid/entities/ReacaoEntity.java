package criticadefilmes.ProjetoIngrid.entities;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="reacoes")
public class ReacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_reacao;

    @Column
    private String id_comentario;

    @Column
    private Integer nickname;

    @Column
    private Boolean reacao;



}
