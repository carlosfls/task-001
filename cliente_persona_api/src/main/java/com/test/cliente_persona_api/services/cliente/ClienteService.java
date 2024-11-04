package com.test.cliente_persona_api.services.cliente;

import com.test.cliente_persona_api.exceptions.AlreadyExistException;
import com.test.cliente_persona_api.exceptions.NotFoundException;
import com.test.cliente_persona_api.exceptions.UnprocessableEntityException;
import com.test.cliente_persona_api.services.cliente.dto.ClienteDTO;
import com.test.cliente_persona_api.services.cliente.dto.CreateClienteDTO;
import com.test.cliente_persona_api.services.cliente.dto.UpdateClienteDTO;

import java.util.List;

public interface ClienteService {

    List<ClienteDTO> getAll(int pageNo, int pageSize);

    ClienteDTO getById(Long id) throws NotFoundException;

    String getByClienteId(String clienteId) throws NotFoundException;

    ClienteDTO create(CreateClienteDTO dto) throws AlreadyExistException, UnprocessableEntityException;

    ClienteDTO update(Long id, UpdateClienteDTO dto) throws NotFoundException;

    void delete(Long id) throws NotFoundException;
}
