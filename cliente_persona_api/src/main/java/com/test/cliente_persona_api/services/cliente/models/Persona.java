package com.test.cliente_persona_api.services.cliente.models;

import com.test.cliente_persona_api.services.cliente.models.enums.Genero;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int edad;
    private String nombre;
    private String direccion;
    private String telefono;
    private Genero genero;
}
