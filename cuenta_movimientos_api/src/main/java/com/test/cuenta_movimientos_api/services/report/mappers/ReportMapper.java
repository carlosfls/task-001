package com.test.cuenta_movimientos_api.services.report.mappers;

import com.test.cuenta_movimientos_api.services.cuentas.dto.CuentaClienteDTO;
import com.test.cuenta_movimientos_api.services.report.dto.EstadoCuentaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReportMapper {

    EstadoCuentaDTO toDto(CuentaClienteDTO cuentaClienteDTO);
}
