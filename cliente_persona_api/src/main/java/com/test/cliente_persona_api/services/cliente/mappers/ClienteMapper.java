package com.test.cliente_persona_api.services.cliente.mappers;

import com.test.cliente_persona_api.services.cliente.dto.ClienteDTO;
import com.test.cliente_persona_api.services.cliente.dto.CreateClienteDTO;
import com.test.cliente_persona_api.services.cliente.dto.UpdateClienteDTO;
import com.test.cliente_persona_api.services.cliente.models.entity.Cliente;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteMapper {

    ClienteDTO toDto(Cliente cliente);

    List<ClienteDTO> toDtoList(List<Cliente> cliente);

    Cliente toEntity(CreateClienteDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cliente partialUpdate(UpdateClienteDTO dto, @MappingTarget Cliente target);
}
