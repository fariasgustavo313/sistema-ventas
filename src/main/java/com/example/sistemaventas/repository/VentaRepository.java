package com.example.sistemaventas.repository;

import com.example.sistemaventas.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    @Query("SELECT v FROM Venta v WHERE v.fecha_venta = :fecha_venta")
    List<Venta> traerVentasPorFecha(@Param("fecha_venta")LocalDate fecha_venta);

    @Query("SELECT v FROM Venta v ORDER BY v.total DESC")
    Venta traerVentaMasAlta();
}