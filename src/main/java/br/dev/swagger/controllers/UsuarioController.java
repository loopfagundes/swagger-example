package br.dev.swagger.controllers;

import br.dev.swagger.entities.UsuarioEntity;
import br.dev.swagger.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    private ProdutoController produtoController;

    @GetMapping
    public List<UsuarioEntity> getAll() {
        return usuarioService.allUsers();
    }

    @GetMapping("/{id}")
    public Optional<UsuarioEntity> getId(@PathVariable Long id) {
        return usuarioService.getIdUser(id);
    }

    @PostMapping
    public UsuarioEntity create(@Valid @RequestBody UsuarioEntity usuarioEntity) {
        return usuarioService.save(usuarioEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> update(@PathVariable Long id, @Valid @RequestBody UsuarioEntity usuarioEntity) {
        return usuarioService.getIdUser(id).map(u -> {
            u.setNome(usuarioEntity.getNome());
            u.setDataNascimento(usuarioEntity.getDataNascimento());
            u.setCpf(usuarioEntity.getCpf());
            u.setProdutoEntities(usuarioEntity.getProdutoEntities()); //Error 500
            UsuarioEntity updated = usuarioService.save(u);
            return ResponseEntity.ok(updated);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteId(@PathVariable Long id) {
        return usuarioService.getIdUser(id).map(user -> {
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllUser() {
        return usuarioService.deleteAll();
    }
}