package com.test.cuenta_movimientos_api.services.movimientos;

import com.test.cuenta_movimientos_api.exceptions.AlreadyExistException;
import com.test.cuenta_movimientos_api.exceptions.NotFoundException;
import com.test.cuenta_movimientos_api.exceptions.UnprocessableEntityException;
import com.test.cuenta_movimientos_api.services.movimientos.dto.CreateMovimientoDTO;
import com.test.cuenta_movimientos_api.services.movimientos.dto.MovimientoDTO;
import com.test.cuenta_movimientos_api.services.movimientos.models.entity.Movimiento;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoService {
    List<MovimientoDTO> getAll(int pageNo, int pageSize);

    MovimientoDTO getById(Long id) throws NotFoundException;

    MovimientoDTO create(CreateMovimientoDTO dto) throws AlreadyExistException, UnprocessableEntityException, NotFoundException;

    List<Movimiento> filter(String clientId, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    void delete(Long id) throws NotFoundException;
}
