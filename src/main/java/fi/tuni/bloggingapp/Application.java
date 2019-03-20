package fi.tuni.bloggingapp;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"fi.tuni.bloggingapp",
		"fi.tuni.bloggingapp.configuration",
		"fi.tuni.bloggingapp.controller",
		"fi.tuni.bloggingapp.entity",
		"fi.tuni.bloggingapp.repository"})
public class Application {
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}