package com.example.springBootJava;

import com.example.springBootJava.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SpringBootJavaApplication implements CommandLineRunner {

	@Autowired
	VeiculoRepository veiculoRepository;


	public static void main(String[] args) {
		SpringApplication.run(SpringBootJavaApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("UGAAAA");
		//veiculoRepository.save(new Veiculo("dguyawduiwa","Camaro Azul",30060000,"k2kkkkk"));


		// Veiculo veiculo = new Veiculo("91","OLOCO", 45678,"null>>>");


	}




}
