package com.test.cuenta_movimientos_api.services.movimientos.dto;

import com.test.cuenta_movimientos_api.services.movimientos.models.enums.TipoMovimiento;

import java.time.LocalDateTime;

public record MovimientoDTO(
        Long id,
        double valor,
        double saldo,
        LocalDateTime fecha,
        TipoMovimiento tipoMovimiento,
        String numeroCuenta
) {}
