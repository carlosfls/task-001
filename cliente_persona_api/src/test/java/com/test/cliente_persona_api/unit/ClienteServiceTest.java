package com.test.cliente_persona_api.unit;


import com.test.cliente_persona_api.exceptions.NotFoundException;
import com.test.cliente_persona_api.services.cliente.dto.ClienteDTO;
import com.test.cliente_persona_api.services.cliente.impl.ClienteServiceImpl;
import com.test.cliente_persona_api.services.cliente.mappers.ClienteMapper;
import com.test.cliente_persona_api.services.cliente.models.entity.Cliente;
import com.test.cliente_persona_api.services.cliente.models.enums.Genero;
import com.test.cliente_persona_api.services.cliente.repositories.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;
    private ClienteDTO dto;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("carlosf","12345678",true);
        cliente.setId(1L);
        cliente.setDireccion("Test calle test");
        cliente.setGenero(Genero.MASCULINO);
        cliente.setNombre("Carlos");
        cliente.setEdad(31);
        cliente.setTelefono("5678123456");

        dto = new ClienteDTO(1L,31,"Carlos","Test calle test",
                "5678123456",Genero.MASCULINO,"carlosf","12345678",true);
    }

    @Test
    void test_getById_whenMethodCall_ThenReturnClienteDTO() throws NotFoundException {
        //arrange
        Mockito.doReturn(Optional.of(cliente))
                .when(clienteRepository).findById(ArgumentMatchers.any());

        Mockito.doReturn(dto)
                .when(clienteMapper).toDto(ArgumentMatchers.any());
        //act
        ClienteDTO result = clienteService.getById(ArgumentMatchers.any());
        //assert
        Assertions.assertNotNull(result, "Debe existir el cliente");
        Assertions.assertEquals(1L ,result.id());
    }

    @Test
    void test_getById_whenMethodCall_ThenThrowsNotFoundException() {
        //arrange
        Mockito.doReturn(Optional.empty())
                .when(clienteRepository).findById(ArgumentMatchers.any());

        //act assert
        Assertions.assertThrows(NotFoundException.class,
                () -> clienteService.getById(ArgumentMatchers.any()));
    }
}
