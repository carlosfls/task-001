package com.test.cliente_persona_api.services.integrations.impl;

import com.test.cliente_persona_api.exceptions.UnprocessableEntityException;
import com.test.cliente_persona_api.services.integrations.CuentaMovimientoClient;
import com.test.cliente_persona_api.services.integrations.CuentaMovimientoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CuentaMovimientoImpl implements CuentaMovimientoService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final CuentaMovimientoClient cuentaMovimientoClient;

    @Transactional(rollbackFor = UnprocessableEntityException.class)
    @Override
    public void deleteCuentasForCliente(String clienteId) {
        kafkaTemplate.send("delete-cuenta-topic", clienteId)
                .whenComplete((r, e) -> {
                    if (e != null) {
                       log.error(e.getMessage());
                    }else {
                        log.info("Cuentas para cliente {} fueron eliminadas exitosamente", clienteId);
                        throw new UnprocessableEntityException("Error al eliminar cuentas para cliente " + clienteId);
                    }
                });
    }
}
