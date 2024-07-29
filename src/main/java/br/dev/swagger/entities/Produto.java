package br.dev.swagger.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Setter
@Getter
@Entity
@Data
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Quantidade nao pode ser em branco.")
    @Column(name = "quantidade")
//    @Pattern(regexp = "^[0-9]{2}$") // para String
//    private String quantidade;
    @Range(min=0, max = 10) // para Integer
    private Integer quantidade;

    @NotBlank(message = "O item nao pode ser em branco.")
    @Column(name = "item")
    private String item;

    @NotBlank(message = "A marca nao pode ser em branco.")
    @Column(name = "marca")
    private String marca;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}