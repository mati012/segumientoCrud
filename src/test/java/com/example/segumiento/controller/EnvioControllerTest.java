package com.example.segumiento.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.example.segumiento.model.Envio;
import com.example.segumiento.service.EnvioService;
import org.springframework.hateoas.EntityModel;
@ExtendWith(MockitoExtension.class)
public class EnvioControllerTest {
    @Mock
    private EnvioService envioService;

    @InjectMocks
    private EnvioController envioController;

    @Test
    void testGetAllEnvios() {
   
        List<Envio> envios = List.of(new Envio(), new Envio());
        when(envioService.getAllEnvios()).thenReturn(envios);


        ResponseEntity<List<EntityModel<Envio>>> response = envioController.getAllEnvios();
     
        assertEquals(2, response.getBody().size()); 
    }
     @Test
    void testCreateEnvio() {
        
        Envio envio = new Envio();
        when(envioService.createEnvio(envio)).thenReturn(envio);


        ResponseEntity<EntityModel<Envio>> response = envioController.createEnvio(envio);

        assertNotNull(response.getBody()); 
        

    }
    @Test
    void testUpdateEstado() {
        
        Long id = 1L;
        String nuevoEstado = "Entregado";
        Envio envioActualizado = new Envio();
        envioActualizado.setEstado(nuevoEstado);
        when(envioService.updateEnvioEstado(id, nuevoEstado)).thenReturn(envioActualizado);

   
        ResponseEntity<EntityModel<Envio>> response = envioController.updateEstado(id, nuevoEstado);

  
        assertNotNull(response.getBody()); 
       
        assertEquals(nuevoEstado, response.getBody().getContent().getEstado()); 
    }

}
