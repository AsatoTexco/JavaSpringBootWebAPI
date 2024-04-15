package com.example.springBootJava.controller;


import com.example.springBootJava.model.Veiculo;
import com.example.springBootJava.repository.VeiculoRepository;
import com.example.springBootJava.service.VeiculoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @GetMapping
    public String teste() throws JsonProcessingException {

        List<Veiculo> veiculos = veiculoRepository.findAll();
        System.out.println(veiculos);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        String json = objectMapper.writeValueAsString(veiculos);

        return json;
    }

    @GetMapping("/{id}")
    public String getVeiculoByID(@PathVariable String id) throws JsonProcessingException {

        Optional<Veiculo> veiculoGet = veiculoRepository.findById(id);
        System.out.println(veiculoGet);

        if (veiculoGet.isPresent()) {
            Veiculo veiculo = veiculoGet.get();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
            String json = objectMapper.writeValueAsString(veiculo);
            return json;
        }
        return "Nenhum dado encontrado!";
    }

    @PostMapping
    public void postTeste(@RequestBody String body) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(body);

        if (jsonNode.get("modelo") != null && jsonNode.get("placa") != null && jsonNode.get("preco") != null) {

            String modelo = jsonNode.get("modelo").asText();
            String placa = jsonNode.get("placa").asText();


            Float preco = Float.parseFloat(jsonNode.get("preco").asText());

            // Arredondar o número para duas casas decimais
            double roundedPreco = Math.round(preco * 100.0) / 100.0;

            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
            otherSymbols.setDecimalSeparator('.');

            DecimalFormat df = new DecimalFormat("#.##", otherSymbols);

            Float formattedNumber = Float.parseFloat(df.format(roundedPreco));

            System.out.println(formattedNumber);

            VeiculoService veiculoService = new VeiculoService(veiculoRepository);
            veiculoService.salvarVeiculo(new Veiculo(modelo, formattedNumber, placa));

        }

    }

    @DeleteMapping("/delete/{id}")
    public String deletar_veiculo(@PathVariable String id, HttpServletResponse response)   {



            VeiculoService veiculoService = new VeiculoService(veiculoRepository);

            if (veiculoService.existById(id)) {
                veiculoService.deleteVeiculo(id);
                response.setStatus(200);
                return "Vehicle has been deleted!";
            }

            return "Error: The Vehicle hasn't been excluded";
    }

    @PutMapping("/{id}")
    public String update_veiculo(@PathVariable String id, @RequestBody Veiculo veiculo, HttpServletResponse response){

        VeiculoService veiculoService = new VeiculoService(veiculoRepository);
        Boolean exist_by_id = veiculoService.existById(id);
        if(exist_by_id){
            Veiculo veiculo1 = veiculoService.findById(id);

            veiculo1.setModelo(veiculo.getModelo());
            veiculo1.setPlaca(veiculo.getPlaca());
            veiculo1.setPreco(veiculo.getPreco());

            veiculoRepository.save(veiculo1);
            return "The vehicle has been updated";
        }
        return "Error: The Vehicle hasn't been updated";

    }


}
