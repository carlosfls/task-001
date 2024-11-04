package com.test.cliente_persona_api.services.cliente.dto;

import com.test.cliente_persona_api.services.cliente.models.enums.Genero;
import lombok.Data;

@Data
public class UpdateClienteDTO {

    private int edad;
    private String nombre;
    private String direccion;
    private String telefono;
    private Genero genero;
    private String password;
    private Boolean estado;
}
