package com.example.segumiento.service;
import org.hibernate.annotations.DialectOverride.OverridesAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import com.example.segumiento.model.Envio;
import com.example.segumiento.repository.EnvioRepository;


@Service
public class EnvioServiceImpl implements EnvioService {
    @Autowired
    private EnvioRepository envioRepository;

    @Override
     public List<Envio> getAllEnvios(){
        return envioRepository.findAll();
       }

    @Override
    public Envio createEnvio(Envio envio){
        return envioRepository.save(envio);
    }
    @Override
public Optional<Envio> getEnvioById(Long id) {
    return envioRepository.findById(id);
}

    @Override
    public Envio updateEnvioEstado(Long id, String nuevoEstado) {
        Optional<Envio> envioExistente = envioRepository.findById(id);
        if (envioExistente.isPresent()) {      
            Envio envio = envioExistente.get();
            envio.setEstado(nuevoEstado);
            return envioRepository.save(envio);
        } else {
            return null; 
        }
    }

    @Override
    public Optional<String> getUbicacionById(Long id) {
        
        Optional<Envio> envio = envioRepository.findById(id);
        return envio.map(Envio::getUbicacionActual);
    }

    @Override
    public void deleteEnvio(Long id){
        envioRepository.deleteById(id);

    }


}
