package com.example.project.controller;

import com.example.project.entity.Usuario;
import com.example.project.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // GET /api/usuarios -> Listar todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        List<Usuario> usuarios = usuarioService.obtenerTodos();
        return ResponseEntity.ok(usuarios);
    }

    // GET /api/usuarios/{id} -> Buscar usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/usuarios -> Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.guardar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DELETE /api/usuarios/{id} -> Eliminar usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
