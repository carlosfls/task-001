package com.test.cuenta_movimientos_api.services.movimientos.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateMovimientoDTO {

    private String noCuenta;
    private double valor;
}
