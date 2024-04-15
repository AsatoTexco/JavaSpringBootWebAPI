package com.example.springBootJava.model;

import com.example.springBootJava.repository.VeiculoRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Setter
@Getter
@Data

@Document
@EnableMongoRepositories

public class Veiculo {

    @Id
    private String id;

    private String modelo;
    private float preco;
    private String placa;

    @JsonIgnore
    private VeiculoRepository veiculoRepository;


    public Veiculo(String modelo, float preco, String placa) {
        super();
//        this.id = id;
        this.modelo = modelo;
        this.preco = preco;
        this.placa = placa;
    }


}
