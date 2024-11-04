package com.test.cliente_persona_api.services.cliente.dto;

import com.test.cliente_persona_api.services.cliente.models.enums.Genero;

public record ClienteDTO (
    Long id,
    int edad,
    String nombre,
    String direccion,
    String telefono,
    Genero genero,
    String clienteId,
    String password,
    boolean estado
){}
