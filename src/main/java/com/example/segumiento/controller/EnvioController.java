package com.example.segumiento.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.swing.text.html.parser.Entity;

import com.example.segumiento.model.Envio;
import com.example.segumiento.service.EnvioService;
import org.springframework.web.bind.annotation.PutMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/envios")
public class EnvioController {

    private static final Logger log = LoggerFactory.getLogger(EnvioController.class);
    @Autowired
    private EnvioService envioService;

    
    @GetMapping
    public List<Envio> getAllEnvios(){
        log.info("GET/envios");
        log.info("retorna todas los envios ");
        return envioService.getAllEnvios();
    }

    @PostMapping
    public Envio createEnvio(@RequestBody Envio envio) {
        log.info("POST/envios");
        log.info("crea un objeto envio");
        return envioService.createEnvio(envio); 
    }
    
    @PutMapping("/{id}/estado")
    public ResponseEntity<Envio> updateEstado(@PathVariable Long id, @RequestBody String nuevoEstado) {
        log.info("PUT/envios/{id}/estado");
        log.info("actuliza el estado de un envio por id ");


        Envio envioActualizado = envioService.updateEnvioEstado(id, nuevoEstado);

        if (envioActualizado != null) {
            return ResponseEntity.ok(envioActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}/ubicacion")
    public ResponseEntity<String> getUbicacionById(@PathVariable Long id) {

        log.info("GET/envios/{id}/ubicacion");
        log.info("retorna la ubicacion de un envio  ");
        Optional<String> ubicacion = envioService.getUbicacionById(id);

        if (ubicacion.isPresent()) {
            return ResponseEntity.ok(ubicacion.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
