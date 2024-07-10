package br.dev.swagger.controllers;

import br.dev.swagger.dto.Produto;
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
    public List<Produto> allProducts() {
        return produtoService.allProducts();
    }

    @PostMapping
    public Produto create(@Valid @RequestBody Produto produto) {
        return produtoService.save(produto);
    }

    @PutMapping("{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @Valid @RequestBody Produto produto) {
        return produtoService.allProducts().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(p -> {
                    p.setItem(produto.getItem());
                    Produto updated = produtoService.save(p);
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