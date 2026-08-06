package com.example.project.controller;

import com.example.project.dto.PedidoDTO;
import com.example.project.entity.Pedido;
import com.example.project.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // GET /api/pedidos -> Listar todos los pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerTodos() {
        List<Pedido> pedidos = pedidoService.obtenerTodos();
        return ResponseEntity.ok(pedidos);
    }

    // GET /api/pedidos/usuario/{usuarioId} -> Obtener pedidos de un usuario específico
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Pedido>> obtenerPorUsuario(@PathVariable Long usuarioId) {
        List<Pedido> pedidos = pedidoService.obtenerPorUsuario(usuarioId);
        return ResponseEntity.ok(pedidos);
    }

    // POST /api/pedidos/usuario/{usuarioId} -> Crear pedido para un usuario usando DTO de respuesta
    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> crearPedido(@PathVariable Long usuarioId, @RequestBody Pedido pedido) {
        try {
            Pedido nuevoPedido = pedidoService.crearPedido(usuarioId, pedido);
            
            PedidoDTO respuesta = new PedidoDTO(
                nuevoPedido.getId(),
                nuevoPedido.getDescripcion(),
                nuevoPedido.getTotal(),
                nuevoPedido.getFechaPedido(),
                nuevoPedido.getUsuario().getId(),
                nuevoPedido.getUsuario().getNombre()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET /api/pedidos/filtrar?minimo=1000 -> Búsqueda personalizada con @Query
    @GetMapping("/filtrar")
    public ResponseEntity<List<PedidoDTO>> obtenerMayoresA(@RequestParam BigDecimal minimo) {
        List<PedidoDTO> pedidos = pedidoService.obtenerMayoresA(minimo);
        return ResponseEntity.ok(pedidos);
    }
}
