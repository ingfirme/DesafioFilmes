package criticadefilmes.ProjetoIngrid.entities;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="comentarios")
public class ComentarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_comentario;

    @Column
    private String titulo;

    @Column
    private Integer nickname;

    @Column
    private String comentario;

    @Column
    private Boolean repetido;

}
