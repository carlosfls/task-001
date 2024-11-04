package com.test.cuenta_movimientos_api.services.cuentas.repositories;

import com.test.cuenta_movimientos_api.services.cuentas.models.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    boolean existsByNumeroCuenta(String numeroCuenta);

    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

    boolean existsByClienteId(String clienteId);

    List<Cuenta> findAllByClienteId(String clienteId);

    void deleteAllByClienteId(String clienteId);
}
