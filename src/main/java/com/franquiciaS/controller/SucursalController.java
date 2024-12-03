package com.franquiciaS.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.franquiciaS.model.Sucursal;
import com.franquiciaS.model.SucursalRequest;
import com.franquiciaS.repository.SucursalRepository;
import com.franquiciaS.service.SucursalService;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private SucursalRepository sucursalRepository;

    @PostMapping
    public ResponseEntity<Sucursal> agregarSucursal(@RequestBody SucursalRequest request) {
        Sucursal sucursal = sucursalService.agregarSucursal(request.getFranquiciaId(), request.getSucursal());
        return ResponseEntity.ok(sucursal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarNombreSucursal(@PathVariable String id, @RequestBody String nuevoNombre) {
        Optional<Sucursal> sucursalOpt = sucursalRepository.findById(id);
        if (!sucursalOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sucursal no encontrada");
        }
        Sucursal sucursal = sucursalOpt.get();
        sucursal.setNombre(nuevoNombre);
        sucursalRepository.save(sucursal);
        return ResponseEntity.ok("Nombre de la sucursal actualizado");
    }

}
