package com.example.sistemaventas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {

    private Long id_venta;
    private Long cantidad_productos;
    private String nombre_cliente;
    private String apellido_cliente;
    private double total;
}
