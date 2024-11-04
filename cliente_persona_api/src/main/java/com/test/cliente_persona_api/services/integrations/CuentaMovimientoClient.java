package com.test.cliente_persona_api.services.integrations;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cuenta", url = "${feign.cuenta_movimiento.uri}")
public interface CuentaMovimientoClient {

    @DeleteMapping(value = "/cuentas/clienteId/{clienteId}")
    Void deleteCuentasByClienteId(@PathVariable String clienteId);
}