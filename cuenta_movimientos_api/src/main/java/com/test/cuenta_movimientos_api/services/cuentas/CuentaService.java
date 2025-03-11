package com.test.cuenta_movimientos_api.services.cuentas;

import com.test.cuenta_movimientos_api.services.cuentas.dto.CreateCuentaDTO;
import com.test.cuenta_movimientos_api.services.cuentas.dto.CuentaClienteDTO;
import com.test.cuenta_movimientos_api.services.cuentas.dto.CuentaDTO;
import com.test.cuenta_movimientos_api.services.cuentas.dto.UpdateCuentaDTO;
import com.test.cuenta_movimientos_api.services.cuentas.models.entity.Cuenta;

import java.util.List;

public interface CuentaService {

    List<CuentaDTO> getAll(int pageNo, int pageSize);

    CuentaDTO getById(Long id);

    Cuenta getByNumber(String numeroCuenta);

    CuentaClienteDTO create(CreateCuentaDTO dto);

    CuentaDTO update(Long id, UpdateCuentaDTO dto);

    void delete(Long id);

    void deleteByClienteId(String clienteId);

    Cuenta save(Cuenta cuenta);

    boolean existCuentaForCliente(String clientId);

    CuentaClienteDTO buildCuentaCliente(Cuenta cuenta, String nombreCliente);
}
