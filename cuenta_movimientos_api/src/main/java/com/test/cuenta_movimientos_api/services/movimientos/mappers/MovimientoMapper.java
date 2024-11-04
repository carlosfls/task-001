package com.test.cuenta_movimientos_api.services.movimientos.mappers;

import com.test.cuenta_movimientos_api.services.movimientos.dto.CreateMovimientoDTO;
import com.test.cuenta_movimientos_api.services.movimientos.dto.MovimientoDTO;
import com.test.cuenta_movimientos_api.services.movimientos.models.entity.Movimiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MovimientoMapper {

    @Mapping(target = "numeroCuenta", source = "cuenta.numeroCuenta")
    MovimientoDTO toDto(Movimiento movimiento);

    List<MovimientoDTO> toDtoList(List<Movimiento> movimientos);

    Movimiento toEntity(CreateMovimientoDTO dto);
}
