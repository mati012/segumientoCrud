package com.example.segumiento.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.segumiento.model.Envio;
import com.example.segumiento.repository.EnvioRepository;

@ExtendWith(MockitoExtension.class)
public class EnvioServiceTest {
    @InjectMocks
    private EnvioServiceImpl envioServicio;

    @Mock
    private EnvioRepository envioRepositoryMock;
    //crear objeto
    @Test
    public void guardarEnvio(){
    Envio envio= new Envio();
    envio.setNombreProducto("comida");
    when(envioRepositoryMock.save(any())).thenReturn(envio);
    Envio resultado = envioServicio.createEnvio(envio);

    assertEquals("comida", resultado.getNombreProducto());
    }
    //obtener ubicacion
    @Test
    public void obtenerUbicacion(){
        Envio envio = new Envio();
        envio.setId(1L);
        envio.setUbicacionActual("Santiago");
        when(envioRepositoryMock.findById(1L)).thenReturn(Optional.of(envio));
        Optional<String> result = envioServicio.getUbicacionById(1L);
        
        assertTrue(result.isPresent());
        assertEquals("Santiago", result.get());
    
    }

}
