package com.example.springBootJava.service;

import com.example.springBootJava.model.Veiculo;
import com.example.springBootJava.repository.VeiculoRepository;

import java.util.Optional;

public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository){
        this.veiculoRepository = veiculoRepository;
    }

    public void salvarVeiculo(Veiculo veiculo){
        veiculoRepository.save(veiculo);
    }

    public void deleteVeiculo(String id){
        veiculoRepository.deleteById(id);
    }

    public Boolean existById(String id){
        return veiculoRepository.findById(id).isPresent();
    }
    public Veiculo findById(String id){
        Optional<Veiculo> veiculoDB = veiculoRepository.findById(id);
        return veiculoDB.orElse(null);
    }


}
