package com.test.cuenta_movimientos_api.resources;

import com.test.cuenta_movimientos_api.exceptions.AlreadyExistException;
import com.test.cuenta_movimientos_api.exceptions.NotFoundException;
import com.test.cuenta_movimientos_api.exceptions.UnprocessableEntityException;
import com.test.cuenta_movimientos_api.services.cuentas.CuentaService;
import com.test.cuenta_movimientos_api.services.cuentas.dto.CreateCuentaDTO;
import com.test.cuenta_movimientos_api.services.cuentas.dto.CuentaClienteDTO;
import com.test.cuenta_movimientos_api.services.cuentas.dto.CuentaDTO;
import com.test.cuenta_movimientos_api.services.cuentas.dto.UpdateCuentaDTO;
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
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class CuentaResource {

    private final CuentaService cuentaService;

    @GetMapping
    public ResponseEntity<List<CuentaDTO>> getAll(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {

        return ResponseEntity.ok(cuentaService.getAll(pageNo, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> getById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(cuentaService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CuentaClienteDTO> create(@RequestBody @Valid CreateCuentaDTO dto) throws UnprocessableEntityException, AlreadyExistException, NotFoundException {
        return ResponseEntity.status(201).body(cuentaService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaDTO> update(@PathVariable Long id, @RequestBody UpdateCuentaDTO dto) throws NotFoundException {
        return ResponseEntity.ok(cuentaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws NotFoundException {
        cuentaService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clienteId/{clienteId}")
    public ResponseEntity<Void> delete(@PathVariable String clienteId) {
        cuentaService.deleteByClienteId(clienteId);

        return ResponseEntity.noContent().build();
    }
}
