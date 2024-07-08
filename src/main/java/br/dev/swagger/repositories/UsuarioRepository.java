package br.dev.swagger.repositories;

import br.dev.swagger.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    Optional<Usuario> findByCpf(String cpf);
}