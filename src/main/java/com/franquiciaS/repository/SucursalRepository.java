package com.franquiciaS.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.franquiciaS.model.Sucursal;

@Repository
public interface  SucursalRepository extends MongoRepository<Sucursal, String> {
    
}
