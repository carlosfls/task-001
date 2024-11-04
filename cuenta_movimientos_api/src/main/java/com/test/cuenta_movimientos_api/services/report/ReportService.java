package com.test.cuenta_movimientos_api.services.report;

import com.test.cuenta_movimientos_api.exceptions.NotFoundException;
import com.test.cuenta_movimientos_api.services.report.dto.EstadoCuentaDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    /**
     * Obtener el estado de cuentas de un cliente y si se desea filtrar por fecha en caso de especificar rango.
     *
     * @param clienteId     {@link String} Identificador del cliente
     * @param fechaInicio   {@link LocalDate} Fecha inicio del movimiento
     * @param fechaFin      {@link LocalDate} Fecha fin del movimiento, si no se especifica se usa la fecha actual.
     * @return {@link List<EstadoCuentaDTO>} Los movimientos de ese cliente en cada cuenta filtrados por fecha (si se especifica un rango).
     * @throws NotFoundException si el cliente no tiene cuentas asociadas.
     */
    List<EstadoCuentaDTO> getEstadoCuenta(String clienteId, LocalDate fechaInicio, LocalDate fechaFin) throws NotFoundException;
}