package com.test.cuenta_movimientos_api.services.cuentas.mappers;

import com.test.cuenta_movimientos_api.services.cuentas.dto.CreateCuentaDTO;
import com.test.cuenta_movimientos_api.services.cuentas.dto.CuentaClienteDTO;
import com.test.cuenta_movimientos_api.services.cuentas.dto.CuentaDTO;
import com.test.cuenta_movimientos_api.services.cuentas.dto.UpdateCuentaDTO;
import com.test.cuenta_movimientos_api.services.cuentas.models.entity.Cuenta;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CuentaMapper {

    CuentaDTO toDto(Cuenta cuenta);

    CuentaClienteDTO toCuentaClienteDto(Cuenta cuenta);

    List<CuentaDTO> toDtoList(List<Cuenta> cuentas);

    Cuenta toEntity(CreateCuentaDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cuenta partialUpdate(UpdateCuentaDTO dto, @MappingTarget Cuenta target);
}
