package com.test.cliente_persona_api.services.cliente;

import com.test.cliente_persona_api.services.cliente.dto.ClienteDTO;
import com.test.cliente_persona_api.services.cliente.dto.CreateClienteDTO;
import com.test.cliente_persona_api.services.cliente.dto.UpdateClienteDTO;

import java.util.List;

public interface ClienteService {

    List<ClienteDTO> getAll(int pageNo, int pageSize);

    ClienteDTO getById(Long id);

    String getByClienteId(String clienteId);

    ClienteDTO create(CreateClienteDTO dto);

    ClienteDTO update(Long id, UpdateClienteDTO dto);

    void delete(Long id);
}
