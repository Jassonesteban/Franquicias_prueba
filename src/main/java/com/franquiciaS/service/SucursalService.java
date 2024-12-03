package com.franquiciaS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franquiciaS.model.Franquicia;
import com.franquiciaS.model.Sucursal;
import com.franquiciaS.repository.FranquiciaRepository;
import com.franquiciaS.repository.SucursalRepository;

@Service
public class SucursalService {

    @Autowired
    private FranquiciaRepository franquiciaRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    public Sucursal agregarSucursal(String franquiciaId, Sucursal sucursal) {
        Franquicia franquicia = franquiciaRepository.findById(franquiciaId)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada"));

        sucursal.setFranquicia(franquicia);
        Sucursal nuevaSucursal = sucursalRepository.save(sucursal);
        franquicia.getSucursales().add(nuevaSucursal);
        franquiciaRepository.save(franquicia);

        return nuevaSucursal;
    }

}
