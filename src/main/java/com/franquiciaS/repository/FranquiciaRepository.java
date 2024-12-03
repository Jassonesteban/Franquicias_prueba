package com.franquiciaS.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.franquiciaS.model.Franquicia;

@Repository
public interface FranquiciaRepository extends MongoRepository<Franquicia, String> {
    
}
