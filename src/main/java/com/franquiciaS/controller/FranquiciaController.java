package com.franquiciaS.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.franquiciaS.model.Franquicia;
import com.franquiciaS.repository.FranquiciaRepository;
import com.franquiciaS.service.FranquiciaService;

@RestController
@RequestMapping("/api/franquicias")
public class FranquiciaController {

    @Autowired
    private FranquiciaService franquiciaService;

    @Autowired
    FranquiciaRepository franquiciaRepository;

    @PostMapping
    public Franquicia agregFranquicia(@RequestBody Franquicia franquicia) {
        return franquiciaService.agregarFranquicia(franquicia);
    }

    @GetMapping("/{id}")
    public Optional<Franquicia> obtenerFranquicia(@PathVariable String id) {
        return franquiciaService.obtenerFranquicia(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarFranquicia(@PathVariable String id, @RequestBody String nuevoNombre) {
        Optional<Franquicia> franquiciaOpt = franquiciaRepository.findById(id);
        if (!franquiciaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Franquicia no encontrada");
        }
        Franquicia franquicia = franquiciaOpt.get();
        franquicia.setNombre(nuevoNombre);
        franquiciaRepository.save(franquicia);
        return ResponseEntity.ok("Nombre de la franquicia actualizado");
    }
}
