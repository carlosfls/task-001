package com.test.cuenta_movimientos_api.resources;

import com.test.cuenta_movimientos_api.exceptions.AlreadyExistException;
import com.test.cuenta_movimientos_api.exceptions.NotFoundException;
import com.test.cuenta_movimientos_api.exceptions.UnprocessableEntityException;
import com.test.cuenta_movimientos_api.services.movimientos.MovimientoService;
import com.test.cuenta_movimientos_api.services.movimientos.dto.CreateMovimientoDTO;
import com.test.cuenta_movimientos_api.services.movimientos.dto.MovimientoDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientoResource {

    private final MovimientoService movimientoService;

    @GetMapping
    public ResponseEntity<List<MovimientoDTO>> getAll(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {

        return ResponseEntity.ok(movimientoService.getAll(pageNo, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoDTO> getById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(movimientoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<MovimientoDTO> create(@RequestBody @Valid CreateMovimientoDTO dto) throws UnprocessableEntityException, AlreadyExistException, NotFoundException {
        return ResponseEntity.status(201).body(movimientoService.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws NotFoundException {
        movimientoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
