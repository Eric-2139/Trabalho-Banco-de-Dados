package com.example.trabalhobancodedados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TrabalhobancodedadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrabalhobancodedadosApplication.class, args);
	}

}
