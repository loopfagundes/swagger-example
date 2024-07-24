package br.dev.swagger.controllers;

import br.dev.swagger.entities.Usuario;
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

    @GetMapping
    public List<Usuario> getAll() {
        return usuarioService.getUserAll();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> getId(@PathVariable Long id) {
        return usuarioService.getIdUser(id);
    }

    @PostMapping
    public Usuario create(@Valid @RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
        return usuarioService.getIdUser(id).map(u -> {
            u.setNome(usuario.getNome());
            u.setDataNascimento(usuario.getDataNascimento());
            u.setCpf(usuario.getCpf());
            Usuario updated = usuarioService.save(u);
            return ResponseEntity.ok(updated);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteId(@PathVariable Long id) {
        return usuarioService.getIdUser(id).map(user -> {
            usuarioService.deleteUserId(id);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllUser() {
        return usuarioService.deleteAll();
    }
}