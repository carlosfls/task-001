package com.test.cuenta_movimientos_api.services.movimientos.impl;

import com.test.cuenta_movimientos_api.exceptions.NotFoundException;
import com.test.cuenta_movimientos_api.services.cuentas.CuentaService;
import com.test.cuenta_movimientos_api.services.cuentas.models.entity.Cuenta;
import com.test.cuenta_movimientos_api.services.movimientos.MovimientoService;
import com.test.cuenta_movimientos_api.services.movimientos.dto.CreateMovimientoDTO;
import com.test.cuenta_movimientos_api.services.movimientos.dto.MovimientoDTO;
import com.test.cuenta_movimientos_api.services.movimientos.mappers.MovimientoMapper;
import com.test.cuenta_movimientos_api.services.movimientos.models.entity.Movimiento;
import com.test.cuenta_movimientos_api.services.movimientos.models.enums.TipoMovimiento;
import com.test.cuenta_movimientos_api.services.movimientos.repositories.MovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final MovimientoMapper movimientoMapper;
    private final CuentaService cuentaService;

    @Override
    public List<MovimientoDTO> getAll(int pageNo, int pageSize){
        return buildListFromPage(movimientoRepository.findAll(PageRequest.of(pageNo, pageSize)));
    }

    @Override
    public MovimientoDTO getById(Long id) throws NotFoundException {
        return movimientoRepository.findById(id)
                .map(movimientoMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Movimiento con id " + id + " no existe"));
    }

    @Override
    public MovimientoDTO create(CreateMovimientoDTO dto) throws NotFoundException {
        Cuenta cuenta = cuentaService.getByNumber(dto.getNoCuenta());
        if (dto.getValor() < 0 && cuenta.getSaldoDisponible() < Math.abs(dto.getValor())){
            throw new NotFoundException("Saldo no disponible");
        }
        var saldoRestante = cuenta.getSaldoDisponible() + dto.getValor();
        Movimiento movimiento = build(dto);
        movimiento.setSaldo(saldoRestante);
        movimiento.setCuenta(cuenta);
        cuenta.setSaldoDisponible(saldoRestante);
        cuenta.addMovimiento(movimiento);
        movimiento = movimientoRepository.save(movimiento);
        cuentaService.save(cuenta);

        return movimientoMapper.toDto(movimiento);
    }

    @Override
    public List<Movimiento> filter(String clientId, LocalDateTime fechaInicio, LocalDateTime fechaFin){
        if (ObjectUtils.isEmpty(fechaInicio) && ObjectUtils.isEmpty(fechaFin)){
            return movimientoRepository.findAllByCuentaClienteId(clientId);
        }
        return movimientoRepository.findAllByFechaBetweenAndCuentaClienteIdOrderByFechaDesc(fechaInicio, fechaFin, clientId);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        if (!movimientoRepository.existsById(id)){
            throw new NotFoundException("No existe el movimiento con id " + id);
        }
        movimientoRepository.deleteById(id);
    }

    private Movimiento build(CreateMovimientoDTO dto){
        Movimiento movimiento = movimientoMapper.toEntity(dto);
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setTipoMovimiento(determineType(dto.getValor()));
        return movimiento;
    }

    private TipoMovimiento determineType(double valor){
        return valor < 0 ? TipoMovimiento.RETIRO : TipoMovimiento.DEPOSITO;
    }

    private List<MovimientoDTO> buildListFromPage(Page<Movimiento> movimientos){
        if (movimientos.isEmpty()) {
            return new ArrayList<>();
        }
        return movimientoMapper.toDtoList(movimientos.getContent());
    }
}
