package com.test.cliente_persona_api.services.integrations.impl;

import com.test.cliente_persona_api.services.integrations.CuentaMovimientoClient;
import com.test.cliente_persona_api.services.integrations.CuentaMovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CuentaMovimientoImpl implements CuentaMovimientoService {

    private final CuentaMovimientoClient cuentaMovimientoClient;

    @Override
    public void deleteCuentasForCliente(String clienteId) {
        cuentaMovimientoClient.deleteCuentasByClienteId(clienteId);
    }
}
