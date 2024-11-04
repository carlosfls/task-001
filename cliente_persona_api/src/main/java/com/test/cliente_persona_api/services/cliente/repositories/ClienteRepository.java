package com.test.cliente_persona_api.services.cliente.repositories;

import com.test.cliente_persona_api.services.cliente.models.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByClienteId(String clienteId);

    Optional<Cliente> findByClienteId(String clienteId);
}
