package com.example.sistemaventas.service;

import com.example.sistemaventas.model.Producto;

import java.util.List;

public interface I_ProductoService {

    public void crearProducto(Producto producto);
    public void eliminarProducto(Long id);
    public void editarProducto(Long id, Producto producto);
    public List<Producto> traerProductos();
    public Producto traerProducto(Long id);
    public List<Producto> productosCantMenorCinco();
}
