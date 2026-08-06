package com.example.project.service;

import com.example.project.dto.PedidoDTO;
import com.example.project.entity.Pedido;
import com.example.project.entity.Usuario;
import com.example.project.repository.PedidoRepository;
import com.example.project.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<Pedido> obtenerTodos() {
        return pedidoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Pedido> obtenerPorUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }

    @Transactional
    public Pedido crearPedido(Long usuarioId, Pedido pedido) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + usuarioId));

        pedido.setUsuario(usuario);
        return pedidoRepository.save(pedido);
    }

    @Transactional(readOnly = true)
    public List<PedidoDTO> obtenerMayoresA(BigDecimal minimo) {
        return pedidoRepository.buscarPedidosMayoresA(minimo).stream()
                .map(p -> new PedidoDTO(
                        p.getId(),
                        p.getDescripcion(),
                        p.getTotal(),
                        p.getFechaPedido(),
                        p.getUsuario().getId(),
                        p.getUsuario().getNombre()
                ))
                .collect(Collectors.toList());
    }
}
