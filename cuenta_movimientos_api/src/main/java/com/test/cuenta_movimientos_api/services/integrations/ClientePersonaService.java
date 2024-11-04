package com.test.cuenta_movimientos_api.services.integrations;

import com.test.cuenta_movimientos_api.exceptions.NotFoundException;

public interface ClientePersonaService {

    String getClientName(String clienteId) throws NotFoundException;
}
