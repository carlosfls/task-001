package com.test.cuenta_movimientos_api.services.cuentas.handlers;

import com.test.cuenta_movimientos_api.services.cuentas.repositories.CuentaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteCuentaHandlerImpl {

    private final CuentaRepository cuentaRepository;

    @Transactional
    @KafkaListener(topics = "delete-cuenta-topic", groupId = "delete-cuenta-topic-events")
    public void handle(String clientId){
        log.info("Recibido cliente numero: {}", clientId);
        deleteByClienteId(clientId);
        log.info("Eliminadas cuentas para cliente: {}", clientId);
    }

    private void deleteByClienteId(String clienteId) {
        cuentaRepository.deleteAllByClienteId(clienteId);
    }
}
