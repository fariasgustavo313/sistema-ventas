package com.example.sistemaventas.service;

import com.example.sistemaventas.model.Cliente;

import java.util.List;

public interface I_ClienteService {

    public void crearCliente(Cliente cliente);
    public void eliminarCliente(Long id);
    public void editarCliente(Long id, Cliente cliente);
    public List<Cliente> traerClientes();
    public Cliente traerCliente(Long id);
}
