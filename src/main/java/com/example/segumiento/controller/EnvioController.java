package com.example.segumiento.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.segumiento.model.Envio;
import com.example.segumiento.service.EnvioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/envios")
public class EnvioController {

    private static final Logger log = LoggerFactory.getLogger(EnvioController.class);

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public ResponseEntity<List<EntityModel<Envio>>> getAllEnvios() {
        log.info("GET/envios");
        log.info("retorna todas los envios");
        List<Envio> envios = envioService.getAllEnvios();
        List<EntityModel<Envio>> enviosModel = envios.stream()
                .map(envio -> EntityModel.of(envio,
                        WebMvcLinkBuilder.linkTo(methodOn(EnvioController.class).getAllEnvios()).withSelfRel()))
                .toList();
        return ResponseEntity.ok(enviosModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Envio>> createEnvio(@RequestBody Envio envio) {
        log.info("POST/envios");
        log.info("crea un objeto envio");
        Envio nuevoEnvio = envioService.createEnvio(envio);
        EntityModel<Envio> envioModel = EntityModel.of(nuevoEnvio,
                WebMvcLinkBuilder.linkTo(methodOn(EnvioController.class).getAllEnvios()).withSelfRel());

        return ResponseEntity.created(WebMvcLinkBuilder.linkTo(methodOn(EnvioController.class).getAllEnvios()).toUri())
                .body(envioModel);
    }

    @PatchMapping("/{id}/updateEnvios")
    public ResponseEntity<EntityModel<Envio>> updateEstado(@PathVariable Long id, @RequestBody String nuevoEstado) {
        log.info("PATCH/envios/{id}/updateEnvios");
        log.info("actualiza el estado de un envio por id");
        Envio envioActualizado = envioService.updateEnvioEstado(id, nuevoEstado);

        if (envioActualizado != null) {
            EntityModel<Envio> envioModel = EntityModel.of(envioActualizado,
                    WebMvcLinkBuilder.linkTo(methodOn(EnvioController.class).getAllEnvios()).withSelfRel());

            return ResponseEntity.ok(envioModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/getEnvios")
    public ResponseEntity<EntityModel<Envio>> getEnvioById(@PathVariable Long id) {
        log.info("GET/envios/{id}/getEnvios");
        log.info("Retorna la información de un envío");
        Optional<Envio> envio = envioService.getEnvioById(id);
    
        if (envio.isPresent()) {
            EntityModel<Envio> envioModel = EntityModel.of(envio.get(),
                    WebMvcLinkBuilder.linkTo(methodOn(EnvioController.class).getAllEnvios()).withSelfRel());
    
            return ResponseEntity.ok(envioModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/ubicacion")
    public ResponseEntity<String> getUbicacionById(@PathVariable Long id) {
        log.info("GET/envios/{id}/ubicacion");
        log.info("Retorna la ubicación actual de un envío");
        Optional<Envio> envio = envioService.getEnvioById(id);
    
        if (envio.isPresent()) {
            return ResponseEntity.ok(envio.get().getUbicacionActual());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

@DeleteMapping("/{id}")
public ResponseEntity<?> deleteEnvio(@PathVariable Long id) {
    log.info("DELETE/envios/{id}");
    envioService.deleteEnvio(id);

    // Crear un enlace a la lista de todos los envíos
    WebMvcLinkBuilder linkToAllEnvios = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnvioController.class)
            .getAllEnvios());

    // Devolver un No Content con un enlace HATEOAS en el encabezado
    return ResponseEntity.noContent().header("Link", linkToAllEnvios.withRel("todos-los-envios").toUri().toString()).build();
}

}
