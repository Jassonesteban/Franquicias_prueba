package com.franquiciaS.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.franquiciaS.model.Franquicia;
import com.franquiciaS.model.Producto;
import com.franquiciaS.model.ProductoMaxStockResponse;
import com.franquiciaS.model.ProductoRequest;
import com.franquiciaS.model.Sucursal;
import com.franquiciaS.repository.FranquiciaRepository;
import com.franquiciaS.repository.ProductoRepository;
import com.franquiciaS.repository.SucursalRepository;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private FranquiciaRepository franquiciaRepository;

    @PostMapping
    public ResponseEntity<?> agregarProducto(@RequestBody ProductoRequest request) {

        Optional<Sucursal> sucursalOptional = sucursalRepository.findById(request.getSucursalId());
        if (!sucursalOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sucursal no encontrada");
        }

        Sucursal sucursal = sucursalOptional.get();
        Producto producto = request.getProducto();
        Producto savedProducto = productoRepository.save(producto);
        sucursal.getProductos().add(savedProducto);
        sucursalRepository.save(sucursal);

        return ResponseEntity.status(HttpStatus.CREATED).body("Producto agregado correctamente");
    }

    @DeleteMapping
    public ResponseEntity<?> eliminarProducto(@RequestBody ProductoRequest request) {

        Optional<Sucursal> sucursalOptional = sucursalRepository.findById(request.getSucursalId());
        if (!sucursalOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sucursal no encontrada");
        }

        Sucursal sucursal = sucursalOptional.get();
        Optional<Producto> productoOptional = sucursal.getProductos().stream()
                .filter(producto -> producto.getId().equals(request.getProductoId()))
                .findFirst();

        if (!productoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado en esta sucursal");
        }

        Producto producto = productoOptional.get();
        sucursal.getProductos().remove(producto);
        sucursalRepository.save(sucursal);

        return ResponseEntity.status(HttpStatus.OK).body("Producto eliminado correctamente");
    }

    @PutMapping("/stock")
    public Producto modificarStock(@RequestBody ProductoRequest request) {
        
        Sucursal sucursal = sucursalRepository.findById(request.getSucursalId())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        Producto producto = productoRepository.findById(request.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setStock(request.getProducto().getStock());
        productoRepository.save(producto);

        List<Producto> productosActualizados = sucursal.getProductos().stream()
                .map(p -> p.getId().equals(request.getProductoId()) ? producto : p)
                .collect(Collectors.toList());

        sucursal.setProductos(productosActualizados);
        sucursalRepository.save(sucursal);

        return producto;
    }

    @GetMapping("/max-stock")
    public ResponseEntity<List<ProductoMaxStockResponse>> productoConMasStock(@RequestParam String franquiciaId) {
        Optional<Franquicia> franquiciaOpt = franquiciaRepository.findById(franquiciaId);
        if (!franquiciaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Franquicia franquicia = franquiciaOpt.get();
        List<Sucursal> sucursales = franquicia.getSucursales();

        List<ProductoMaxStockResponse> respuesta = new ArrayList<>();
        for (Sucursal sucursal : sucursales) {
            Producto productoMaxStock = sucursal.getProductos().stream()
                    .max(Comparator.comparingInt(Producto::getStock))
                    .orElse(null);

            if (productoMaxStock != null) {
                ProductoMaxStockResponse response = new ProductoMaxStockResponse();
                response.setSucursal(sucursal.getNombre());
                response.setProducto(productoMaxStock);
                respuesta.add(response);
            }
        }

        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("updateName/{id}")
    public ResponseEntity<String> actualizarNombreProducto(@PathVariable String id, @RequestBody String nuevoNombre) {
        Optional<Producto> productoOpt = productoRepository.findById(id);
        if (!productoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
        Producto producto = productoOpt.get();
        producto.setNombre(nuevoNombre);
        productoRepository.save(producto);
        return ResponseEntity.ok("Nombre del producto actualizado");
    }
}
