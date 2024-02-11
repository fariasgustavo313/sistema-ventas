package com.example.sistemaventas.controller;

import com.example.sistemaventas.model.Cliente;
import com.example.sistemaventas.model.Producto;
import com.example.sistemaventas.service.I_ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private I_ProductoService interProducto;

    @PostMapping("/productos/crear")
    public String crearProducto(@RequestBody Producto producto) {
        interProducto.crearProducto(producto);
        return "Producto creado con exito";
    }

    @DeleteMapping("/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        interProducto.eliminarProducto(id);
        return "Producto eliminado con exito";
    }

    @PutMapping("/productos/editar/{id}")
    public String editarProducto(@PathVariable Long id,
                                 @RequestBody Producto producto) {
        interProducto.editarProducto(id, producto);
        return "Producto editado con exito";
    }

    @GetMapping("/productos")
    public List<Producto> traerProductos() {
        return interProducto.traerProductos();
    }

    @GetMapping("/productos/{id}")
    public Producto traerProducto(@PathVariable Long id) {
        return interProducto.traerProducto(id);
    }

    // obtener productos con cantidad menor a 5
    @GetMapping("/productos/falta_stock")
    public List<Producto> traerProductosMenorCinco() {
        return interProducto.productosCantMenorCinco();
    }
}
