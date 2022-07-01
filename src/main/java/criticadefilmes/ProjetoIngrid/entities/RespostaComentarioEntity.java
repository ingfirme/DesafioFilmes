package criticadefilmes.ProjetoIngrid.entities;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="resposta_comentarios")
public class RespostaComentarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_resposta_comentario;

    @Column
    private Integer nickname;

    @Column
    private Integer id_comentario;

    @Column
    private String comentario;

}
