package com.example.spca;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.spca.entities")
public class SpcaApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpcaApplication.class, args);
	}
}