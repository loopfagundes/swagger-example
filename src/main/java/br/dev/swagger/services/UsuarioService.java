package br.dev.swagger.services;

import br.dev.swagger.models.Usuario;
import br.dev.swagger.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getUserAll() {
        try {
            return usuarioRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Usuario> getIdUser(Long id) {
        try {
            return usuarioRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public Usuario save(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void deleteUserId(Long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            usuarioRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}