package com.test.cuenta_movimientos_api.services.movimientos.models.entity;

import com.test.cuenta_movimientos_api.services.cuentas.models.entity.Cuenta;
import com.test.cuenta_movimientos_api.services.movimientos.models.enums.TipoMovimiento;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valor;

    private double saldo;

    private LocalDateTime fecha;

    private TipoMovimiento tipoMovimiento;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;
}
