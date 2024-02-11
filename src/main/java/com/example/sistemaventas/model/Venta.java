package com.example.sistemaventas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_venta;
    private LocalDate fecha_venta;
    private Double total;
    @OneToMany(mappedBy = "venta")
    private List<Producto> listaProductos;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
}
