package com.example.project.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoDTO {
    private Long id;
    private String descripcion;
    private BigDecimal total;
    private LocalDateTime fechaPedido;
    private Long usuarioId;
    private String usuarioNombre;

    public PedidoDTO() {}

    public PedidoDTO(Long id, String descripcion, BigDecimal total, LocalDateTime fechaPedido, Long usuarioId, String usuarioNombre) {
        this.id = id;
        this.descripcion = descripcion;
        this.total = total;
        this.fechaPedido = fechaPedido;
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public LocalDateTime getFechaPedido() { return fechaPedido; }
    public void setFechaPedido(LocalDateTime fechaPedido) { this.fechaPedido = fechaPedido; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public String getUsuarioNombre() { return usuarioNombre; }
    public void setUsuarioNombre(String usuarioNombre) { this.usuarioNombre = usuarioNombre; }
}
