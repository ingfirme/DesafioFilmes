package criticadefilmes.ProjetoIngrid.entities;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="citar_comentario")
public class CitacaoComentarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_citar_comentario;

    @Column
    private Integer id_comentario;

    @Column
    private String comentario;

}
