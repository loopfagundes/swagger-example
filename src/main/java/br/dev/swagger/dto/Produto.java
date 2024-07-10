package br.dev.swagger.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Data
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O item nao pode ser em branco.")
    @Column(name = "item")
    private String item;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}