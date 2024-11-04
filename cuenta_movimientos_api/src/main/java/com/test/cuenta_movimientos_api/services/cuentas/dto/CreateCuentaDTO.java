package com.test.cuenta_movimientos_api.services.cuentas.dto;

import com.test.cuenta_movimientos_api.services.cuentas.models.enums.TipoCuenta;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCuentaDTO {

    @Min(value = 0, message = "La cuenta debe tener un valor minimo de 0")
    private double saldoInicial;

    @NotEmpty(message = "El numero de cuenta no puede estar vacio")
    private String numeroCuenta;

    @NotNull(message = "Se debe especificar un tipo de cuenta")
    private TipoCuenta tipoCuenta;

    @NotEmpty(message = "El id del cliente no puede estar vacio")
    private String clienteId;
}
