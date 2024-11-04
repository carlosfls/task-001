package com.test.cuenta_movimientos_api.services.movimientos.dto;

import com.test.cuenta_movimientos_api.services.cuentas.models.enums.TipoCuenta;
import com.test.cuenta_movimientos_api.services.movimientos.dto.MovimientoDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CuentaMovimientoDTO {

    private boolean estado;

    private double saldoInicial;

    private String numeroCuenta;

    private TipoCuenta tipoCuenta;

    private List<MovimientoDTO> movimientos;
}
