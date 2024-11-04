package com.test.cliente_persona_api.services.cliente.models.entity;

import com.test.cliente_persona_api.services.cliente.models.Persona;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Persona {

    @Column(unique = true)
    private String clienteId;

    private String password;

    private boolean estado;

    public Cliente withEstado(boolean estado){
        this.estado = estado;
        return this;
    }

}
