package br.dev.swagger.repositories;

import br.dev.swagger.dto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}