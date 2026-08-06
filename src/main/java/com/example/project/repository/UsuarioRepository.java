package com.example.project.repository;

import com.example.project.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Consulta derivada: Spring genera automáticamente la SQL basada en el nombre del método
    Optional<Usuario> findByEmail(String email);

    // Consulta derivada para verificar existencia por email
    boolean existsByEmail(String email);
}
