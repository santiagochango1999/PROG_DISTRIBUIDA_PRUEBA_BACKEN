package org.example.db;

import jakarta.persistence.*;


import java.math.BigDecimal;

@Entity
@Table(name = "orden_compra")
public class Orden_Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name="nombre")
    private BigDecimal precio;

    @Column(name="cliente_id")
    private Integer clienteId;

    @Column(name="producto_id")
    private Integer productoId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }
}
