package com.test.cliente_persona_api.resources;

import com.test.cliente_persona_api.exceptions.AlreadyExistException;
import com.test.cliente_persona_api.exceptions.NotFoundException;
import com.test.cliente_persona_api.exceptions.UnprocessableEntityException;
import com.test.cliente_persona_api.services.cliente.ClienteService;
import com.test.cliente_persona_api.services.cliente.dto.ClienteDTO;
import com.test.cliente_persona_api.services.cliente.dto.CreateClienteDTO;
import com.test.cliente_persona_api.services.cliente.dto.UpdateClienteDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteResource {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAll(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        List<ClienteDTO> clientes = clienteService.getAll(pageNo, pageSize);

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getById(@PathVariable Long id) throws NotFoundException {
        System.out.println("");
        return ResponseEntity.ok(clienteService.getById(id));
    }

    @GetMapping("/clienteId/{clienteId}")
    public ResponseEntity<String> getNameByClienteId(@PathVariable String clienteId) throws NotFoundException {
        return ResponseEntity.ok(clienteService.getByClienteId(clienteId));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody @Valid CreateClienteDTO dto) throws UnprocessableEntityException, AlreadyExistException {
        return ResponseEntity.status(201).body(clienteService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody UpdateClienteDTO dto) throws NotFoundException {
        return ResponseEntity.ok(clienteService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws NotFoundException {
        clienteService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
