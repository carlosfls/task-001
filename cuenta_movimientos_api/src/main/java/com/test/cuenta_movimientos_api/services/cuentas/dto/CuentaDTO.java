package com.test.cuenta_movimientos_api.services.cuentas.dto;

import com.test.cuenta_movimientos_api.services.cuentas.models.enums.TipoCuenta;

public record CuentaDTO (
    Long id,
    boolean estado,
    double saldoInicial,
    double saldoDisponible,
    String numeroCuenta,
    TipoCuenta tipoCuenta
){}
