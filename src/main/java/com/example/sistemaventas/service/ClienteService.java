package com.example.sistemaventas.service;

import com.example.sistemaventas.model.Cliente;
import com.example.sistemaventas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements I_ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Override
    public void crearCliente(Cliente cliente) {
        try {
            List<Cliente> listaClientes = this.traerClientes();
            for (Cliente cli : listaClientes) {
                if (cli.getDni() == cliente.getDni()) {
                    throw new RuntimeException("El cliente con DNI " + cliente.getDni() + " ya existe");
                }
            }
            clienteRepository.save(cliente);
        } catch (RuntimeException ex) {
            System.err.println("Error al crear cliente: " + ex.getMessage());
        }
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);;
    }

    @Override
    public void editarCliente(Long id, Cliente cliente) {
        Cliente cli = this.traerCliente(id);
        cli.setNombre(cliente.getNombre());
        cli.setApellido(cliente.getApellido());
        cli.setDni(cliente.getDni());
        this.crearCliente(cli);
    }

    @Override
    public List<Cliente> traerClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente traerCliente(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }
}
