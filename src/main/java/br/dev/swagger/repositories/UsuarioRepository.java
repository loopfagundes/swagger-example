package br.dev.swagger.repositories;

import br.dev.swagger.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository <UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByCpf(String cpf);
}