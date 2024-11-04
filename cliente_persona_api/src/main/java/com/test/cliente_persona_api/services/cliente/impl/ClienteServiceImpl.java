package com.test.cliente_persona_api.services.cliente.impl;

import com.test.cliente_persona_api.exceptions.AlreadyExistException;
import com.test.cliente_persona_api.exceptions.NotFoundException;
import com.test.cliente_persona_api.exceptions.UnprocessableEntityException;
import com.test.cliente_persona_api.services.cliente.ClienteService;
import com.test.cliente_persona_api.services.cliente.dto.ClienteDTO;
import com.test.cliente_persona_api.services.cliente.dto.CreateClienteDTO;
import com.test.cliente_persona_api.services.cliente.dto.UpdateClienteDTO;
import com.test.cliente_persona_api.services.cliente.mappers.ClienteMapper;
import com.test.cliente_persona_api.services.cliente.models.Persona;
import com.test.cliente_persona_api.services.cliente.models.entity.Cliente;
import com.test.cliente_persona_api.services.cliente.repositories.ClienteRepository;
import com.test.cliente_persona_api.services.integrations.CuentaMovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final CuentaMovimientoService cuentaMovimientoService;

    @Override
    public List<ClienteDTO> getAll(int pageNo, int pageSize){
        return buildListFromPage(clienteRepository.findAll(PageRequest.of(pageNo, pageSize)));
    }

    @Override
    public ClienteDTO getById(Long id) throws NotFoundException {
        return clienteRepository.findById(id)
                .map(clienteMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Cliente con id " + id + " no existe"));
    }

    @Override
    public String getByClienteId(String clienteId) throws NotFoundException {
        return clienteRepository.findByClienteId(clienteId)
                .map(Persona::getNombre)
                .orElseThrow(() -> new NotFoundException("Cliente con id " + clienteId + " no existe"));
    }

    @Override
    public ClienteDTO create(CreateClienteDTO dto) throws AlreadyExistException, UnprocessableEntityException {
        if (clienteRepository.existsByClienteId(dto.getClienteId())){
            throw new AlreadyExistException("El id proporcionado ya existe " + dto.getClienteId());
        }

        return Optional.ofNullable(clienteMapper.toEntity(dto))
                .map(cliente -> cliente.withEstado(true))
                .map(clienteRepository::save)
                .map(clienteMapper::toDto)
                .orElseThrow(() -> new UnprocessableEntityException("Error al salvar el cliente"));
    }

    @Override
    public ClienteDTO update(Long id, UpdateClienteDTO dto) throws NotFoundException {
        return clienteRepository.findById(id)
                .map(c -> clienteMapper.partialUpdate(dto, c))
                .map(clienteRepository::save)
                .map(clienteMapper::toDto)
                .orElseThrow(() -> new NotFoundException("No existe el cliente con id " + id));
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        if (!clienteRepository.existsById(id)){
            throw new NotFoundException("No existe un cliente con id "+id);
        }

        clienteRepository.findById(id).ifPresent(c -> {
            cuentaMovimientoService.deleteCuentasForCliente(c.getClienteId());
            clienteRepository.deleteById(c.getId());
        });
    }

    private List<ClienteDTO> buildListFromPage(Page<Cliente> clientes){
        if (clientes.isEmpty()) {
            return new ArrayList<>();
        }
        return clienteMapper.toDtoList(clientes.getContent());
    }
}
