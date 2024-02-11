package com.example.sistemaventas.service;

import com.example.sistemaventas.dto.VentaDTO;
import com.example.sistemaventas.model.Cliente;
import com.example.sistemaventas.model.Producto;
import com.example.sistemaventas.model.Venta;
import com.example.sistemaventas.repository.ClienteRepository;
import com.example.sistemaventas.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements I_VentaService {

    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Override
    public void crearVenta(Venta venta) {
        try {
            Cliente clienteExistente = clienteRepository.findById(venta.getCliente().getId_cliente()).orElse(null);
            venta.setCliente(clienteExistente);
            List<Producto> listaProductos = venta.getListaProductos();
            for (Producto producto : listaProductos) {
                if (producto.getCantidad_disponible() > 1) {
                    ventaRepository.save(venta);
                } else {
                    System.out.println("El producto no tiene la cantidad disponible");
                }
            }
        } catch (RuntimeException ex) {
            System.err.println("Error al procesar la venta.");
        }

    }

    @Override
    public void eliminarVenta(Long id) {
        ventaRepository.deleteById(id);
    }

    @Override
    public void editarVenta(Long id, Venta venta) {
        Venta vta = this.traerVenta(id);
        vta.setFecha_venta(venta.getFecha_venta());
        vta.setCliente(venta.getCliente());
        vta.setListaProductos(venta.getListaProductos());
        vta.setTotal(venta.getTotal());
        this.crearVenta(venta);;
    }

    @Override
    public List<Venta> traerVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta traerVenta(Long id) {
        return ventaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Producto> traerProductosSegunVenta(Long id) {
        Venta vta = this.traerVenta(id);
        List<Producto> listaProductosSegunVenta = vta.getListaProductos();
        return listaProductosSegunVenta;
    }

    @Override
    public String obtenerTotalYCantVentas(LocalDate fecha_venta) {
        List<Venta> listaVentasPorFecha = ventaRepository.traerVentasPorFecha(fecha_venta);
        int cantVentas = listaVentasPorFecha.size();
        double total = 0;
        for (Venta venta : listaVentasPorFecha) {
            total += venta.getTotal();
        }
        return "La cantidad de ventas es: " + cantVentas + ", el monto total es: $ " + total;
    }

    @Override
    public VentaDTO traerVentaMasAlta() {
        Venta ventaMayor = ventaRepository.traerVentaMasAlta();

        if (ventaMayor != null && ventaMayor.getCliente() != null) {
            Cliente cliente = clienteRepository.findById(ventaMayor.getCliente().getId_cliente()).orElse(null);

            if (cliente != null) {
                VentaDTO ventaDTO = new VentaDTO();
                ventaDTO.setId_venta(ventaMayor.getId_venta());
                ventaDTO.setTotal(ventaMayor.getTotal());
                Long cantProductos = ventaMayor.getListaProductos().stream().count();
                ventaDTO.setCantidad_productos(cantProductos);
                ventaDTO.setNombre_cliente(cliente.getNombre());
                ventaDTO.setApellido_cliente(cliente.getApellido());

                return ventaDTO;
            }
        }
        return null;
    }
}