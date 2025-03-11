package com.test.cuenta_movimientos_api.services.integrations;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cliente", url = "${feign.cliente_persona.uri}")
public interface ClientePersonaClient {

    @GetMapping(value = "/clientes/clienteId/{clienteId}")
    String getClientNameByClienteId(@PathVariable String clienteId);
}