package com.test.cuenta_movimientos_api.services.cuentas.dto;

import com.test.cuenta_movimientos_api.services.cuentas.models.enums.TipoCuenta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CuentaClienteDTO {

    private boolean estado;

    private double saldoInicial;

    private String numeroCuenta;

    private TipoCuenta tipoCuenta;

    private String nombreCliente;
}
