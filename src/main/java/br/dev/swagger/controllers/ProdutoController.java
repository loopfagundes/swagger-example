package br.dev.swagger.controllers;

import br.dev.swagger.entities.ProdutoEntity;
import br.dev.swagger.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<ProdutoEntity> allProducts() {
        return produtoService.allProducts();
    }

    @PostMapping
    public ProdutoEntity create(@Valid @RequestBody ProdutoEntity produtoEntity) {
        return produtoService.save(produtoEntity);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProdutoEntity> update(@PathVariable Long id, @Valid @RequestBody ProdutoEntity produtoEntity) {
        return produtoService.getIdProduct(id).stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(p -> {
                    p.setQuantidade(produtoEntity.getQuantidade());
                    p.setItem(produtoEntity.getItem());
                    p.setMarca(produtoEntity.getMarca());
                    ProdutoEntity updated = produtoService.save(p);
                    return ResponseEntity.ok(updated);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        return produtoService.allProducts().stream()
                .filter(p -> p.getId().equals(id)).findFirst()
                .map(p -> {
                    produtoService.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}