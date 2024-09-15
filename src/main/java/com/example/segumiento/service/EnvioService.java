package com.example.segumiento.service;


import com.example.segumiento.model.Envio;
import java.util.List;
import java.util.Optional;

public interface EnvioService {
    List<Envio> getAllEnvios();
    Envio createEnvio(Envio envio);
    Envio updateEnvioEstado(Long id, String nuevoEstado);
    Optional<String> getUbicacionById(Long id);
}
