package com.franquiciaS.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.franquiciaS.model.Producto;

@Repository
public interface ProductoRepository extends MongoRepository<Producto, String> {
    
}
