package com.example.project.service;

import com.example.project.entity.Usuario;
import com.example.project.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // Inyeccion de dependencias mendiante constructor (buena practica)
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    public Usuario guardar(Usuario usuario) {
        // Regla de negocio: Validar duplicidad de email antes de guardar
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("El email " + usuario.getEmail() + " ya está registrado.");
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("No se encontró el usuario con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}
