package com.franquiciaS.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franquiciaS.model.Franquicia;
import com.franquiciaS.repository.FranquiciaRepository;

@Service
public class FranquiciaService {

    @Autowired
    private FranquiciaRepository franquiciaRepository;

    public Franquicia agregarFranquicia(Franquicia franquicia) {
        return franquiciaRepository.save(franquicia);
    }

    public Optional<Franquicia> obtenerFranquicia(String id) {
        return franquiciaRepository.findById(id);
    }

    public Franquicia actualizarFranquicia(String id, Franquicia franquicia) {
        return franquiciaRepository.findById(id)
            .map(existingFranquicia -> {
                existingFranquicia.setNombre(franquicia.getNombre());
                return franquiciaRepository.save(existingFranquicia);
            }).orElseThrow(() -> new RuntimeException("Franquicia no encontrada"));
    }
    
}
