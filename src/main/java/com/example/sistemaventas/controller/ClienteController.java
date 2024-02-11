package com.example.sistemaventas.controller;

import com.example.sistemaventas.model.Cliente;
import com.example.sistemaventas.service.I_ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    private I_ClienteService interCliente;

    @PostMapping("/clientes/crear")
    public String crearCliente(@RequestBody Cliente cliente) {
        interCliente.crearCliente(cliente);
        return "Cliente creado con exito";
    }

    @DeleteMapping("/clientes/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        interCliente.eliminarCliente(id);
        return "Cliente eliminado con exito";
    }

    @PutMapping("/clientes/editar/{id}")
    public String editarCliente(@PathVariable Long id,
                                @RequestBody Cliente cliente) {
        interCliente.editarCliente(id, cliente);
        return "Cliente actualizado con exito";
    }

    @GetMapping("/clientes")
    public List<Cliente> traerClientes() {
        return interCliente.traerClientes();
    }

    @GetMapping("/clientes/{id}")
    public Cliente traerCliente(@PathVariable Long id) {
        return interCliente.traerCliente(id);
    }
}
