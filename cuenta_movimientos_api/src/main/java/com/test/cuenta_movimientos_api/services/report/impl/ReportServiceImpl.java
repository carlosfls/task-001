package com.test.cuenta_movimientos_api.services.report.impl;

import com.test.cuenta_movimientos_api.exceptions.NotFoundException;
import com.test.cuenta_movimientos_api.services.cuentas.CuentaService;
import com.test.cuenta_movimientos_api.services.cuentas.dto.CuentaClienteDTO;
import com.test.cuenta_movimientos_api.services.integrations.ClientePersonaService;
import com.test.cuenta_movimientos_api.services.movimientos.MovimientoService;
import com.test.cuenta_movimientos_api.services.movimientos.models.entity.Movimiento;
import com.test.cuenta_movimientos_api.services.report.ReportService;
import com.test.cuenta_movimientos_api.services.report.dto.EstadoCuentaDTO;
import com.test.cuenta_movimientos_api.services.report.mappers.ReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final CuentaService cuentaService;
    private final MovimientoService movimientoService;
    private final ClientePersonaService clientePersonaService;
    private final ReportMapper reportMapper;

    @Override
    public List<EstadoCuentaDTO> getEstadoCuenta(String clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        if (!cuentaService.existCuentaForCliente(clienteId)) {
            throw new NotFoundException("No existe la cuenta para el cliente "+ clienteId);
        }

        var nombreCliente = clientePersonaService.getClientName(clienteId);
        var fin = Optional.ofNullable(fechaFin)
                .map(LocalDate::atStartOfDay)
                .orElse(null);
        List<Movimiento> movimientos = movimientoService.filter(clienteId, fechaInicio.atStartOfDay(), fin);
        return movimientos.stream()
                .map(movimiento -> buildEstadoCuenta(movimiento, nombreCliente))
                .collect(Collectors.toList());
    }

    private EstadoCuentaDTO buildEstadoCuenta(Movimiento movimiento, String nombreCliente){
        CuentaClienteDTO cuentaClienteDTO = cuentaService.buildCuentaCliente(movimiento.getCuenta(), nombreCliente);
        EstadoCuentaDTO estadoCuenta = reportMapper.toDto(cuentaClienteDTO);
        estadoCuenta.setFecha(movimiento.getFecha());
        estadoCuenta.setMovimiento(movimiento.getValor());
        estadoCuenta.setSaldoDisponible(movimiento.getSaldo());

        return estadoCuenta;
    }
}
