package com.test.cliente_persona_api.services.cliente.dto;

import com.test.cliente_persona_api.services.cliente.models.enums.Genero;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateClienteDTO {

    @NotNull(message = "La edad no puede ser nula")
    @Min(value = 1, message = "La edad debe ser mayor que 0")
    private int edad;

    @NotEmpty(message = "El nombre no debe estar vacio")
    private String nombre;

    @NotEmpty(message = "La direccion no debe estar vacia")
    private String direccion;

    @NotEmpty(message = "El genero no debe estar vacia")
    private String telefono;

    @NotNull(message = "Se debe elegir un genero")
    private Genero genero;

    @NotEmpty(message = "El id del cliente no puede estar vacio")
    private String clienteId;

    @Size(min = 8, max = 12, message = "El password debe tener una logintud minima de 8 y maxima de 12 caracteres")
    @NotEmpty(message = "El password no puede estar vacio")
    private String password;
}
