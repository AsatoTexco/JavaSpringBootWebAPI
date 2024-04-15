package com.example.springBootJava.repository;

import com.example.springBootJava.model.Veiculo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

// usado para cadastrar VeiculoRepository.save()
public interface VeiculoRepository extends MongoRepository<Veiculo, String> {

    @Query("{modelo:'?0'}")
    Veiculo findItemByName(String name);

    public long count();

}