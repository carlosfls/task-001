package com.test.cliente_persona_api.integration;

import com.test.cliente_persona_api.services.cliente.models.entity.Cliente;
import com.test.cliente_persona_api.services.cliente.models.enums.Genero;
import com.test.cliente_persona_api.services.cliente.repositories.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class ClienteResourceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteRepository clienteRepository;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("carlosf","12345678",true);
        cliente.setId(1L);
        cliente.setDireccion("Test calle test");
        cliente.setGenero(Genero.MASCULINO);
        cliente.setNombre("Carlos");
        cliente.setEdad(31);
        cliente.setTelefono("5678123456");
    }

    @Test
    void test_getById_whenEndpointCall_ThenReturnClienteDTO() throws Exception {
        //arrange
        Long clienteId = 1L;

        Mockito.when(clienteRepository.findById(clienteId))
                .thenReturn(Optional.of(cliente));

        //act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/clientes/{id}", clienteId))
                .andReturn();

        //assert
        Assertions.assertEquals(200 ,mvcResult.getResponse().getStatus());
    }

    @Test
    void test_getById_whenEndpointCall_ThenThrowNotFoundException() throws Exception {
        //arrange
        Long clienteId = 2L;

        Mockito.when(clienteRepository.findById(clienteId))
                .thenReturn(Optional.empty());

        //act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/clientes/{id}", clienteId))
                .andReturn();

        //assert
        Assertions.assertEquals(404 ,mvcResult.getResponse().getStatus());
    }

}

