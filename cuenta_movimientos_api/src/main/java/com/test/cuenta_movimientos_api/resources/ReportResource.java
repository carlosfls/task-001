package com.test.cuenta_movimientos_api.resources;

import com.test.cuenta_movimientos_api.services.report.ReportService;
import com.test.cuenta_movimientos_api.services.report.dto.EstadoCuentaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReportResource {

    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<List<EstadoCuentaDTO>> getEstadoCuenta(
            @RequestParam String clienteId,
            @RequestParam(required = false) LocalDate fechaInicio,
            @RequestParam(required = false) LocalDate fechaFin) {

        return ResponseEntity.ok(reportService.getEstadoCuenta(clienteId, fechaInicio, fechaFin));
    }
}
