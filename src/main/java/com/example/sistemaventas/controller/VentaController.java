package com.example.sistemaventas.controller;

import com.example.sistemaventas.dto.VentaDTO;
import com.example.sistemaventas.model.Producto;
import com.example.sistemaventas.model.Venta;
import com.example.sistemaventas.service.I_VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class VentaController {

    @Autowired
    private I_VentaService interVenta;

    @PostMapping("/ventas/crear")
    public String crearVenta(@RequestBody Venta venta) {
        interVenta.crearVenta(venta);
        return "Venta creada exitodamente";
    }

    @DeleteMapping("/ventas/eliminar/{id}")
    public String eliminarVenta(@PathVariable Long id) {
        interVenta.eliminarVenta(id);
        return "Venta eliminada exitosamente";
    }

    @PutMapping("/ventas/editar/{id}")
    public String editarVenta(@PathVariable Long id,
                              @RequestBody Venta venta) {
        interVenta.editarVenta(id, venta);
        return "Venta editada exitosamente";
    }

    @GetMapping("/ventas")
    public List<Venta> traerVentas() {
        return interVenta.traerVentas();
    }

    @GetMapping("/ventas/traer/{id}")
    public Venta traerVenta(@PathVariable Long id) {
        return interVenta.traerVenta(id);
    }

    /*@GetMapping("/ventas/productos/{id}")
    public List<Producto> traerProductosSegnVenta(@PathVariable Long id) {
        return interVenta.traerProductosSegunVenta(id);
    }*/

    @GetMapping("/ventas/{fecha_venta}")
    public String obtenerTotalYCantVentas(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha_venta) {
        return interVenta.obtenerTotalYCantVentas(fecha_venta);
    }

    @GetMapping("/ventas/mayor_venta")
    public VentaDTO traerVentaMasAlta() {
        return interVenta.traerVentaMasAlta();
    }
}
