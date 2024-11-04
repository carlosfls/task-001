package com.test.cuenta_movimientos_api.services.cuentas;

import com.test.cuenta_movimientos_api.exceptions.AlreadyExistException;
import com.test.cuenta_movimientos_api.exceptions.NotFoundException;
import com.test.cuenta_movimientos_api.exceptions.UnprocessableEntityException;
import com.test.cuenta_movimientos_api.services.cuentas.dto.CreateCuentaDTO;
import com.test.cuenta_movimientos_api.services.cuentas.dto.CuentaClienteDTO;
import com.test.cuenta_movimientos_api.services.cuentas.dto.CuentaDTO;
import com.test.cuenta_movimientos_api.services.cuentas.dto.UpdateCuentaDTO;
import com.test.cuenta_movimientos_api.services.cuentas.models.entity.Cuenta;

import java.util.List;

public interface CuentaService {

    List<CuentaDTO> getAll(int pageNo, int pageSize);

    CuentaDTO getById(Long id) throws NotFoundException;

    Cuenta getByNumber(String numeroCuenta) throws NotFoundException;

    CuentaClienteDTO create(CreateCuentaDTO dto) throws AlreadyExistException, UnprocessableEntityException, NotFoundException;

    CuentaDTO update(Long id, UpdateCuentaDTO dto) throws NotFoundException;

    void delete(Long id) throws NotFoundException;

    void deleteByClienteId(String clienteId);

    Cuenta save(Cuenta cuenta);

    boolean existCuentaForCliente(String clientId);

    CuentaClienteDTO buildCuentaCliente(Cuenta cuenta, String nombreCliente);
}
