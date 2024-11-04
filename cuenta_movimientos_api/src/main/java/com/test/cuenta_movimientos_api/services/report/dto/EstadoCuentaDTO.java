package com.test.cuenta_movimientos_api.services.report.dto;

import com.test.cuenta_movimientos_api.services.cuentas.dto.CuentaClienteDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoCuentaDTO extends CuentaClienteDTO {

    private LocalDateTime fecha;

    private double movimiento;

    private double saldoDisponible;
}
