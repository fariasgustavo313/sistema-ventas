package com.example.sistemaventas.service;

import com.example.sistemaventas.model.Cliente;
import com.example.sistemaventas.model.Producto;
import com.example.sistemaventas.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService implements I_ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    @Override
    public void crearProducto(Producto producto) {
        try {
            List<Producto> listaProductos = this.traerProductos();
            for (Producto prod : listaProductos) {
                if (prod.getNombre() == producto.getNombre() && prod.getMarca() == producto.getMarca()) {
                    throw new RuntimeException("El Producto " + producto.getNombre() + " ya existe");
                }
            }
            productoRepository.save(producto);
        } catch (RuntimeException ex) {
            System.err.println("Error al crear el producto: " + ex.getMessage());
        }
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public void editarProducto(Long id, Producto producto) {
        Producto prod = this.traerProducto(id);
        prod.setNombre(producto.getNombre());
        prod.setMarca(producto.getMarca());
        prod.setCosto(producto.getCosto());
        prod.setCantidad_disponible(producto.getCantidad_disponible());
        this.crearProducto(prod);
    }

    @Override
    public List<Producto> traerProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto traerProducto(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Producto> productosCantMenorCinco() {
        List<Producto> listaProductos = this.traerProductos();
        List<Producto> listaProductosMenor = new ArrayList<Producto>();
        for (Producto producto : listaProductos) {
            if (producto.getCantidad_disponible() < 5) {
                listaProductosMenor.add(producto);
            }
        }
        return listaProductosMenor;
    }
}
