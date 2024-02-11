package com.example.sistemaventas.service;

import com.example.sistemaventas.dto.VentaDTO;
import com.example.sistemaventas.model.Producto;
import com.example.sistemaventas.model.Venta;

import java.time.LocalDate;
import java.util.List;

public interface I_VentaService {

    public void crearVenta(Venta venta);
    public void eliminarVenta(Long id);
    public void editarVenta(Long id, Venta venta);
    public List<Venta> traerVentas();
    public Venta traerVenta(Long id);
    public List<Producto> traerProductosSegunVenta(Long id);
    public String obtenerTotalYCantVentas(LocalDate fecha_venta);
    public VentaDTO traerVentaMasAlta();
}
