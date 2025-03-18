package com.test.cuenta_movimientos_api.services.cuentas.impl;

import com.test.cuenta_movimientos_api.exceptions.AlreadyExistException;
import com.test.cuenta_movimientos_api.exceptions.NotFoundException;
import com.test.cuenta_movimientos_api.exceptions.UnprocessableEntityException;
import com.test.cuenta_movimientos_api.services.cuentas.CuentaService;
import com.test.cuenta_movimientos_api.services.cuentas.dto.CreateCuentaDTO;
import com.test.cuenta_movimientos_api.services.cuentas.dto.CuentaClienteDTO;
import com.test.cuenta_movimientos_api.services.cuentas.dto.CuentaDTO;
import com.test.cuenta_movimientos_api.services.cuentas.dto.UpdateCuentaDTO;
import com.test.cuenta_movimientos_api.services.cuentas.mappers.CuentaMapper;
import com.test.cuenta_movimientos_api.services.cuentas.models.entity.Cuenta;
import com.test.cuenta_movimientos_api.services.cuentas.repositories.CuentaRepository;
import com.test.cuenta_movimientos_api.services.integrations.ClientePersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final CuentaMapper cuentaMapper;
    private final ClientePersonaService clientePersonaService;

    @Override
    public List<CuentaDTO> getAll(int pageNo, int pageSize){
        return buildListFromPage(cuentaRepository.findAll(PageRequest.of(pageNo, pageSize)));
    }

    @Override
    public CuentaDTO getById(Long id) {
        return cuentaRepository.findById(id)
                .map(cuentaMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Cuenta con id " + id + " no existe"));
    }

    @Override
    public Cuenta getByNumber(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new NotFoundException("Cuenta con numero " + numeroCuenta + " no existe"));
    }

    @Transactional
    @Override
    public CuentaClienteDTO create(CreateCuentaDTO dto) {
        if (cuentaRepository.existsByNumeroCuenta(dto.getNumeroCuenta())){
            throw new AlreadyExistException("La cuenta ya existe");
        }
        var nombreCliente = clientePersonaService.getClientName(dto.getClienteId());

        return Optional.ofNullable(cuentaMapper.toEntity(dto))
                .map(cuenta -> cuenta.withEstado(true))
                .map(cuenta -> cuenta.withSaldoDisponible(dto.getSaldoInicial()))
                .map(this::save)
                .map(cuenta -> buildCuentaCliente(cuenta, nombreCliente))
                .orElseThrow(() -> new UnprocessableEntityException("Error al crear la cuenta"));
    }

    @Transactional
    @Override
    public CuentaDTO update(Long id, UpdateCuentaDTO dto) {
        return cuentaRepository.findById(id)
                .map(c -> cuentaMapper.partialUpdate(dto, c))
                .map(this::save)
                .map(cuentaMapper::toDto)
                .orElseThrow(() -> new NotFoundException("No existe la cuenta con id " + id));
    }

    @Override
    public void delete(Long id) {
        if (!cuentaRepository.existsById(id)){
            throw new NotFoundException("No existe la cuenta con id " + id);
        }
        cuentaRepository.deleteById(id);
    }

    @Override
    public void deleteByClienteId(String clienteId) {
        cuentaRepository.deleteAllByClienteId(clienteId);
    }

    @Override
    public Cuenta save(Cuenta cuenta){
        return cuentaRepository.save(cuenta);
    }

    @Override
    public boolean existCuentaForCliente(String clientId){
        return cuentaRepository.existsByClienteId(clientId);
    }

    @Override
    public CuentaClienteDTO buildCuentaCliente(Cuenta cuenta, String nombreCliente){
        CuentaClienteDTO dto = cuentaMapper.toCuentaClienteDto(cuenta);
        dto.setNombreCliente(nombreCliente);
        return dto;
    }

    private List<CuentaDTO> buildListFromPage(Page<Cuenta> cuentas){
        if (cuentas.isEmpty()) {
            return new ArrayList<>();
        }
        return cuentaMapper.toDtoList(cuentas.getContent());
    }
}
