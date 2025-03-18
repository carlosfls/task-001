package com.test.cuenta_movimientos_api.services.cuentas.models.entity;

import com.test.cuenta_movimientos_api.services.cuentas.models.enums.TipoCuenta;
import com.test.cuenta_movimientos_api.services.movimientos.models.entity.Movimiento;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean estado;

    private double saldoInicial;

    private double saldoDisponible;

    @Column(unique = true)
    private String numeroCuenta;

    private TipoCuenta tipoCuenta;

    private String clienteId;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "cuenta")
    private List<Movimiento> movimientos;

    public Cuenta() {
        this.movimientos = new ArrayList<>();
    }

    public Cuenta withEstado(boolean estado){
        this.estado = estado;
        return this;
    }

    public Cuenta withSaldoDisponible(double saldoDisponible){
        this.saldoDisponible = saldoDisponible;
        return this;
    }

    public void addMovimiento(Movimiento movimiento){
        if (movimientos == null){
            this.movimientos = new ArrayList<>();
        }
        this.movimientos.add(movimiento);
    }
}
