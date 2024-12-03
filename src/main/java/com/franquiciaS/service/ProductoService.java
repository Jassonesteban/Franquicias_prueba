package com.franquiciaS.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franquiciaS.model.Producto;
import com.franquiciaS.model.Sucursal;
import com.franquiciaS.repository.SucursalRepository;

@Service
public class ProductoService {

    @Autowired
    private SucursalRepository sucursalRepository;

    public Producto agregarProducto(String sucursalId, Producto producto) {
        Sucursal sucursal = sucursalRepository.findById(sucursalId)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
        producto.setId(UUID.randomUUID().toString());
        sucursal.getProductos().add(producto);
        sucursalRepository.save(sucursal);
        return producto;
    }

    public Producto eliminarProducto(Sucursal sucursal, String nombreProducto) {
        Optional<Producto> producto = sucursal.getProductos().stream()
            .filter(p -> p.getNombre().equals(nombreProducto))
            .findFirst();

        producto.ifPresent(sucursal.getProductos()::remove);
        return producto.orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Producto modificarStock(Sucursal sucursal, String nombreProducto, int nuevoStock) {
        Producto producto = sucursal.getProductos().stream()
            .filter(p -> p.getNombre().equals(nombreProducto))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.setStock(nuevoStock);
        return producto;
    }

    public Producto productoConMasStock(Sucursal sucursal) {
        return sucursal.getProductos().stream()
            .max((p1, p2) -> Integer.compare(p1.getStock(), p2.getStock()))
            .orElseThrow(() -> new RuntimeException("No hay productos"));
    }
    
}
