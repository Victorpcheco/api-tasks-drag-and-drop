package Api.tasks.drag_and_drop.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;


@Entity
@Table(name = "Tb_tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    @NotBlank(message = "o campo título é obrigatório")
    private String titulo;

    @Column(name = "status")
    @NotBlank(message = "o campo de status é obrigatório")
    private String status;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_criacao")
    private LocalDateTime data_criacao;

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }

    @Column(name = "usuario_id")
    private Long usuario_id;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getStatus() {
        return status;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getData_cracao() {
        return data_criacao;
    }

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setData_criacao(LocalDateTime data_criacao) {
        this.data_criacao = data_criacao;
    }
}
