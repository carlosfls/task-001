package com.test.cuenta_movimientos_api.services.integrations.impl;

import com.test.cuenta_movimientos_api.services.integrations.ClientePersonaClient;
import com.test.cuenta_movimientos_api.services.integrations.ClientePersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientePersonaServiceImpl implements ClientePersonaService {

    private final ClientePersonaClient clientePersonaClient;

    @Override
    public String getClientName(String clienteId) {
        return clientePersonaClient.getClientNameByClienteId(clienteId);
    }
}
