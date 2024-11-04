package com.test.cuenta_movimientos_api.services.movimientos.repositories;

import com.test.cuenta_movimientos_api.services.movimientos.models.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    @Query("""
    select m from Movimiento m
    where m.fecha between :fechaInicio and coalesce(:fechaFin, CURRENT_TIMESTAMP)  and m.cuenta.clienteId = :clienteId
    order by m.fecha DESC""")
    List<Movimiento> findAllByFechaBetweenAndCuentaClienteIdOrderByFechaDesc(LocalDateTime fechaInicio, LocalDateTime fechaFin, String clienteId);

    List<Movimiento> findAllByCuentaClienteId(String clienteId);

}
