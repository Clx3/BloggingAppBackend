package fi.tuni.bloggingapp;

import fi.tuni.bloggingapp.entity.User;
import fi.tuni.bloggingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan(basePackages = {
		"fi.tuni.bloggingapp",
		"fi.tuni.bloggingapp.configuration",
		"fi.tuni.bloggingapp.controller",
		"fi.tuni.bloggingapp.entity",
		"fi.tuni.bloggingapp.repository"})
public class Application {
	@Autowired
	UserRepository userRepo;
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
	public void setAdminAcc(){
		if(userRepo.findByUsername("admin") == null){
			userRepo.save(new User("admin", "21232f297a57a5a743894a0e4a801fc3", true));
		}
	}
    
}